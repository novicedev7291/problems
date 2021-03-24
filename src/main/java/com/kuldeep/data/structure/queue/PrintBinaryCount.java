package com.kuldeep.data.structure.queue;

import java.util.LinkedList;
import java.util.Queue;

public class PrintBinaryCount {
    public static void main(String[] args) {
        int n = 4, i = 1;
        Queue<Integer> q = new LinkedList<>();
        System.out.printf("%s " , 0);
        while(i <= n){
            if(q.isEmpty()){
                q.add(i);
            }
            else {
                int N = q.poll();
                System.out.printf("%s ", N);
                q.add(N * 10);
                q.add(N * 10 + 1);
            }
            i++;
        }
    }
}
