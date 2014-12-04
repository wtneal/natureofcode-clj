(ns natureofcode.base
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 255)
  {})

(defn update [state]
  state)

(defn draw [state]
  nil)

(q/defsketch base
  :title "Base"
  :size [500 500]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
