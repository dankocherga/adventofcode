(ns adventofcode.2019-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode.2019.d1 :as d1]
            ))

(def input-d1
  (map read-string
       (str/split-lines
         (slurp "resources/2019/d1_input.txt"))))

(deftest d1-part1
  (is (= 3178783 (d1/part1 input-d1))))

(deftest d1-part2
  (is (= 4765294 (d1/part2 input-d1))))
