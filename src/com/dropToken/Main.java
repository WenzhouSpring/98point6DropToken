package com.dropToken;

import java.util.Vector;
import java.util.List;
import java.util.Scanner;


public class Main {

    // The column and row size for the board
    private static final int size = 4;

    public static void main(String[] args) {

        System.out.println("Welcome to Drop Token Game!");
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(size, size);
        List<Integer> listOfPut = new Vector<>();
        int player = 1;

        while (true) {

            String[] split = scanner.nextLine().split("\\s+");
            if (split.length != 1 && split.length != 2) {
                System.out.println("Input is not valid! Please enter a valid command!");
                continue;
            }

            switch (split[0]) {
                case "GET":
                    if (board.isBoardEmpty()) {
                        continue;
                    }
                    listOfPut.forEach(System.out::println);
                    continue;
                case "BOARD":
                    board.printBoard();
                    continue;
                case "PUT":
                    int column;
                    try {
                        column = Integer.valueOf(split[1]);
                        if (column < 1 || column > size) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Input is not valid! Please enter a valid command!");
                        continue;
                    }

                    if (board.isColumnFull(column)) {
                        System.out.println("ERROR");
                        continue;
                    }

                    listOfPut.add(column);
                    if (board.insertAndCheck(column, player)) {
                        System.out.println("WIN");
                    } else if (board.isBoardFull()) {
                        System.out.println("DRAW");
                    } else {
                        System.out.println("OK");
                    }

                    player = -player;

                    continue;
                case "EXIT":
                    System.exit(0);
                default:
                    System.out.println("Input is not valid! Please enter a valid command!");
            }
        }

    }


}
