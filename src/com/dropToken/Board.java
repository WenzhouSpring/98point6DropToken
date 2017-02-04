package com.dropToken;

class Board {

    private int rows;
    private int columns;
    private int[][] payloads;
    private int spaceOfTable;
    private int[] cursors;

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

    boolean isBoardFull() {
        return spaceOfTable == 0;
    }

    boolean isBoardEmpty() {
        return spaceOfTable == (rows * columns);
    }

    boolean isColumnFull(int column) {
        return cursors[column - 1] == 0;
    }

    boolean insertAndCheck(int column, int value) {
        int rowOfPayloads = cursors[column - 1] - 1;
        int columnOfPayloads = column - 1;
        payloads[rowOfPayloads][columnOfPayloads] = value;
        spaceOfTable--;
        cursors[column - 1]--;
        return checkWinning(rowOfPayloads, columnOfPayloads);
    }

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

        return(score == rows || score == -rows);

    }
}
