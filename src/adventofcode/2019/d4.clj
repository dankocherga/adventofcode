(ns adventofcode.2019.d4
  (:require [clojure.math.numeric-tower :as math]))

(defn- num-digits [number]
  (loop [n number
         digits []]
    (if (= 0 n) digits
      (recur (quot n 10) (cons (rem n 10) digits)))))

(defn- all-ascending? [digits]
  (let [pairs (partition 2 1 digits)]
    (every? (fn [[d1 d2]] (>= d2 d1)) pairs)))

(defn- adjacent-groups [digits]
  "[x x x y z z] -> [3 1 2]"
  (let [pairs (partition 2 1 digits)]
    (loop [[[d1 d2] & others] pairs
           groups [1]]
      (if (nil? d1) groups
        (recur others
               (if (= d1 d2)
                 (conj (pop groups) (inc (peek groups)))
                 (conj groups 1)))))))

(defn- valid? [pass]
  (let [digits (num-digits pass)]
    (and (all-ascending? digits)
         (> (apply max (adjacent-groups digits)) 1))))

(defn- still-valid? [pass]
  (let [digits (num-digits pass)]
    (and (all-ascending? digits)
         (some #(= 2 %) (adjacent-groups digits)))))

(defn part1 [from to]
  (let [all-between (range from (inc to))]
    (count (filter valid? all-between))))

(defn part2 [from to]
  (let [all-between (range from (inc to))]
    (count (filter still-valid? all-between))))
