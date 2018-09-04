package com.kuldeep.problems;

import java.util.Scanner;

public class FindingFrequencyUsingRecursion {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- != 0){
            int N = sc.nextInt();
            int[] arr = new int[N];
            for(int i = 0; i < N; i++){
                arr[i] = sc.nextInt();
            }
            int q = sc.nextInt();
            int[] queries = new int[q];
            for(int i = 0; i < q; i++){
                queries[i] = sc.nextInt();
            }

            for(int i =0 ; i < q; i++){
                System.out.printf("%s ", new FindingFrequencyUsingRecursion().count(arr, queries[i], 0));
            }
        }


    }

    public int count(int[] arr, int k, int i){
        if(i == arr.length) return 0;
        if(arr[i] == k)
            return 1 + count(arr, k, i+1);
        return count(arr, k, i+1);
    }
}
