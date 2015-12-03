(ns rosetta-code.24-game
  (:require [clojure.string :as str]))

(defn main []
  (let [selected (take 4 (repeatedly #(rand-nth (range 1 10))))]
    (println "use the following 4 digits: " selected)
    (let [expr (read-line)]
      (if (and ;; check: only digits and operators
           (= expr (re-find #"[\d\s+-\\*/()]+" expr))
           ;; check: same digits as provided
           (let [digits (map read-string (re-seq #"\d" expr))]
             ;; check: count to account for duplicate digits
             (and (= (count digits) (count selected))
                  (= (into #{} digits)
                     (into #{} selected)))))
        (let [rslt (eval (read-string expr))]
          (if (= rslt 24)
            (println "Yay! You've won...")
            (println "Sorry! You've lost...")))
        (println "Use only the selected digits, and only +,-,*,\\ operators")))))
