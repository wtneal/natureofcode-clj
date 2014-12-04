(ns natureofcode.chp1.bouncingball
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn place-inbounds [[x y]]
  [(if (> (q/width) x 0)
     x
     (q/width))
   (if (> (q/height) y 0)
     y
     (q/height))])

(defn check-inbounds [[vx vy]
                      [x y]]
  (println x "," y ":" vx "," vy)
  [(if (>= (q/width) x 0)
     vx
     (- vx))
   (if (>= (q/height) y 0)
     vy
     (- vy))])

(defn draw-ball [[x y]]
  (q/ellipse x y 16 16))

(defn setup []
  {:location [100 100]
   :velocity [2.5 5]})

(defn update [state]
  (-> state
      (update-in [:location] #(map + % (:velocity state)))
      (update-in [:velocity] check-inbounds (:location state))
      (update-in [:location] place-inbounds)))

(defn draw [state]
  ;(println state)
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
