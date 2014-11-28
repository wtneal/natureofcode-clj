(ns natureofcode.intro.walker
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defrecord Walker [location])

(defn choice []
  (Math/round (- (rand 2) 1)))

(defn step [[x y]]
  [(+ x (choice))
   (+ y (choice))])

(defn setup []
  (q/background 255)
  {:w (Walker. [(/ (q/width) 2)
                (/ (q/height) 2)])})

(defn update [state]
  (let [w (:w state)]
    {:w (assoc w :location (step (:location w)))}))

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
