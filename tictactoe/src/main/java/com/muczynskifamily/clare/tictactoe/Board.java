package com.muczynskifamily.clare.tictactoe;

/**
 * The Board class models the game-board.
 */
final public class Board {
    // Named-constants for the dimensions

    public static final int TOTAL_NUMBER_OF_ROWS_OR_COLS = 3;

    /**
     * the 3x3 board: a board composes of ROWS-by-COLS Cell instances
     */
    Seed[][] cells;

    /**
     * the current seed's row and column -- the last played move
     */
    int currentRow, currentCol;

    /**
     * Constructor to initialize the game board
     */
    public Board() {
        cells = new Seed[TOTAL_NUMBER_OF_ROWS_OR_COLS][TOTAL_NUMBER_OF_ROWS_OR_COLS];  // allocate the array
        init(Seed.EMPTY);
    }

    /**
     * Initialize (or re-initialize) the contents of the game board
     *
     * @param value
     */
    public void init(Seed value) {
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS_OR_COLS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_ROWS_OR_COLS; ++col) {
                cells[row][col] = value;
            }
        }
    }

    /**
     * Return true if it is a draw (i.e., no more EMPTY cell)
     *
     * @return
     */
    public boolean isDraw() {
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS_OR_COLS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_ROWS_OR_COLS; ++col) {
                if (cells[row][col] == Seed.EMPTY) {
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
        return horizontalRowAllMatches(theSeed)
                || verticalColumnAllMatches(theSeed)
                || upperLeftToLowerRightDiagonal(theSeed)
                || upperRightToLowerLeftDiagonal(theSeed);
    }

    private boolean horizontalRowAllMatches(Seed theSeed) {
        return cells[currentRow][0] == theSeed // 3-in-the-row
                && cells[currentRow][1] == theSeed
                && cells[currentRow][2] == theSeed;
    }

    private boolean verticalColumnAllMatches(Seed theSeed) {
        return cells[0][currentCol] == theSeed // 3-in-the-column
                && cells[1][currentCol] == theSeed
                && cells[2][currentCol] == theSeed;
    }

    private boolean upperLeftToLowerRightDiagonal(Seed theSeed) {
        return cells[0][0] == theSeed
                && cells[1][1] == theSeed
                && cells[2][2] == theSeed;
    }

    /**
     * Paint itself
     *
     * @return text that can be printed with System.out.print
     */
    public String paint() {
        StringBuilder stringBuilder = new StringBuilder(1000);
        for (int row = 0; row < TOTAL_NUMBER_OF_ROWS_OR_COLS; ++row) {
            for (int col = 0; col < TOTAL_NUMBER_OF_ROWS_OR_COLS; ++col) {
                stringBuilder.append(" "
                        + cells[row][col].getCharacter() + " ");
                if (col < TOTAL_NUMBER_OF_ROWS_OR_COLS - 1) {
                    stringBuilder.append(VERTICAL_BAR);
                }
            }
            stringBuilder.append("\n");

            if (row < TOTAL_NUMBER_OF_ROWS_OR_COLS - 1) {
                stringBuilder.append(HORIZONTAL_BAR);
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    private static final String HORIZONTAL_BAR = "-----------";

    private static final String VERTICAL_BAR = "|";

    private boolean upperRightToLowerLeftDiagonal(Seed theSeed) {
        return cells[0][2] == theSeed
                && cells[1][1] == theSeed
                && cells[2][0] == theSeed;
    }
}
