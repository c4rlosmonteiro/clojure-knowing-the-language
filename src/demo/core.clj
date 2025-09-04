(ns demo.core
  (:gen-class))

;; Ensure that the SVG code is evaluated
(require 'utils.carlos-math)
;; Refer the namespace so that you don't have to use the
;; fully qualified name to reference svg functions
(refer 'utils.carlos-math)

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

; 11
; You can use map with multiples collections.
;  (map str ["a" "b" "c"] ["A" "B" "C"])
;  ; => ("aA" "bB" "cC")
; It’s as if map does the following:
; (list (str "a" "A") (str "b" "B") (str "c" "C"))
;
; Just be sure that your mapping
; function can take a number of arguments equal to the number of collections you’re passing to map.
(defn using-map-with-multiple-lists
  [l1 l2]
  (map str l1 l2))

; 12
; You can use a map to retrieve the value associated with a keyword from a collection of map.
; Because keywords can be used as functions, you can do this succinctly.
(defn extract-keys-of-list-or-vector-of-maps
  []
  (let [vector-of-maps [{:name "Bob" :age 20} {:name "Lucas" :age 18} {:name "Willian" :age 30}]]
        (map :name vector-of-maps)))

; 13 - take, drop
; take and drop both take two arguments: a number and a sequence. take
; returns the first n elements of the sequence, whereas drop returns the
;sequence with the first n elements removed
; (take 3 [1 2 3 4 5 6 7 8 9 10])
; => (1 2 3)
; (drop 3 [1 2 3 4 5 6 7 8 9 10])
; => (4 5 6 7 8 9 10)

; 14 - take-while, and drop-while
; Each takes a predicate function (a function whose return value is evaluated
;for truth or falsity) to determine when it should stop taking or dropping.
;
; (take-while #(< (:month %) 3) food-journal)

; 15 - filter and some
; Use filter to return all elements of a sequence that test true for a predicate
;function.
; (filter #(< (:human %) 5) food-journal)
;
; The some function does that, returning
;the first truthy value (any value that’s not false or nil) returned by a predi-
;cate function:
;(some #(> (:critter %)
;
; (some #(> (:critter %) 5) food-journal)
;; => nil
;(some #(> (:critter %) 3) food-journal)
;; => true

; 16 - sort and sort-by
;
; You can sort elements in ascending order with sort:
;(sort [3 1 2])
;; => (1 2 3)
;
; If your sorting needs are more complicated, you can use sort-by, which
;allows you to apply a function (sometimes called a key function) to the ele-
;ments of a sequence and use the values it returns to determine the sort
;order.
;
; (sort-by count ["aaa" "c" "bb"])
;; => ("c" "bb" "aaa")


; 17 - concat
;
; Finally, concat simply appends the members of one sequence to the end of
;another:
; (concat [1 2] [3 4])
;; => (1 2 3 4)


; 18 - Macros
;
; When is a macro that is part of clojure core
; Using (macroexpand  '(when (< 1 2) (str "Do something...")))
; Will will see (if (< 1 2) (do (str Do something...)))
;
; Macro definitions look much like function definitions. They have a name,
;an optional document string, an argument list, and a body. The body will
;almost always return a list. This makes sense because macros are a way of
;transforming a data structure into a form Clojure can evaluate, and Clojure
;uses lists to represent function calls, special form calls, and macro calls.
;
; (defmacro infix
;   "Use this macro when you pine for the notation of your childhood"
;   [infixed]
;   (list (second infixed) (first infixed) (last infixed)))
;
;  This macro rearranges a list into the correct order for infix notation.
;Here’s an example:
;
;  (infix (1 + 1))
;  ; => 2
;
; The single quote character ' is a reader macro for (quote x):
;
; If we add quote at the beginning, it returns an unevaluated data
;structure
; (quote (+ 1 2))
;  ; => (+ 1 2)
;
;
;
(defn is-less-than
  [a b]
  (when (< a b)
    (str "Do something...")))

; TODO FIXME
(defmacro ptln
  "Documentation"
  {:added "1.0"} ; I think this is the version of inclusion
  [a]
  (list 'println a))

; 19 - thread-last macro ->>
; In Clojure, ->> is a "thread-last" macro. It is used to make
; code more readable by chaining operations where the result of
; one operation becomes the last argument to the next operation.
(defn testing-thread-last-macro
  ""
  []
  (->> my-list
       (map #(str "[" % "]"))
       (apply str "Result: ")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!" " | " (ns-name *ns*) " | " (ns-interns *ns*))
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
  (println "10) Destructing a map wih let: " (destructing-with-let))
  (println "11) Map with multiples inputs, (a b c) and (1 2 3) equal to:" (using-map-with-multiple-lists '("a" "b" "c") '(1 2 3)))
  (println "12) Extracting keys from vector or list of maps using map function: " (extract-keys-of-list-or-vector-of-maps))
  (println "18) 1 < 2 = " (is-less-than 1 2) "2 < 1 = " (is-less-than 2 1) " | " (macroexpand  '(when (< 1 2) (str "Do something..."))))
  (println "19) " (testing-thread-last-macro))


  (println "??) The result of the sum-tow-number function " (sum-two-number 3 4))
  (println "??) " (ptln "=:)="))
  (println ">> " (ns-name *ns*) " | " (ns-interns *ns*) " | " (ns-map *ns*))
  )
