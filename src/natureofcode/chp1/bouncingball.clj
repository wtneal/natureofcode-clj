(ns natureofcode.chp1.bouncingball
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn check-inbounds [[x y]]
  [(if (or (> x (q/width)) (< x 0))
     -1 1)
   (if (or (> y (q/height)) (< y 0))
     -1 1)])

(defn draw-ball [[x y]]
  (q/ellipse x y 16 16))

(defn setup []
  {:location [100 100]
   :velocity [2.5 5]})

(defn update [state]
  (let [new-location (map + (:location state)
                            (:velocity state))
        new-velocity (map * (:velocity state)
                            (check-inbounds new-location))]
      {:location new-location
       :velocity new-velocity}))

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
