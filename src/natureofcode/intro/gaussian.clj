(ns natureofcode.intro.gaussian
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random]))

(def R (Random.))

(defn setup []
  (q/background 255)
  {:sd 60
   :mean (/ (q/width) 2)
   :x (/ (q/width) 2)})

(defn update [state]
  (assoc state :x (-> (:sd state)
                      (* (.nextGaussian R))
                      (+ (:mean state))
                      (int))))

(defn draw [state]
  ;(println state)
  (q/no-stroke)
  (q/fill 0 10)
  (q/ellipse (:x state) 180 16 16))

(q/defsketch gaussian
  :title "Random Gaussian Distribution"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
