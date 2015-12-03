(ns rosetta-code.100-doors)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; non-optimized

(defn init [n] (vec (take n (repeat 0))))

(defn toggle [x] (mod (inc x) 2))

(defn toggle-doors [doors i]
  (mapv #(if (zero? (mod % i)) (toggle (doors %)) (doors %))
       (range (count doors))))

(defn main-helper [n]
  (loop [doors (init n), i 1]
    (if (= i n)
      doors
      (recur (toggle-doors doors i) (inc i)))))

(defn main-1 [n]
  (->> n
       main-helper
       (map-indexed #(when-not (zero? %2) %1))
       (remove nil?)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; optimized

(defn main-2 [n]
  (filter #(let [sqrt (Math/sqrt %)]
             (= (* 1.0 (int sqrt)) sqrt))
          (range n)))
