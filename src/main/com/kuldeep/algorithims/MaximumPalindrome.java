package com.kuldeep.algorithims;

public class MaximumPalindrome {
    public static void main(String[] args) {

    }

    int N = 100001;
    int MOD = 1000000007;
    int A = 'z' - 'a' + 1;
    int[][] count = new int[N][A];
    long[] fact = new long[N];
    long[] rFact = new long[N];

    public long power(long x, int y){
        if(y == 0) return 1;
        if(x == 1) return 1;
        long res = 1;
        while(y > 0){
            if(y % 2 == 0)
                res = (res * x) % MOD;
            y = y >> 1;
            x = (x * x) % MOD;
        }
        return res;
    }

    public int maxPalindrome(String s, int l, int r){
        fact[0] = 1;
        rFact[0] = 1;
        int n = s.length();
        for(int i = 1; i < N; i++){
            fact[i] = fact[i-1] * i % MOD;
            rFact[i] = power(fact[i], MOD-2);
        }
        return 0;
    }
}
