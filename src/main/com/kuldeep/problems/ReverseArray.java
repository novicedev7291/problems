package com.kuldeep.problems;

import java.util.Objects;
import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        String[] arrItems = scanner.nextLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(arrItems[i]);
        }

        int[] rvArr = reverseArrayUsingMidMethod(arr, N);
        //int[] rvArr = reverseArray(arr, N);

        for (int i = 0 ; i < N; i++){
            System.out.print(rvArr[i] + " ");
        }
    }

    private static int[] reverseArray(int[] arr,int N) {
        // Solution1 straight: iterate over an array from last and put one element one by one in new array
        Objects.requireNonNull(arr);
        int[]  temp = new int[arr.length];
        int j = N - 1;
        for(int i = 0; i < N ; i++){
            temp[i] = arr[j--];
        }
        return temp;
    }

    private static int[] reverseArrayUsingMidMethod(int[] arr, int N){
        //Solution2 swap first with last until mid
        int mid = 0 + (N - 0)/2;
        for(int i = 0,j = N -1; i < j; i++, j-- ){
            swap2NNumbers(arr, i, j);
        }
        return arr;
    }

    private static void swap2NNumbers(int[] arr, int i, int j){
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
}
