(ns natureofcode.intro.walker
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random]))

(defrecord Walker [location])

(defn normalize-mouse-pos
  "Translates the mouse position to a normal vector based on
  the position relative to a 9 quadrant grid"
  [[x y :as pos]
   [w h :as dim]]
  (vector (cond
            (< (/ x w) (/ 1 3)) -1
            (> (/ x w) (/ 2 3)) 1
            :else 0)
          (cond
            (< (/ y h) (/ 1 3)) -1
            (> (/ y h) (/ 2 3)) 1
            :else 0)))

(defn next-move []
  (let [r (rand 1)
        m-pos (normalize-mouse-pos [(q/mouse-x) (q/mouse-y)]
                                   [(q/width) (q/height)])]
    (cond (< r (/ 7 16)) m-pos
          (< r (/ 8 16)) [-1 -1]
          (< r (/ 9 16)) [-1 0]
          (< r (/ 10 16)) [-1 1]
          (< r (/ 11 16)) [1 -1]
          (< r (/ 12 16)) [1 0]
          (< r (/ 13 16)) [1 1]
          (< r (/ 14 16)) [0 -1]
          (< r (/ 15 16)) [0 1]
          :else [0 0])))

(defn step
  "Returns an updated position based on the random next-move"
  [[x y]]
  (let [[x2 y2] (next-move)]
    [(+ x x2)
     (+ y y2)]))

(defn setup []
  (q/background 255)
  {:w (Walker. [(/ (q/width) 2)
                (/ (q/height) 2)])})

(defn update [state]
  (update-in state [:w :location] step))

(defn draw [state]
  (q/stroke 0)
  (let [[x y] (:location (:w state))]
    (q/point x y)))

(q/defsketch natureofcode
  :title "Simulating a Random Walk"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
