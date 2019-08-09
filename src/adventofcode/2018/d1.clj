(ns adventofcode.2018.d1)

(defn part1 [input]
  (reduce + input))

(defn part2 [input]
  (reduce 
    (fn [seen v] (if-not (get seen v) (conj seen v) (reduced v)))
    #{}
    (reductions + (cycle input))))
