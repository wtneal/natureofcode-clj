(ns natureofcode.chp1.vector-multiplication
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:use [natureofcode.vectors]))

(defn setup []
  {})

(defn update [state]
  state)

(defn draw [state]
  (let [mouse [(q/mouse-x) (q/mouse-y)]
        center [(/ (q/width) 2)
                (/ (q/height) 2)]]
    (q/background 255)
    (apply q/translate center)
    ; Draw a line from the mouse to the center
    (apply (partial q/line 0 0)
           (vdiv (vsub mouse center)  3))))

(q/defsketch vector-multiplication
  :title "vector-multiplication"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
