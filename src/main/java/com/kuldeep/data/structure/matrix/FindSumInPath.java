package com.kuldeep.data.structure.matrix;


public class FindSumInPath {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7,8,9}};
        System.out.println(new FindSumInPath().ifSumInPath(arr, 25, arr.length, arr[0].length, 0, 0));
    }

    public boolean ifSumInPath(int[][] arr, int sum, int rows,int cols, int i, int j){
        if(i == rows || j == cols) return false;
        if(i == rows - 1 && j == cols - 1 && arr[i][j] == sum) return true;
        boolean isPath1 = ifSumInPath(arr, sum - arr[i][j], rows, cols, i+1,j);
        boolean isPath2 = ifSumInPath(arr, sum - arr[i][j], rows, cols, i,j+1);
        return isPath1 || isPath2;
    }
}
