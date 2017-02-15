package com.dropToken;

/**
 * Board represents a grid with m rows and n columns,
 * which m and n depended on initial constructor input.
 * A Board is mainly a two dimensional int array,
 * which rows is the length of outer array, and column is the length of inner array.
 *
 * In addition, Board acts as model part in entire MVC design pattern
 */
class Board {

    // the number of rows of grid
    private int rows;
    // the number of columns of grid
    private int columns;
    // contents of grid
    private int[][] payloads;
    // volume or size of grid
    private int spaceOfTable;
    // the remaining space of each column can be filled with token
    private int[] cursors;

    /**
     *
     * Construct a new board
     *
     * @param rows the number of rows of board
     * @param columns the number of columns of board
     */
    Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.payloads = new int[rows][columns];
        this.spaceOfTable = rows * columns;
        this.cursors = new int[rows];
        for (int i = 0; i < cursors.length; i++) {
            cursors[i] = rows;
        }
    }

    /**
     *
     * Check whether the board is full
     *
     * @return a boolean, b, such that b = true; if the board is full,
     * otherwise, the board is not full.
     */
    boolean isBoardFull() {
        return spaceOfTable == 0;
    }

    /**
     *
     * Check whether the board is empty
     *
     * @return a boolean, b, such that b = true; if the board is empty,
     * otherwise, the board is not empty.
     */
    boolean isBoardEmpty() {
        return spaceOfTable == (rows * columns);
    }

    /**
     *
     * Check whether the column is full.
     *
     * @param column the column to be checked
     * @return a boolean, b, such that b = true; if the column is full,
     * otherwise, the column is not full.
     */
    boolean isColumnFull(int column) {
        return cursors[column - 1] == 0;
    }

    /**
     *
     * drop a token into the board, and check the status of game.
     *
     * @param column the column that the token dropped along with
     * @param value the value of the token specified by player
     * @return a boolean, b, such that b = true; if the player wins,
     * otherwise, the player does not win.
     */
    boolean dropAndCheck(int column, int value) {
        int rowOfPayloads = cursors[column - 1] - 1;
        int columnOfPayloads = column - 1;
        payloads[rowOfPayloads][columnOfPayloads] = value;
        spaceOfTable--;
        cursors[column - 1]--;
        return checkWinning(rowOfPayloads, columnOfPayloads);
    }

    /**
     * Print the board
     */
    void printBoard() {
        // print out the contents of the array
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {

                int valueToPrint = payloads[i][j];
                if (valueToPrint == -1) {
                    valueToPrint = 2;
                }
                System.out.print(" " + valueToPrint);
            }
            System.out.println();
        }

        // print out the cut-off line.
        System.out.print("+");
        for (int k = 0; k < columns * 2; k++) {
            System.out.print("-");
        }
        System.out.println();

        // print out the column number
        for (int q = 1; q <= columns; q++) {
            System.out.print(q + " ");
        }
        System.out.println();
    }

    /**
     *
     * Check the status of the board to see if there is a winner
     *
     * @param row the row that related to the dropped token
     * @param column the column that related to the dropped token
     * @return a boolean, b, such that b = true; if a player wins,
     * otherwise, there is no winning occurred.
     */
    private boolean checkWinning(int row, int column) {

        int score = 0;

        // check the diagonal if there is possible diagonal win for the slot
        if (((row == 0) && (column == 0)) || ((row == rows - 1) && (column == columns - 1))) {

            for (int i = 0; i < rows; i++) {
                score += payloads[i][i];
            }

            if (score == rows || score == -rows) {
                return true;
            }

            score = 0;

        }

        if (((row == 0) && (column == columns - 1)) || ((row == rows - 1) && (column == 0))) {

            for (int j = 0; j < rows; j++) {
                score += payloads[j][columns - j - 1];
            }

            if (score == rows || score == -rows) {
                return true;
            }

            score = 0;

        }

        // check the column
        for (int i = 0; i < rows; i++) {
            score += payloads[i][column];
        }

        if (score == rows || score == -rows) {
            return true;
        }

        score = 0;

        // check the row
        for (int i = 0; i < columns; i++) {
            score += payloads[row][i];
        }

        return (score == rows || score == -rows);

    }
}
