package com.kuldeep.problems;

public class StringEditDistance {

    public static void main(String[] args) {
        System.out.println(new StringEditDistance().editDistance("apples".toCharArray(), "apple".toCharArray()));
    }

    char[] a;
    char[] b;
    int nA;
    int nB;
    int[][] dp;
    int editDistance(char a[], char b[])
    {
        nA = a.length;
        nB = b.length;
        dp = new int[500][500];
        this.a = a;
        this.b = b;
        return editDisHelper(0,0);
    }

    int editDisHelper(int ai, int bi){
        if(ai >= nA && bi >= nB) return 0;
        if(bi >= nB && ai < nA) return dp[ai][bi] = nA - ai;
        else if(ai >= nA && bi < nB) return dp[ai][bi] = nB - bi;
        if(dp[ai][bi] != 0) return dp[ai][bi];
        if(a[ai] == b[bi]){
            return dp[ai][bi] = editDisHelper(ai+1, bi+1);
        }
        return dp[ai][bi] = Math.min(2+editDisHelper(ai+1, bi+1), 1+editDisHelper(ai+1, bi+1));
    }
}
