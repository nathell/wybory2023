(ns wybory2023.dhondt
  (:require [wybory2023.parse :as parse]))

(defn max-by
  ([f x] x)
  ([f x y] (if (> (f x) (f y)) x y))
  ([f x y & more]
   (reduce (partial max-by f)
           (max-by f x y)
           more)))

(defn merge-sorted [f lists]
  (let [lists (filter seq lists)
        firsts (map first lists)]
    (when (seq firsts)
      (let [greatest (apply max-by f firsts)]
        (lazy-cat
         (filter #(= greatest %) firsts)
         (merge-sorted f
          (map (fn [l]
                 (if (= (first l) greatest)
                   (next l)
                   l))
               lists)))))))

(def sample-results
  {:ko 104883
   :pis 62574
   :lewica 32285
   :td 30244
   :konf 16942})

(defn dhondt [votes seats]
  (->>
   (for [[k n] votes]
     (take 20
           (map #(vector k (/ n %)) (iterate inc 1))))
   (merge-sorted second)
   (take seats)
   (map first)
   frequencies))

(def above-threshold
  #{29616 29624 29617 29625 29615 29621 29619 29643})

(def seats-per-region
  [12 8 14 12 13 15 12 12 10 9 12 8 14 10 9 10 9 12 20 12
   12 11 15 14 12 14 9 7 9 9 12 9 16 8 10 12 9 9 10 8 12])

(defn calculate [results]
  (map (fn [{:keys [region results]}]
         (let [seats (nth seats-per-region (dec region))
               input (for [{:keys [list votes id]} results]
                       [(:short-name (parse/committees id)) votes])]
           {:region region
            :seats (dhondt input seats)}))
       results))

(defn total [results]
  (apply merge-with + (map :seats results)))
