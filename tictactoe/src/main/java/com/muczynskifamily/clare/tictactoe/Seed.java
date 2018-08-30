package com.muczynskifamily.clare.tictactoe;

/**
 * Enumerations for the seeds and cell contents
 */
public enum Seed {

    EMPTY {
        @Override
        public char getCharacter() {
            return ' ';
        }

        @Override
        public GameState getWinnerState() {
            return GameState.PLAYING;
        }
    },
    CROSS {
        @Override
        public char getCharacter() {
            return 'X';
        }

        @Override
        public GameState getWinnerState() {
            return GameState.CROSS_WON;
        }
    },
    NOUGHT {
        @Override
        public char getCharacter() {
            return 'O';
        }

        @Override
        public GameState getWinnerState() {
            return GameState.NOUGHT_WON;
        }
    };

    public abstract char getCharacter();

    public abstract GameState getWinnerState();
}
