package com.kuldeep.data.structure.matrix;

public class FindingInSortedMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,6,8},{7, 11, 12}};
        new FindingInSortedMatrix().findNumberInSortedGrid(arr, 100);
    }

    void findNumberInSortedGrid(int[][] arr, int key){
        int rows = arr.length;
        int cols = 0;
        if(rows > 0){
            cols = arr[0].length;
        }

        int i = 0;
        int j = cols - 1;
        boolean found = false;

        while (i < rows && j > -1){
            if(key > arr[i][j]){
                i++;
            }else if(key < arr[i][j]){
                j--;
            }
            else{
                System.out.printf("%s %s", i, j);
                found = true;
                break;
            }
        }

        if(!found){
            System.out.printf("%s",-1);
        }
        System.out.println();
    }

    private boolean ifFoundPrintAndReturn(int[][] arr, int key, int rowId, int colIdStart, int colIdEnd) {
        if(colIdEnd < colIdStart) {
            return false;
        }
        int mid = colIdStart + (colIdEnd - colIdStart)/2;
        if(arr[rowId][mid] == key){
            System.out.printf("%s %s", rowId, mid);
            return true;
        }else if(key < arr[rowId][mid]){
            return ifFoundPrintAndReturn(arr, key, rowId, colIdStart, mid - 1);
        }
        return ifFoundPrintAndReturn(arr, key, rowId, mid + 1, colIdEnd);
    }
}
