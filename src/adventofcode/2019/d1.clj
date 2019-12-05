(ns adventofcode.2019.d1)

(defn- fuel-required [mass]
  (- (quot mass 3) 2))

(defn- fuel-required-with-fuel [mass]
  (reduce + (take-while pos? (rest (iterate fuel-required mass)))))

(defn part1 [input]
  (reduce + (map fuel-required input)))

(defn part2 [input]
  (reduce + (map fuel-required-with-fuel input)))
