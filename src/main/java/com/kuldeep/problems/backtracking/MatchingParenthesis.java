package com.kuldeep.problems.backtracking;

public class MatchingParenthesis {
    public static void main(String[] args) {
        new MatchingParenthesis().printParenthesis(3);
    }
    public void printParenthesis(int N){
        char[] p = new char[2*N];
        p[0] = '(';
        printParenthesisHelper(2*N, p, 1, 1);
    }

    void printParenthesisHelper(int N, char[] p, int i, int countSb) {
        if(i == N){
            for(int j = 0; j < N; j++){
                System.out.printf("%c",p[j]);
            }
            System.out.printf(" ");
            return;
        }

        if(countSb == N/2){
            p[i] = ')';
            printParenthesisHelper(N, p, i + 1, countSb);
            return;
        }

        p[i] = '(';
        printParenthesisHelper(N, p, i + 1, countSb + 1);

        if((countSb * 2) != i){
            p[i] = ')';
            printParenthesisHelper(N, p, i+1, countSb);
        }

    }
}
