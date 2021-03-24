package com.kuldeep.problems.backtracking;

import java.util.Scanner;

public class FacebookWorm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] a = new int[r][c];
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                a[i][j] = sc.nextInt();

        System.out.println(new FacebookWorm().countSafe(a, r, c));
    }

    int[][] a;
    int r;
    int c;
    public int countSafe(int[][] a, int r, int c){
        this.a = a;
        this.r = r;
        this.c = c;
        countHelper(0,0);
        int count = 0;
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(a[i][j] == 1)
                    count+=1;
        return count;
    }

    void countHelper(int i, int j){
        if(i >= r || j >= c) return;
        if(a[i][j] == 3 || a[i][j] == -1){
            for(int k = i - 1; k <= i+1; k++){
                for(int l = j - 1; l <= j+1; l++){
                    if(k != i || l != j){
                        if(k >= 0 && l >=0 && k < r && l < c){
                            if(a[k][l] == 0) continue;
                            if(a[k][l] != 3 && a[k][l] != -1){
                                a[k][l] = -1;
                                countHelper(k, l);
                            }
                        }
                    }
                }
            }
        }
        countHelper(i+1, j);
        countHelper(i, j+1);
    }
}
