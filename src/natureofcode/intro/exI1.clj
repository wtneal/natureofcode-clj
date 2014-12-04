(ns natureofcode.intro.exI1
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random]))

(defrecord Walker [location])

(def R (Random.))

(defn choice
  "Returns a random number between 0 and 8 with an additonal weight on 8"
  []
  (let [n (.nextInt R 10)]
    (cond
      (> n 8) 8
      :else n)))

(defn next-move
  "Normalizes The result of choice into a vector [x y]"
  []
  (let [n (choice)]
    [(-> n (rem 3) dec)
     (-> n (quot 3) dec)]))

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
