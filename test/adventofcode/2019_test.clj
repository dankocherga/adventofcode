(ns adventofcode.2019-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode.2019.d1 :as d1]
            [adventofcode.2019.d2 :as d2]
            ))

(def input-d1
  (map read-string
       (str/split-lines
         (slurp "resources/2019/d1_input.txt"))))

(deftest d1-part1
  (is (= 3178783 (d1/part1 input-d1))))

(deftest d1-part2
  (is (= 4765294 (d1/part2 input-d1))))

(def input-d2
  (mapv read-string
        (str/split
          (slurp "resources/2019/d2_input.txt"), #",")))

(deftest d2-part1
  (is (= 2890696 (d2/part1 input-d2))))

(deftest d2-part1
  (is (= 8226 (d2/part2 input-d2 19690720))))
