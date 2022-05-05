(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))


;; test recursion
;; - seems like once recur is called, it starts at the previous `loop`
(defn recur_test
  [x]
  (println "first")
  (loop [y 0]
    (println "y=" y)
    (if (< 10 y) (println "done")
      (recur (inc y)))))
(recur_test 99)


(defn reduce_test
  [arr]
  (println "reducing: " arr)
  (reduce (fn [acc, n] (+ acc n)) arr)
)
(reduce_test [1 2 3])

;; same as reduce_test but using an anonmyous function
(defn reduce_test_anon
  [arr]
  (println "reducing: " arr)
  (reduce #(+ %1 %2) arr)
)
(reduce_test_anon [1 2 3])


;; Exercises from https://www.braveclojure.com/do-things/#Exercises

;; exercise 1
(str "Got this word" "hello buddy")
(vector 1 2 3)
(list 1 2 3)
(hash-map :a 1)
(hash-set :a :a 1 2)

;; exercise 2, write a function that takes anumber and adds 100 to it
;; using anon functions
(#(+ % 100) 10)
;; anon function and returning functions
(defn add-n
  [offset]
  #(+ % offset))
(def add100 (add-n 100))
(add100 20)

;; exercise 3, write a function, `dec-maker`, that works exactly liek the function `inc-maker` except with subtraction
(defn dec-maker
  [dec-by]
  #(- % dec-by))
;; tests exercise 3
(def dec9 (dec-maker 9))
(dec9 100)


;; exercise 4, write a function, `mapset`, that works like `map` except the return value is a set
(defn mapset
  [f coll]
  (set (map f coll)))
;; tests exercise 4
(mapset inc [1 1 2 2])

;; might be more performant compared to the previous function
(defn mapset2
  [f col]
  (map f (set col)))
(mapset2 inc [1 1 2 2])

(println "test")

;; exercise 5, Create a function that's similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry.
;; Instead of two eyes, arms, legs and so on, they have five

(defn repeater
  "returns a vector with the item repeated n times"
  [n item]
  (loop
    [counter n
     res []]
    (if (< counter 1) res (recur (- counter 1) (conj res item)))))
(repeater 2 "test")

;; special note, that `[]` is important in the `reduce`. otherwise we get an error
;; I think the reason why that throws an error is because of this (https://clojuredocs.org/clojure.core/reduce)
;;    > "If val is not supplied, returns the result of applying f to the first 2 items in coll, then applying f to that result and the 3rd item, etc."
;; Because `into` doesn't exist for String, but it does for vector
(defn radial-symetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final part] (into final (repeater 5 part))) [] asym-body-parts))
(println "exercise 5" (radial-symetrize-body-parts ["arm", "leg"]))



;; exercise 6, building on top of 5
(defn radial-symetrize-body-parts-n
  [asym-body-parts n]
  (reduce (fn [final part] (into final (repeater n part))) [] asym-body-parts)
)
(println "exercise 6" (radial-symetrize-body-parts-n ["arm", "leg"] 2))






