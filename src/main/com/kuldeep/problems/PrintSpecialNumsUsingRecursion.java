package com.kuldeep.problems;

import java.util.Scanner;

public class PrintSpecialNumsUsingRecursion {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- != 0){
            int N = sc.nextInt();
            int[] arr = new int[N];
            for(int i = 0; i < N; i++){
                arr[i] = sc.nextInt();
            }
            new PrintSpecialNumsUsingRecursion().printSpecialNums(arr, 0);
        }
    }

    public void printSpecialNums(int[] arr, int i){
        if(i == arr.length) return;
        if((arr[i] % 10) == 0) {
            System.out.printf("%s ", arr[i]);
        }
        printSpecialNums(arr, i+1);
    }
}
