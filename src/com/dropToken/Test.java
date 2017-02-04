package com.dropToken;

public class Test {

    public static void main(String[] args){
        try{
            int column = Integer.valueOf("123456");
            if(column < 1 || column > 4){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println("Input is not valid! Please enter a valid command!");
        }
    }

}
