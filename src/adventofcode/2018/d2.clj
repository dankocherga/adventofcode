(ns adventofcode.2018.d2
  (:require
    [clojure.set :as set]
    [clojure.math.combinatorics :as combo]))

;; Part 1

(defn- appears-exactly [n]
  (fn [id]
    (contains? (set/map-invert (frequencies id)) n)))

(defn part1 [input]
  (* (count (filter (appears-exactly 2) input))
     (count (filter (appears-exactly 3) input))))

;; Part 2

(defn- correct-ids? [[id1 id2]]
  "Count how many chars differ among ids. Return true if it's only 1"
  (= (count (filter false? (map = id1 id2))) 1))

(defn- common-letters [[id1 id2]]
  "Return string of only common letters among ids"
  (reduce str
          (map (fn [a b] (when (= a b) a))
               id1 id2)))

(defn part2 [input]
  (map common-letters
       (filter correct-ids? (combo/combinations input 2))))
