(ns adventofcode.2018-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode.2018.d1 :as d1]
            [adventofcode.2018.d2 :as d2]
            [adventofcode.2018.d3 :as d3]
            ))

(def input-d1
  (map read-string
       (str/split-lines
         (slurp "resources/d1_input.txt"))))

(def input-d2
  (str/split-lines
    (slurp "resources/d2_input.txt")))

(def input-d3
  (str/split-lines
    (slurp "resources/d3_input.txt")))

(deftest d1-part1
  (is (= 490 (d1/part1 input-d1))))

(deftest d1-part2
  (is (= 70357 (d1/part2 input-d1))))

(deftest d2-part1
  (is (= 7533 (d2/part1 input-d2))))

(deftest d2-part2
  (is (= ["mphcuasvrnjzzkbgdtqeoylva"] (d2/part2 input-d2))))

(deftest d3-ex
  (is (= [4 #{3}] (d3/solution ["#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4" "#3 @ 5,5: 2x2"]))))

(deftest d3
  (is (= [118539 #{1270}] (d3/solution input-d3))))

