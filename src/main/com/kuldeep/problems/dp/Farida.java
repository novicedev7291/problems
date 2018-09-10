package com.kuldeep.problems.dp;

import java.util.Scanner;

public class Farida {
    long[] arr;
    int N;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int j = 0;
        while(j < t){
            int N = sc.nextInt();
            long[] arr = new long[N];
            for(int i = 0; i < N; i++){
                arr[i] = sc.nextInt();
            }
            System.out.printf("Case %d: %d", j + 1, new Farida().maxCoins(arr));
            System.out.println();
            j++;
        }
    }

    long maxCoins(long[] arr){
        N = arr.length;
        this.arr = arr;
        return maxCoinsHelper(0);
    }

    long maxCoinsHelper(int index){
        if(index >= N) return 0;
        return  Math.max(arr[index] + maxCoinsHelper(index+2), maxCoinsHelper(index+1));
    }
}
