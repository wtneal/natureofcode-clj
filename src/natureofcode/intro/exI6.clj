(ns natureofcode.intro.exI6
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random]))

(defrecord Walker [location])
(def R (Random.))

(defn model [x]
  (* x x))

(defn monte-carlo [f]
  (loop []
  (let [r1 (rand 10)
        p (model r1)
        r2 (rand 10)]
    (if (< r2 p)
      r1
      (recur)))))

(defn step
  [[x y]]
  (let [step-size (monte-carlo model)]
    [(+ x (Math/round (q/random (- step-size) step-size)))
     (+ y (Math/round (q/random (- step-size) step-size)))]))

(defn setup []
  (q/background 255)
  {:w (Walker. [(/ (q/width) 2)
                (/ (q/height) 2)])})

(defn update [state]
  (-> state
      (update-in [:w :location] step)))

(defn draw [state]
  (q/stroke 0)
  (let [[x y] (:location (:w state))]
    (q/point x y)))

(q/defsketch exI6
  :title "Simulating a Random Walk"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
