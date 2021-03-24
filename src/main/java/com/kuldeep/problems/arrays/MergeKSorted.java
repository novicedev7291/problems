package com.kuldeep.problems.arrays;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeKSorted {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] out = new int[n*k];
        int[][] a = new int[k][n];
        for(int i = 0; i < k; i++)
            for(int j = 0; j < n; j++)
                a[i][j] = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < k; j++){
                pq.offer(a[j][i]);
            }

        }

        Iterator<Integer> itr = pq.iterator();
        while(itr.hasNext()){
            System.out.printf("%d ", itr.next());
        }

    }
}
