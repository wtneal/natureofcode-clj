(ns natureofcode.chp1.vector-subtraction
  (:require [quil.core :as q]
            [quil.middleware :as m]))

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
    (apply (partial q/line 0 0)
           (map - mouse center))))

(q/defsketch vector-subtraction
  :title "vector-subtraction"
  :size [640 360]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
