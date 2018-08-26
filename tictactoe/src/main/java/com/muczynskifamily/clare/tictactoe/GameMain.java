package com.muczynskifamily.clare.tictactoe;

import java.util.Scanner;

/**
 * The main class for the Tic-Tac-Toe (Console-OO, non-graphics version) It acts
 * as the overall controller of the game.
 */
public final class GameMain {

    private final Board board;            // the game board
    private GameState currentState; // the current state of the game (of enum GameState)
    private Seed currentPlayer;     // the current player (of enum Seed)

    private final static Scanner IN = new Scanner(System.in);  // input Scanner

    /**
     * Constructor to setup the game
     */
    public GameMain() {
        board = new Board();  // allocate game-board

        // Initialize the game-board and current status
        initGame();
    }

    private void playOneGame() {
        initGame();

        // Play the game once. Players CROSS and NOUGHT move alternately.
        do {
            playerMove(currentPlayer); // update the content, currentRow and currentCol

            System.out.println(board.paint());

            updateGame(currentPlayer); // update currentState

            if (null != currentState) // Print message if game-over
            {
                switch (currentState) {
                    case CROSS_WON:
                        System.out.println("'X' won! Bye!");
                        break;

                    case NOUGHT_WON:
                        System.out.println("'O' won! Bye!");
                        break;

                    case DRAW:
                        System.out.println("It's Draw! Bye!");
                        break;
                    default:
                        // keep playing
                        break;
                }
            }

            // Switch player
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;

        } while (currentState == GameState.PLAYING);  // repeat until game-over

    }

    /**
     * Initialize the game-board contents and the current states
     */
    public void initGame() {
        /* clear the board contents */
        board.init(Seed.EMPTY);

        /**
         * IMPORTANT: Is it correct that the game starts with X ? Inquiring
         * minds want to know. How can we find out? Should we just ask the
         * people whether it should be X or O who starts?
         */
        /* CROSS plays first */
        currentPlayer = Seed.CROSS;

        /* ready to play */
        currentState = GameState.PLAYING;
    }

    /**
     * The player with "theSeed" makes one move, with input validation. Update
     * Cell's content, Board's currentRow and currentCol.
     *
     * @param theSeed
     */
    public void playerMove(Seed theSeed) {
        boolean validInput = false;  // for validating input
        do {
            char playerCharacter = theSeed.getCharacter();
            System.out.print("Player '" + playerCharacter + "', enter your move (row[1-3] column[1-3]): ");

            int row = IN.nextInt() - 1;
            int col = IN.nextInt() - 1;

            if (validIndex(row) && validIndex(col)) {
                if (board.cells[row][col].content == Seed.EMPTY) {

                    board.cells[row][col].content = theSeed;
                    board.currentRow = row;
                    board.currentCol = col;
                    validInput = true; // input okay, exit loop
                }
            }

            if (!validInput) {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);   // repeat until input is valid
    }

    private boolean validIndex(int row) {
        return row >= 0 && row < Board.TOTAL_NUMBER_OF_ROWS;
    }

    /**
     * Update the currentState after the player with "theSeed" has moved
     *
     * @param theSeed
     */
    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {  // check for win
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {  // check for draw
            currentState = GameState.DRAW;
        }

        // Otherwise, no change to current state (still GameState.PLAYING).
    }

    /**
     * The entry main() method
     *
     * @param args the usual text from the command line. Not used yet.
     */
    public static void main(String[] args) {
        final GameMain gameMain = new GameMain();
        gameMain.repeatLoop();
    }

    /**
     * keep asking them if they want to play another game
     */
    private void repeatLoop() {
        boolean keepPlaying = true;
        while (keepPlaying) {
            playOneGame();
            keepPlaying = askPlayAgain();
        }

    }

    /**
     * Ask if they want to play again.
     *
     * @return true for yes and false for no "
     */
    private boolean askPlayAgain() {
        System.out.print("You want to play again, right? ");
        String text = IN.next();
        return (text.toUpperCase().contains("Y"));
    }

}
