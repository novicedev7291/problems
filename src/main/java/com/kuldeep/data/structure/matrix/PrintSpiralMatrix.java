package com.kuldeep.data.structure.matrix;

public class PrintSpiralMatrix {
    public static void main(String[] args) {
        //int[][] arr = {{1,2,3,4},{4,5,6,7},{8,9,10,11},{12,13,14,15}};
        //int[][] arr = {{1,2,3},{4,5,6},{8,9,10},{12,13,14}};
        //int[][] arr = {{1,2,3},{4,5,6},{8,9,10}};
        int[][] arr = {{2,4,6,8,10}};
        //int[][] arr = {{1,2,3},{4,5,6}};
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length ; j++){
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
        new PrintSpiralMatrix().printSpirally(arr);
    }
    void printSpirally(int arr[][]) {
        int rows = arr.length;
        int cols = arr[0].length;
        int i = 0, j = 0, r = rows, c = cols, pi = 0;
        int[] spiral = new int[rows*cols];
        while (i < rows && j < cols){
            if(i == r - 1 && j == c - 1){
                spiral[pi++] = arr[i][j];
            }else
                pi = printHelper(arr, spiral,pi, i, j, r, c);
            i++;
            j++;
            r = r - 1;
            c = c - 1;
        }
        for(i = 0 ; i < spiral.length - 1; i++){
            System.out.printf("%d, ", spiral[i]);
        }
        System.out.printf("%d", spiral[spiral.length-1]);
        System.out.println();

    }

    int printHelper(int[][] arr,int[] spiral,int pi, int i, int j, int rows, int cols){
        int m = i, n = j;
        System.out.printf("%d, %d", rows, cols);
        System.out.println();
        while (n < cols-1 && pi < spiral.length){
            spiral[pi++] = arr[m][n];
            n++;
        }
        n = cols - 1;
        while(m < rows-1 && pi < spiral.length){
            spiral[pi++] = arr[m][n];
            m++;
        }
        m = rows - 1;
        while (n > j && pi < spiral.length){
            spiral[pi++] = arr[m][n];
            n--;
        }
        while (m > i && pi < spiral.length){
            spiral[pi++] = arr[m][n];
            m--;
        }

        return pi;
    }
}
