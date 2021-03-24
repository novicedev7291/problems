package com.kuldeep.problems.backtracking;

public class PrintNBitNumber {
    public static void main(String[] args) {
        new PrintNBitNumber().printNBitNumbersWithKBitsSet(4,2);
    }
    void printNBitNumbersWithKBitsSet(int n, int k) {
        if(k > n) return;
        char[] p = new char[n];
        for(int i = 0; i < n; i++){
            p[i] = '0';
        }
        printNBitNumberHelper(n, p, 0, k);
    }

    void printNBitNumberHelper(int n, char[] p, int pi, int k) {
        if(pi == n){
            for(int i = 0; i < n; i++){
                System.out.printf("%c", p[i]);
            }
            System.out.printf(" ");
            return;
        }

            if((n-pi) <= k){
                p[pi] = '1';
                printNBitNumberHelper(n, p, pi+1, k-1);
                p[pi] = '1';
            }else{
                p[pi] = '0';
                printNBitNumberHelper(n, p, pi+1, k);
                p[pi] = '0';
                if(k > 0){
                    p[pi] = '1';
                    printNBitNumberHelper(n, p, pi+1, k-1);
                    p[pi] = '0';
                }
            }
    }
}
