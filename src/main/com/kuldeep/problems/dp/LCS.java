package com.kuldeep.problems.dp;

import java.util.Arrays;

/**
 * In given two strings find out the longest sequence of chars which are in both strings.
 */
public class LCS {
    char[] ga;
    char[] gb;
    int[][] dp;
    int lcsLen(char[] a, char[] b){
        ga = a;
        gb = b;
        dp = new int[a.length][b.length];
        for(int i = 0; i < a.length; i++){
            Arrays.fill(dp[i], 0);
        }
        return lcsLenHelper(0, 0);
    }

    int lcsLenHelper(int ai, int bi){
        if(ai == ga.length || bi == gb.length) return 0;

        if(dp[ai][bi] != 0) return dp[ai][bi];

        if(ga[ai] == gb[bi]){
            return dp[ai][bi] = 1 + lcsLenHelper(ai + 1, bi + 1);
        }
        return dp[ai][bi] = Math.max(lcsLenHelper(ai + 1, bi), lcsLenHelper(ai, bi + 1));
    }

    public static void main(String[] args) {
        String s1 = "pancakes";
        String s2 = "pcaves";
        System.out.println(new LCS().lcsLen(s1.toCharArray(), s2.toCharArray()));
    }
}
