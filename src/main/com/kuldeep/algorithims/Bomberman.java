package com.kuldeep.algorithims;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Bomberman {

    // Complete the bomberMan function below.
    static String[] bomberMan(int n, String[] grid) {
        int p = grid.length;
        int q = grid[0].length();
        int[][] board = new int[p][q];
        int i = 0;
        for(String s: grid){
            for(int j = 0; j < q; j++){
                if(s.charAt(j) == '.'){
                    board[i][j] = -1;
                }
                else{
                    board[i][j] = 2;
                }
            }
            i++;
        }

        i = 1; //Seconds
        while(i < n){
            for(int k = 0; k < p; k++){
                for(int l = 0; l < q; l++){
                    if(board[k][l] == -1){
                        board[k][l] = 3;
                    }
                }
            }
            for(int k = 0; k < p; k++){
                for(int l = 0; l < q; l++){
                    if(board[k][l] == 1){
                        detonate(board, k, l);
                    }
                    if(board[k][l] > 1){
                        // reduce the time of bomb
                        board[k][l] -= 1;
                    }
                }
            }
            i++;
        }
        String[] ans = new String[p];
        for(int k = 0; k < p; k++){
            char[] row = new char[q];
            for(int l = 0; l < q; l++){
                if(board[k][l] == -1){
                    row[l] = '.';
                }
                else{
                    row[l] = 'O';
                }
            }
            ans[k] = new String(row);
        }
        return ans;
    }

    private static void detonate(int[][] b, int i, int j){
        b[i][j] = -1;
        if(i-1 >= 0)
            b[i-1][j] = -1;
        if(i+1 < b.length)
            b[i+1][j] = -1;
        if(j-1 >= 0)
            b[i][j-1] = -1;
        if(j+1 < b[0].length)
            b[i][j+1] = -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/mario/bomberman.txt"));

        String[] rcn = scanner.nextLine().split(" ");

        int r = Integer.parseInt(rcn[0]);

        int c = Integer.parseInt(rcn[1]);

        int n = Integer.parseInt(rcn[2]);

        String[] grid = new String[r];

        for (int i = 0; i < r; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = bomberMan(n, grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

