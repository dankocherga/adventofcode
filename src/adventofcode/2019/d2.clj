(ns adventofcode.2019.d2)

(defn- run-instruction [fn intcode addr]
  (let [arg1     (get intcode (get intcode (+ addr 1)))
        arg2     (get intcode (get intcode (+ addr 2)))
        res-pos  (get intcode (+ addr 3)) ]
    (assoc intcode res-pos (fn arg1 arg2))))

(defn- exec-intcode [program]
  (loop 
    [ptr 0
     intcode program]
    (let [opcode (get intcode ptr)]
      (cond 
        (>= ptr (count intcode)) intcode
        (= opcode 99) intcode
        (= opcode 1) (recur (+ ptr 4) (run-instruction + intcode ptr))
        (= opcode 2) (recur (+ ptr 4) (run-instruction * intcode ptr))))))

(defn- exec-program [memory in1 in2]
  (get (exec-intcode (assoc (assoc memory 1 in1) 2 in2)) 0))

(defn part1 [input]
  (exec-program input 12 2))

(defn part2 [input answer]
  (first (for
    ; Fuck you and brutforce
    [noun (range 100)
     verb (range 100)
     :when (= answer (exec-program input noun verb))]
    (+ (* noun 100) verb))))
