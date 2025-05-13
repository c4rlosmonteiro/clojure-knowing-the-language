(ns demo.core
  (:gen-class))

; 0
(def my-number 1)
(def my-list '(1 2 3 4 5))
(def my-vector [1 2 3 4 5])
(def my-map {:name "Bob" :age 99 :details {:hobbies ["1" "2" "3"]}})
(def my-map-created-with-hash-map-function (hash-map :key1 "value1" :ley2 "value2"))
(def my-set #{"A" "B" "C"})
(def my-set-created-with-hash-set-function (hash-set "A" "B" "C"))

; 1
(defn sum-two-number
  "This function is receiving two numbers and will sum them"
  [a b]
  (+ a b))

; 2
;
; reduce
;
;Clojure also allows you to define variable-arity functions by including a
;rest parameter, as in “put the rest of these arguments in a list with the following
;name.” The rest parameter is indicated by an ampersand (&)
(defn sum-multiples-numbers
  "This functon will sum all received numbers"
  [& numbers]
  (reduce + numbers))

;3
; anonymous function with fn
(defn apply_to
  "This function will receive another function (testing anonymous) and apply to received value(s) "
  [func value]
  (func value))

; 4
(defn anonymous-function-can-have-name
  "This is a anonymous function with a name"
  []
  ((fn my_print_fuc [] "[:(]")))

; 5
; if
(defn number-is-greater-than
  [a b]
  (if (> a b)
    (str "Yes!")
    (str "No!")))

; 6
; Using nth to get a list element (you can use get)
(defn get-first-element-from-list
  "Get the first element of a list"
  [l]
  (let [fist-with-nth (nth l 0)]
    (str "first using get: " fist-with-nth)))

; 7
; For lists, conj will add an element in the first position
(defn add-element-to-list
  [l]
  (conj l 99))

; 8
;
(defn get-first-element-from-vector
  [v]
  (let [first-with-get (get v 0) second-with-nth (nth v 1)]
    (str "first using get: " first-with-get " and second using nth: " second-with-nth)))

; 9
; For vectors, conj will add an element in the after the last position
(defn add-element-to-vector
  [v]
  (conj v 99))

; 10
; You can destructuring when using let
(defn destructing-with-let
  "First-value will be associated with the first item of the map and the rest of items will be put in rest-of-values"
  []
  (let [[first-value & rest-of-values] my-map]
    (str first-value " ||| " rest-of-values)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println "1) The result of my sum is:" (sum-two-number 3 4))
  (println "2) The result of adding multiple numbers is:" (sum-multiples-numbers 5 3 4))
  (println "3.1) Testing apply to with one value: " (apply_to (fn [a] (str ">" a "<"))  "A"))
  (println "3.2) Testing apply to with a list of values: " (apply_to (fn [l] (reduce (fn [a b] (str a ", " b)) l)) '("A" "B" "C")))
  (println "4) Anonymous function with name: " (anonymous-function-can-have-name))
  (println "5) 5 > 1 = " (number-is-greater-than 5 1) "| 1 > 5" (number-is-greater-than 1 5))
  (println "6)" (get-first-element-from-list my-list))
  (println "7) After add an element to my list | Before " my-list " | After " (add-element-to-list my-list) " | Now " my-list)
  (println "8)" (get-first-element-from-vector my-vector))
  (println "9) After add an element to my vector | Before " my-vector " | After " (add-element-to-list my-vector) " | Now " my-vector)
  (println "10) Destructing a map wih let: " (destructing-with-let)))

