(ns natureofcode.chp1.magnitude
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
                (/ (q/height) 2)]
        mouse-from-center (vsub mouse center)]
    (q/background 255)
    ; Draw the magnitude bar
    (q/fill 0)
    (q/rect 0 0 (vmag mouse-from-center) 10)
    ; Draw the line from the center to the mouse
    (apply q/translate center)
    (apply (partial q/line 0 0) mouse-from-center)
    ))

(q/defsketch magnitude
  :title "vector magnitude"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
