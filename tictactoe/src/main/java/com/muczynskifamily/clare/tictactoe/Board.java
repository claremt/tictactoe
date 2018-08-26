package com.muczynskifamily.clare.tictactoe;

/**
 * The Board class models the game-board.
 */
final public class Board {
    // Named-constants for the dimensions

    public static final int TOTAL_NUMBER_OF_ROWS = 3;
    public static final int TOTAL_NUMBER_OF_COLS = 3;

    /**
     * the 3x3 board: a board composes of ROWS-by-COLS Cell instances
     */
    Cell[][] cells;

    /**
     * the current seed's row and column -- the last played move
     */
    int currentRow, currentCol;

    /**
     * Constructor to initialize the game board
     */
    public Board() {
        cells = new Cell[TOTAL_NUMBER_OF_ROWS][TOTAL_NUMBER_OF_COLS];  // allocate the array
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_COLS; ++col) {
                cells[row][col] = new Cell(); // allocate element of the array
            }
        }
    }

    /**
     * Initialize (or re-initialize) the contents of the game board
     *
     * @param value
     */
    public void init(Seed value) {
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_COLS; ++col) {
                cells[row][col].content = value;
            }
        }
    }

    /**
     * Return true if it is a draw (i.e., no more EMPTY cell)
     *
     * @return
     */
    public boolean isDraw() {
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_COLS; ++col) {
                if (cells[row][col].content == Seed.EMPTY) {
                    return false; // an empty seed found, not a draw, exit
                }
            }
        }
        return true; // no empty cell, it's a draw
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     *
     * @param theSeed the player that just played
     * @return
     */
    public boolean hasWon(Seed theSeed) {
        return (cells[currentRow][0].content == theSeed // 3-in-the-row
                && cells[currentRow][1].content == theSeed
                && cells[currentRow][2].content == theSeed
                || cells[0][currentCol].content == theSeed // 3-in-the-column
                && cells[1][currentCol].content == theSeed
                && cells[2][currentCol].content == theSeed
                || currentRow == currentCol // 3-in-the-diagonal
                && cells[0][0].content == theSeed
                && cells[1][1].content == theSeed
                && cells[2][2].content == theSeed
                || currentRow + currentCol == 2 // 3-in-the-opposite-diagonal
                && cells[0][2].content == theSeed
                && cells[1][1].content == theSeed
                && cells[2][0].content == theSeed);
    }

    /**
     * Paint itself
     *
     * @return text that can be printed with System.out.print
     */
    public String paint() {
        StringBuilder stringBuilder = new StringBuilder(1000);
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_COLS; ++col) {
                stringBuilder.append(" " +
                        cells[row][col].content.getCharacter() + " ");
                if (col < TOTAL_NUMBER_OF_COLS - 1) {
                    stringBuilder.append(VERTICAL_BAR);
                }
            }
            stringBuilder.append("\n");
   
            if (row < TOTAL_NUMBER_OF_ROWS - 1) {
                stringBuilder.append(HORIZONTAL_BAR);
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    private static final String HORIZONTAL_BAR = "-----------";

    private static final String VERTICAL_BAR = "|";
}
