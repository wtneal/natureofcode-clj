(ns natureofcode.chp1.bouncingball
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:import [java.util Random])
  (:use [natureofcode.vector]))

(def Rnd (Random.))
(defrecord Ball [location velocity])

(defn check-inbounds [[x y]]
  [(> (q/width) x 0) (> (q/height) y 0)])

(defn draw-ball [[x y]]
  (q/ellipse x y 16 16))

(defn update-ball [ball]
  (let [[x-in? y-in?] (check-inbounds (:location ball))]

    )
  (-> ball
      (assoc :location (map + (:location ball)
                              (:velocity ball)))))


(defn setup []
  {:ball (Ball. [(.nextInt Rnd (q/width))
                 (.nextInt Rnd (q/height))]
                [(- (.nextInt Rnd 5) 2)
                 (- (.nextInt Rnd 5) 2)])})

(defn update [state]
  (assoc state :ball (update-ball (:ball state))))

(defn draw [state]
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (draw-ball (:location state)))

(q/defsketch bouncingball
  :title "Bouncing ball with vectors"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
