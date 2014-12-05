(ns natureofcode.vectors)

(defn vadd
  "Vector addition"
  [v1 v2]
  (map + v1 v2))

(defn vsub
  "Vector subtraction"
  [v1 v2]
  (map - v1 v2))

(defn vmult
  "Vector-scalar multiplication"
  [v s]
  (map #(* s %) v))

(defn vdiv
  "Vector-scalar division"
  [v s]
  (map #(/ % s) v))

(defn vmag
  "Vector magnitude"
  [[x y]]
  (Math/sqrt (+ (* x x) (* y y))))
