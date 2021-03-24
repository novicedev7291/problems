package com.kuldeep.data.structure.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintAllPathsInRatMaje {
    List<Character> path = new ArrayList<>();
    void printAllPaths(int arr[][]) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] s = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            Arrays.fill(s[i],0);
        }
        generateSolHelper(arr, s, 0, rows, cols, 0, 0);
    }

    void generateSolution(int[][] m, int[][] s) {
        generateSolHelper(m, s, 0,m.length, m[0].length, 0,0);
        System.out.println();
    }

    void generateSolHelper(int[][] m, int[][] s, int pi, int r, int c, int i, int j){
        if(i == r || j == c || i < 0 || j < 0 || m[i][j] == 0) {
            return;
        }

        if(i == r-1 && j == c-1 ) {
            printPath();
            return;
        }

        m[i][j] = 0;
        path.add(pi, 'D');
        generateSolHelper(m, s, pi + 1 , r, c, i+1, j);
        path.remove(pi);
        path.add(pi, 'R');
        generateSolHelper(m, s, pi + 1, r, c, i, j+1);
        path.remove(pi);
        path.add(pi, 'U');
        generateSolHelper(m, s, pi + 1, r, c, i-1, j);
        path.remove(pi);
        path.add(pi, 'L');
        generateSolHelper(m, s, pi + 1, r, c, i, j-1);
        m[i][j] = 1;
        path.remove(pi);
    }

    private void printPath() {
        for (Character c: path
             ) {
            System.out.printf("%c", c);
        }
        System.out.printf(" ");
    }

    public static void main(String[] args) {
        int[][] arr = {{1,0,0,0},{1,1,0,1},{0,1,0,0},{0,1,1,1}};
        new PrintAllPathsInRatMaje().printAllPaths(arr);
    }
}
