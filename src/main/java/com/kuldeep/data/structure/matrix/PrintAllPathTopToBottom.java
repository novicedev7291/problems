package com.kuldeep.data.structure.matrix;

public class PrintAllPathTopToBottom {
    void printAllMatrixPaths(int[][] m) {
        int[] path = new int[m.length+m[0].length-1];
        printHelper(m, path, 0, m.length, m[0].length, 0, 0);
    }

    void printHelper(int[][] arr, int[] path,int pi, int rows, int cols, int i, int j){
        if(i == rows || j == cols) return;
        if(i == rows - 1 && j == cols - 1){
            path[pi] = arr[i][j];
            printPath(path);
            return;
        }

        path[pi] = arr[i][j];
        printHelper(arr, path, pi+1, rows, cols, i + 1, j);
        printHelper(arr, path, pi+1, rows, cols, i, j + 1 );
    }

    private void printPath(int[] path) {
        for(int i = 0; i < path.length; i++){
            System.out.printf("%d ", path[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] m = {{1,2,3},{4,5,6}};
        new PrintAllPathTopToBottom().printAllMatrixPaths(m);
    }
}
