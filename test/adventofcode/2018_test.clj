(ns adventofcode.2018-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode.2018.d1 :as d1]))

(def input-d1
  (map #(Integer/parseInt %)
       (str/split-lines
         (slurp "resources/d1_input.txt"))))

(deftest d1-part1
  (is (= 490 (d1/part1 input-d1))))

(deftest d1-part2
  (is (= 70357 (d1/part2 input-d1))))
