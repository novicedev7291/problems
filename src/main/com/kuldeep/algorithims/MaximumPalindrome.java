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

    private long power(long x, int y){
        if(y == 0) return 1;
        if(x == 1) return 1;
        long res = 1;
        while(y > 0){
            if((y & 1) != 0)
                res = (res * x) % MOD;
            y = y >> 1;
            x = (x * x) % MOD;
        }
        return res;
    }

    private long calculate(int l, int r){
        int sum = 0;
        long res = 1;
        int odd = 0;
        for(int i = 0; i < A; i++){
            int cur = count[r][i] - count[l-1][i];
            sum += cur / 2;
            res = (res * rFact[cur/2]) % MOD;
            if(cur % 2 == 1){
                odd++;
            }
        }
        res = ((res * Math.max(1, odd)) % MOD * fact[sum]) % MOD;
        return res;
    }

    public long maxPalindrome(String s, int l, int r){
        fact[0] = 1;
        rFact[0] = 1;
        int n = s.length();
        for(int i = 1; i < N; i++){
            fact[i] = fact[i-1] * i % MOD;
            rFact[i] = power(fact[i], MOD-2);
        }

        for(int i = 1; i <= n; i++){
            count[i][s.charAt(i)-'a'] += 1;
        }

        for(int i = 1; i < N; i++) {
            for (int j = 0; j < A; j++) {
                count[i][j] += count[i - 1][j];
            }
        }
        return calculate(l, r);
    }
}
