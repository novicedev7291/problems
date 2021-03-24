package com.kuldeep.problems.backtracking;

import java.util.Scanner;

public class MarcusAndIEHOVA {
    void printPath(char[][] arr, int i, int j){
        int rows = arr.length;
        int cols = arr[0].length;
        printPathHelper(arr, "IEHOVA", "", 0, rows, cols, i, j);
    }

    void printPathHelper(char[][] arr, String command, String step, int ci, int rows, int cols, int i, int j){
        if(i == rows || j == cols || i < 0 || j < 0) return;
        if(arr[i][j] == '#' && ci == command.length()) {
            System.out.printf("%s ", step);
            return;
        }

        if(ci < command.length() && command.charAt(ci) == arr[i][j]){
            System.out.printf("%s ", step);
            printPathHelper(arr, command, "forth", ci + 1, rows, cols, i - 1, j);
            printPathHelper(arr, command, "right", ci + 1, rows, cols, i, j + 1);
            printPathHelper(arr, command, "left", ci + 1, rows, cols, i , j - 1);
        }
        if (arr[i][j] == '@'){
            printPathHelper(arr, command, "forth", ci, rows, cols, i - 1, j);
            printPathHelper(arr, command, "right", ci, rows, cols, i, j + 1);
            printPathHelper(arr, command, "left", ci, rows, cols, i , j - 1);
        }
    }

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //char[][] arr = {{'P','S','T', '#', 'T'}, {'B', 'T', 'J', 'A', 'S'},
        //        {'T','Y', 'C', 'V', 'M'}, {'Y','E','H','O','F'},{'X','I','B','K','U'},{'N','@','R','J','B'}};
        //char[][] arr = {{'J','A','#', 'X'}, {'J', 'V', 'B', 'N'},
        //        {'X','O', 'H', 'D'}, {'D','Q','E','M'},{'T','@','I','Y'}};
        //new MarcusAndIEHOVA().printPath(arr, 4, 1);
        int t = sc.nextInt();
        while (t-- != 0){
            int rows = sc.nextInt();
            int cols = sc.nextInt();
            sc.nextLine();
            char[][] arr = new char[rows][cols];
            for(int i = 0; i < rows; i++){
                String s = sc.nextLine();
                for(int j = 0; j < cols; j++){
                    arr[i][j] = s.charAt(j);
                }
            }

            for(int i = rows - 1, j = 0 ; j < cols; j++){
                if(arr[i][j] == '@'){
                    new MarcusAndIEHOVA().printPath(arr, i, j);
                    System.out.println();
                    break;
                }
            }
        }
    }
}
