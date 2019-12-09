(ns adventofcode.2019.d3
  (:require [clojure.string :as str]))

(defn- crossed? [e1 e2]
  "Are two edges crossed with each other?"
  (and 
    (> (max (:x1 e1) (:x2 e1)) (min (:x1 e2) (:x2 e2)))
    (< (min (:x1 e1) (:x2 e1)) (max (:x1 e2) (:x2 e2)))
    (> (max (:y1 e1) (:y2 e1)) (min (:y1 e2) (:y2 e2)))
    (< (min (:y1 e1) (:y2 e1)) (max (:y1 e2) (:y2 e2)))))

(defn- cross-point [e1 e2]
  "Calculate coordinate of edges cross"
  {:x (max
        (min (:x1 e1) (:x2 e1))
        (min (:x1 e2) (:x2 e2)))
   :y (max
        (min (:y1 e1) (:y2 e1))
        (min (:y1 e2) (:y2 e2)))})

(defn- distance [p1 p2]
  "Manhattan distance between 2 coords"
  (+
   (Math/abs (- (:x p1) (:x p2)))
   (Math/abs (- (:y p1) (:y p2)))))

(defn move->vertex [move from-vertex]
  (case (:dir move)
    "R" (update-in from-vertex [:x] + (:dist move))
    "L" (update-in from-vertex [:x] - (:dist move))
    "D" (update-in from-vertex [:y] + (:dist move))
    "U" (update-in from-vertex [:y] - (:dist move))))

(defn- moves->vertex [moves]
  (loop [[move & other-moves] moves
         vertex [{:x 0 :y 0}]]
    (if (nil? move)
      vertex
      (recur other-moves (conj vertex (move->vertex move (last vertex)))))))

(defn- vertex->edges [vertex]
  (map (fn [[e1 e2]]
         {:x1 (:x e1) :x2 (:x e2) :y1 (:y e1) :y2 (:y e2)})
       (partition 2 1 vertex)))

(defn- edge-length [edge]
  "Measure the edge in a number of steps taken by the wire"
  (distance {:x (:x1 edge) :y (:y1 edge)}
                 {:x (:x2 edge) :y (:y2 edge)}))


(defn- parse-move [txt]
  (let [[_ & matches] (re-find #"(\D+)(\d+)" txt)
        [dir dist] matches]
    {:dir dir :dist (read-string dist)}))

(defn- parse-move-list [txt]
  (map parse-move (str/split txt #",")))

(defn- min-cross-distance [wire1-edges wire2-edges]
  (apply min (for [e1 wire1-edges
                   e2 wire2-edges
                   :when (crossed? e1 e2)]
               (distance (cross-point e1 e2) {:x 0 :y 0}))))

; Well... this is dirty
(defn- min-cross-steps [wire1-edges wire2-edges]
  (let [wire1-steps (vec (reductions + (map edge-length wire1-edges)))
        wire2-steps (vec (reductions + (map edge-length wire2-edges)))]
    (apply min (for [[e1-idx, e1] (map-indexed vector wire1-edges)
                     [e2-idx, e2] (map-indexed vector wire2-edges)
                     :let [wire1-traversed (get wire1-steps e1-idx)
                           wire2-traversed (get wire2-steps e2-idx)
                           cross (cross-point e1 e2)
                           wire1-distance (- wire1-traversed (distance {:x (:x2 e1) :y (:y2 e1)} cross))
                           wire2-distance (- wire2-traversed (distance {:x (:x2 e2) :y (:y2 e2)} cross))]
                     :when (crossed? e1 e2)]
                 (+ wire1-distance wire2-distance)))))

(defn solution [wire1-input wire2-input]
  (let [wire1-edges (vertex->edges (moves->vertex (parse-move-list wire1-input)))
        wire2-edges (vertex->edges (moves->vertex (parse-move-list wire2-input)))
        part1 (min-cross-distance wire1-edges wire2-edges)
        part2 (min-cross-steps wire1-edges wire2-edges)]
    [part1 part2]))
