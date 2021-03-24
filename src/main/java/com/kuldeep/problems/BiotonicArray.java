package com.kuldeep.problems;

import java.util.Scanner;

public class BiotonicArray {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- != 0){
            int N = sc.nextInt();
            int[] arr = new int[N];

            for(int i = 0 ; i < arr.length; i++){
                arr[i] = sc.nextInt();
            }

            int Q = sc.nextInt();
            for(int i = 0; i < Q; i++){
                int num = sc.nextInt();
                boolean found = false;
                for(int j = 0, k = arr.length-1; i < k; j++, k--){
                    if(arr[j] == num){
                        System.out.printf("%s ", j);
                        found = true;
                        break;
                    }
                    else if(arr[k] == num){
                        System.out.printf("%s ", k);
                        found = true;
                        break;
                    }
                }
                if(!found) System.out.printf("%s ", -1);
            }
            System.out.println();
        }
    }
}
