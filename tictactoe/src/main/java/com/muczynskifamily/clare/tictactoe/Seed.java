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
    },
    CROSS {
        @Override
        public char getCharacter() {
            return 'X';
        }
    },
    NOUGHT {
        @Override
        public char getCharacter() {
            return 'O';
        }
    };

    public abstract char getCharacter();
}
