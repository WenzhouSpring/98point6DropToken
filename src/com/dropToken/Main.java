package com.dropToken;
import com.dropToken.Board;

import java.util.Scanner;


public class Main {

    // The column and row size for the board
    private static final int size = 4;

    public static void main(String[] args) {

        System.out.println("Welcome to Drop Token Game!");
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(size, size);
        while(true){

            String[] split = scanner.nextLine().split("\\s+");
            if(split.length != 1 && split.length != 2){
                System.out.println("Input is not valid! Please enter a valid command!");
                continue;
            }

            switch (split[0]){
                case "GET":
                    break;
                case "BOARD":
                    break;
                case "PUT":
                            try{
                                int column = Integer.valueOf(split[1]);
                                if(column < 1 || column > size){
                                    throw new Exception();
                                }
                            }catch (Exception e){
                                System.out.println("Input is not valid! Please enter a valid command!");
                                continue;
                            }
                    break;
                case "EXIT":
                    break;
                default:
                    System.out.println("Input is not valid! Please enter a valid command!");
                    continue;
            }





        }

    }





}
