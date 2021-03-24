package com.kuldeep.data.structure.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KthLargestElement {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int t = sc.nextInt();
        while(t-- != 0){
            int k = sc.nextInt();
            int n = sc.nextInt();
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, new MinPQComparator());
            int i = 0;
            while(i < n){
                pq.offer(sc.nextInt());
                /*if(i < k-1){
                    System.out.printf("%d ", -1);
                }
                else{
                    System.out.printf("%d ", pq.poll());
                }*/
                i++;
            }
            Integer[] arr = new Integer[n];
            System.out.println(Arrays.toString(pq.toArray(arr)));
            System.out.println();
        }
    }
}

class MinPQComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b){
        return a - b;
    }
}
