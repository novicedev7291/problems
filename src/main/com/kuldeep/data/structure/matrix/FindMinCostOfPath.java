package com.kuldeep.data.structure.matrix;

import java.util.Arrays;

public class FindMinCostOfPath {
    public static void main(String[] args) {
        //int[][] arr = {{1,2,3},{4,-9,6},{7,8,9}};
        int[][] arr = {{5},{4},{2},{1}};
        System.out.println(new FindMinCostOfPath().minCostOfPath(arr, arr.length, arr[0].length, 0, 0));
    }

    public int minCostOfPath(int[][] arr, int rows, int cols, int i, int j){
        if(i == rows || j == cols) return Integer.MAX_VALUE;
        if(i == rows-1 && j == cols-1) return arr[i][j];
        int cost1 = minCostOfPath(arr, rows, cols, i, j+1);
        int costDiagonal = minCostOfPath(arr, rows, cols, i+1, j+1);
        int cost3 = minCostOfPath(arr, rows, cols, i+1, j);
        return arr[i][j] + Math.min(cost1, Math.min(costDiagonal, cost3));
    }
}
