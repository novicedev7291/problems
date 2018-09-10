package com.kuldeep.problems.backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class FindinKisses {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- != 0){
            int rows = sc.nextInt();
            int cols = sc.nextInt();
            sc.nextLine();
            String word = sc.nextLine();
            char[][] arr = new char[rows][cols];
            for(int i = 0; i < rows; i++){
                String s = sc.nextLine();
                for(int j = 0; j < cols; j++){
                    arr[i][j] = s.charAt(j);
                }
            }
            System.out.println();
            System.out.printf("%s  -> %b", word, new FindinKisses().hasWord(arr, word.toCharArray()));
            System.out.println();
        }
    }

    char[][] grid;
    char[] word;
    int WLen, rows, cols;
    boolean hasWordHelper(int i, int j, int wi){
        if(wi == WLen) return true;
        if(i == rows || j == cols) return false;
        /*System.out.printf("Word length : %d, and index: %d", WLen, wi);
        System.out.println();
        System.out.printf("%d, %d, element: %c", i, j, grid[i][j]);
        System.out.println();*/
        if(wi < WLen && grid[i][j] == word[wi]){
            return hasWordHelper(i + 1, j, wi+1) || hasWordHelper(i, j+1, wi + 1);
        }

        if(wi == 0){
            return hasWordHelper(i+1, j, wi) || hasWordHelper(i, j+1, wi);
        }
        return false;
    }

    public boolean hasWord(char[][] grid, char[] word) {
        this.grid = grid;
        this.word = word;
        WLen = word.length;
        rows = grid.length;
        cols = grid[0].length;
        return hasWordHelper(0,0, 0);
    }
}
