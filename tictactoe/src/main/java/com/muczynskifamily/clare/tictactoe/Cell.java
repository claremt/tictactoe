package com.muczynskifamily.clare.tictactoe;

/**
 * The Cell class models each individual cell of the game board.
 */
public class Cell {  // save as Cell.java
    // package access

    Seed content; // content of this cell of type Seed.
    // take a value of Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT

    /**
     * Constructor to initialize this cell
     */
    public Cell() {
        clear();  // clear content
    }

    /**
     * Clear the cell content to EMPTY
     */
    public void clear() {
        content = Seed.EMPTY;
    }

}