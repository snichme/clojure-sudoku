(ns sudoku
  (:require [clojure.set :as set]))

(def board identity)

(def all-values #{1 2 3 4 5 6 7 8 9})

; Write the function (value-at board coordinates) that
; returns the value at coordinate in board:
;
; (value-at sudoku-board [0 1]) ;=> 3
; (value-at sudoku-board [0 0]) ;=> 5
(defn value-at [board coord]
  (let [y (first coord)
        x (second coord)]
    (get (get board y) x)))

; Write the function (has-value? board coordinates)
; that returns false if the square at coordinates is empty (has 0),
; and otherwise true.
;
; (has-value? sudoku-board [0 0]) ;=> true
; (has-value? sudoku-board [0 2]) ;=> false
(defn has-value? [board coord]
  (not (zero? (value-at board coord))))

; Write the function (row-values board coordinates) that returns
; a set with all numbers on the row of the coordinates
; Remember that you can use destructing inside the parameter
; vector to get the row.
;
; (row-values sudoku-board [0 2]) ;=> #{0 5 3 7}
; (row-values sudoku-board [3 2]) ;=> #{0 8 6 3}
(defn row-values [board [y _]]
  (set (get board y)))

; Write the function (col-values board coordinates) that returns
; a set with all numbers on the col of the coordinates
;
; (col-values sudoku-board [0 2]) ;=> #{0 8}
; (col-values sudoku-board [4 8]) ;=> #{3 1 6 0 5 9}
(defn col-values [board [_ x]]
  (set (map #(get % x) board)))

; Write the function (coord-pairs coord-sequence) that returns all
; coordinate-pairs of the form [row col] where row is from coord-sequence
; and col is from coord-sequence.
;
; (coord-pairs [0 1])   ;=> [[0 0] [0 1]
;                       ;    [1 0] [1 1]];
;
; (coord-pairs [0 1 2]) ;=> [[0 0] [0 1] [0 2]
;                       ;    [1 0] [1 1] [1 2]
;                       ;    [2 0] [2 1] [2 2]]
(defn coord-pairs [coords]
  (for [i coords
        j coords]
          [i j]))

; Write the function (block-values board coordinates) that returns a set
; with all numbers in the block of coordinates.
; You might want to write a helper function that returns the coordinates
; for the top left corner of the block.
;
; (block-values sudoku-board [0 2]) ;=> #{0 5 3 6 8 9}
; (block-values sudoku-board [4 5]) ;=> #{0 6 8 3 2}
(defn get-top-left-coordinates [coord]
  (let [y (first coord)
        x (second coord)]
          [(- y (mod y 3)) (- x (mod x 3))]))

(defn block-values [board coord]
  (set
    (let [top-left (get-top-left-coordinates coord)]
      (for [i (range (first top-left) (+ (first top-left) 3))
            j (range (second top-left) (+ (second top-left) 3))]
                (value-at board [i j])))))

; Write the function (valid-values-for board coordinates) that returns a
; set with all valid numbers for the square at coordinates.
; If the square at coordinates already has a value, valid-values should
; return the empty set #{}.
; Remember that we already defined the set all-values.
;
; (valid-values-for sudoku-board [0 0]) ;=> #{}
; (valid-values-for sudoku-board [0 2]) ;=> #{1 2 4})
(defn valid-values-for [board coord]
  (if (has-value? board coord)
    #{}
    (set/difference all-values (row-values board coord) (col-values board coord) (block-values board coord))
  )
)

; Write the function (filled? board) which returns true if there are no
; empty squares in board, and otherwise false.
; It might help to write a helper function that returns all numbers of
; the board in a sequence.
; Remember that (contains? set element) can be used to check if element
; is in set.
;
; (filled? sudoku-board) ;=> false
; (filled? solved-board) ;=> true
(defn get-all-numbers-in-a-sequence [board]
  (apply set/union (map #(set %) board)))

(defn filled? [board]
  (not (contains? (get-all-numbers-in-a-sequence board) 0))
)

; Write the function (rows board) that returns a sequence of value sets
; for each row of board. That is, the first set in (rows board) is a set
; that has every element of the first row of board as element and so on.
;
; (rows sudoku-board) ;=> [#{5 3 0 7}
;                     ;    #{6 0 1 9 5}
;                     ;    #{0 9 8 6}
;                     ;    #{8 0 6 3}
;                     ;    #{4 0 8 3 1}
;                     ;    #{7 0 2 6}
;                     ;    #{0 6 2 8}
;                     ;    #{0 4 1 9 5}
;                     ;    #{0 8 7 9}]
;
; (rows solved-board) ;=> [#{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}]
(defn rows [board]
  nil)

; Write the function (valid-rows? board) that returns true if every row
; in board is a valid filled row.
;
; (valid-rows? solved-board)  ;=> truthy
; (valid-rows? invalid-board) ;=> falsey
(defn valid-rows? [board]
  nil)

; Write the function (cols board) that returns the values of each column
; in board as a sequence of sets.
;
; (cols sudoku-board) ;=> [#{5 6 0 8 4 7}
;                     ;    #{3 0 9 6}
;                     ;    #{0 8}
;                     ;    #{0 1 8 4}
;                     ;    #{7 9 0 6 2 1 8}
;                     ;    #{0 5 3 9}
;                     ;    #{0 2}
;                     ;    #{0 6 8 7}
;                     ;    #{0 3 1 6 5 9}]
;
; (cols solved-board) ;=> [#{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}
;                     ;    #{1 2 3 4 5 6 7 8 9}]
(defn cols [board]
  nil)

; Write the function (valid-cols? board) that returns true if every row
; in board is a valid filled column.
;
; (valid-cols? solved-board)  ;=> truthy
; (valid-cols? invalid-board) ;=> falsey
(defn valid-cols? [board]
  nil)

; Write the function (blocks board) that returns the values of each block
; in board as a sequence of sets.
;
; (blocks sudoku-board) ;=> [#{5 3 0 6 9 8}
;                       ;    #{0 7 1 9 5}
;                       ;    #{0 6}
;                       ;    #{8 0 4 7}
;                       ;    #{0 6 8 3 2}
;                       ;    #{0 3 1 6}
;                       ;    #{0 6}
;                       ;    #{0 4 1 9 8}
;                       ;    #{2 8 0 5 7 9}]
;
; (blocks solved-board) ;=> [#{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}
;                       ;    #{1 2 3 4 5 6 7 8 9}])
(defn blocks [board]
  nil)

; Write the function (valid-blocks? board) that returns true if every
; block in board is a valid filled block.
;
; (valid-blocks? solved-board)  ;=> truthy
; (valid-blocks? invalid-board) ;=> falsey
(defn valid-blocks? [board]
  nil)

; Write the function (valid-solution? board) that returns true if board
; is a valid solution to sudoku.
;
; (valid-solution? solved-board)  ;=> truthy
; (valid-solution? invalid-board) ;=> falsey)
(defn valid-solution? [board]
  nil)

; Write the function (set-value-at board coord new-value) that changes
; the value at coord in board to new-value.
;
; (def before-change
;   (board [[5 3 0 0 7 0 0 0 0]
;           [6 0 0 1 9 5 0 0 0]
;           [0 9 8 0 0 0 0 6 0]
;           [8 0 0 0 6 0 0 0 3]
;           [4 0 0 8 0 3 0 0 1]
;           [7 0 0 0 2 0 0 0 6]
;           [0 6 0 0 0 0 2 8 0]
;           [0 0 0 4 1 9 0 0 5]
;           [0 0 0 0 8 0 0 7 9]]))
;
; (def after-change
;   (board [[5 3 0 0 7 0 0 0 0]
;           [6 0 0 1 9 5 0 0 0]
;           [0 4 8 0 0 0 0 6 0]
;           [8 0 0 0 6 0 0 0 3]
;           [4 0 0 8 0 3 0 0 1]
;           [7 0 0 0 2 0 0 0 6]
;           [0 6 0 0 0 0 2 8 0]
;           [0 0 0 4 1 9 0 0 5]
;           [0 0 0 0 8 0 0 7 9]]))
;
; (set-value-at before-change [2 1] 4)
(defn set-value-at [board coord new-value]
  nil)

; Write the function (find-empty-point board) that returns coordinates
; to an empty point (that is, in our representation has value 0).
(defn find-empty-point [board]
  nil)


(defn solve [board]
  (if (filled? board)
    (if (valid-solution? board) board '[])
    (let [coord (find-empty-point board)
          values (valid-values-for board coord)]
      (mapcat #(solve (set-value-at board coord %)) values))))
