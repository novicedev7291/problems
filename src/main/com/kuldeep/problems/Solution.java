package com.kuldeep.problems;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0){
            int N = sc.nextInt();
            int[] a = new int[N];
            for(int i = 0; i < N; i++){
                a[i] = sc.nextInt();
            }

            System.out.println(new Solution().minSteps(a, N));

        }
    }

    public int minSteps(int[] a, int N){
        return minStepsH(a, N, 0);
    }

    public int minStepsH(int[] a, int N, int i){
        if(i >= N) return -1;
        if(i < N && i+a[i] < N && a[i+1] > a[i+ a[i]]){
            return 1 + minStepsH(a, N, i+1);
        }
        return 1 + minStepsH(a, N, i + a[i]);
    }


}