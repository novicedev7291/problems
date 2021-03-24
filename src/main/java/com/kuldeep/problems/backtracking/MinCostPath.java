package com.kuldeep.problems.backtracking;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MinCostPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] a = new int[m][n];
            for(int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (sc.hasNextInt()) {
                        a[i][j] = sc.nextInt();
                    } else {
                        a[i][j] = 100;
                        sc.next();
                    }

                }
            }
            MinCostPath o = new MinCostPath();
            int q = sc.nextInt();
            while(q-- != 0){
                int r = sc.nextInt();
                int c = sc.nextInt();
                int k = sc.nextInt();
                o.minCost(a, m, n, r, c);
                int ans = o.minCostForK(k);
                if(ans == -10){
                    System.out.println("No more path available");
                }
                else if(ans == 100){
                    System.out.println("Obstacles");
                }else
                    System.out.println(ans);
            }
        }
    }

    public int minCostForK(int k){
        if(!pq.isEmpty()){
            if(k > pq.size()){
                return -10;
            }
            return pq.poll();
        }
        return -10;
    }

    int[][] a;
    int m,n;
    PriorityQueue<Integer> pq;
    void minCost(int[][] a, int m, int n, int r, int c){
        pq = new PriorityQueue<>();
        this.a = a;
        this.m = m;
        this.n = n;
        minCostHelper(0,0, r, c, 0);
    }

    public void minCostHelper(int i, int j, int r, int c, int sum){
        if(i >= m || j >= n) return;
        if(a[i][j] == 100) {
            pq.offer(100);
            return;
        }
        if(i == r && j == c){
            pq.offer(sum+a[i][j]);
            return;
        }
        minCostHelper(i, j+1, r, c,a[i][j] + sum);
        minCostHelper(i+1, j, r, c, sum + a[i][j]);
    }
}
