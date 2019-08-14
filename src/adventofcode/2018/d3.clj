(ns adventofcode.2018.d3
  (:require
    [clojure.math.combinatorics :as combo]
    ))

;; Fabric is represented as a nested map of coordinates and claims applied to those coordinates:
;; { x { y [ id1, id2, ... ] } }

(defn- apply-claim 
  "Apply claim to the fabic. Return updated fabric."
  [fab { :keys [id l t w h] }]
  (let [points (combo/cartesian-product
                 (range l (+ l w))
                 (range t (+ t h)))]
    (reduce (fn [fab [x y]]
              (update-in fab [x y] (fnil #(conj % id) [])))
            fab points)))

(defn- parse-claim
  "Parse claim string into hash"
  [txt]
  (let [[_ & matches] (re-find #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)" txt)
        [id l t w h] (map read-string matches)]
    { :id id :l l :t t :w w :h h }))

(defn- fabric-points
  [fab]
  (mapcat vals (vals fab)))

(defn- overlaps
  "Get points in fabric that have overlaps"
  [fab]
  (filter #(> (count %) 1) (fabric-points fab)))

(defn- no-overlaps-claims
  "Get claim IDs that are not overlapped"
  [fab claims]
  (let [claim-ids (into #{} (map :id claims))
        overlaps (overlaps fab)]
    (apply disj claim-ids (flatten overlaps))))

(defn solution
  [claims-txt]
  (let [claims (map parse-claim claims-txt)
        fabric (reduce apply-claim {} claims)
        part1  (count (overlaps fabric) )
        part2  (no-overlaps-claims fabric claims)]
    [part1 part2]))
