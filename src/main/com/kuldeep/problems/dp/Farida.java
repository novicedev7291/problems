package com.kuldeep.problems.dp;

import java.util.Scanner;

public class Farida {
    long[] arr;
    int N;
    long[] dp;
    public long maxCoins(long[] arr){
        N = arr.length;
        this.arr = arr;
        dp = new long[N];
        for(int i = 0; i < N; i++){
            dp[i] = 0;
        }
        return maxCoinsHelper(0);
    }

    private long maxCoinsHelper(int i) {
        if(i >= N) return 0;

        if(dp[i] != 0) return dp[i];

        return dp[i] = Math.max(arr[i] + maxCoinsHelper(i+2), maxCoinsHelper(i+1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int j = 1;
        while(j <= t){
            int N = sc.nextInt();
            long[] arr = new long[N];
            for(int i = 0; i < N; i++){
                arr[i] = sc.nextLong();
            }
            System.out.printf("Case %d: %d", j, new Farida().maxCoins(arr));
            System.out.println();
            j++;
        }
    }


}
