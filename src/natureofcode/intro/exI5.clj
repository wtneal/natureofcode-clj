(ns natureofcode.intro.gaussian
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random]))

(defrecord Walker [location])
(def R (Random.))

(defn choice []
  (Math/round (- (rand 2) 1)))

(defn step
  [[x y]]
  (let [step-size (-> 5 ; std dev
                      (* (.nextGaussian R))
                      (+ 2))] ; mean
    [(+ x (* (choice) step-size))
     (+ y (* (choice) step-size))]))

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

(q/defsketch natureofcode
  :title "Simulating a Random Walk"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
