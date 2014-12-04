(ns natureofcode.intro.randomdistribution
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random]))

(def R (Random.))

(defn setup []
  (q/background 255)
  {:random-counts (vec (repeat 20 3))})

(defn update [state]
  (update-in state [:random-counts (.nextInt R 20)] inc)) ; Randomly select the index to inc

(defn draw [state]
  (q/stroke 0)
  (q/fill 175)
  (let [w (/ (q/width)
             (count (:random-counts state)))
        rc (:random-counts state)]
    ; Draw each of the values in the vector
    (dotimes [idx (count rc)]
      (q/rect (* idx w)
              (- (q/height) (rc idx))
              (dec w)
              (rc idx)))))

(q/defsketch natureofcode
  :title "Random Number Distribution"
  :size [640 240]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
