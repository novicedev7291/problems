package com.kuldeep.data.structure;

import java.util.Arrays;

public class BITOffline {
    public static void main(String[] args) {
        int[] a = { 7, 3, 9, 13, 5, 4 };
        int[][] q = {{ 1, 4 }, { 2, 6 }};
        int[] k = { 6, 8 };
        MaxElementsRange o = new MaxElementsRange();
        o.solve(a, q, k);
    }
}

class MaxElementsRange{
    class Node{
        int val;
        int pos;
        int l;
        int r;

        public Node(int val, int pos, int l, int r) {
            this.val = val;
            this.pos = pos;
            this.l = l;
            this.r = r;
        }
    }

    int[] BIT;
    public void solve(int[] a, int[][] q, int[] k){
        int N = a.length;
        int Q = q.length;

        Node[] nodes = new Node[N+Q+1];

        for(int i = 1; i <= N; ++i){
            Node n = new Node(a[i-1], 0, 0, i);
            nodes[i] = n;
        }

        for(int i = N + 1; i <= N+Q; ++i){
            Node n = new Node(k[i - N - 1], i - N, q[i-N-1][0], q[i-N-1][1]);
            nodes[i] = n;
        }
        
        Arrays.sort(nodes, 1, N+Q+1 ,(Node o1, Node o2) -> {
                if(o1.val == o2.val)
                    return o1.l < o2.l ? 1 : -1;
                return o1.val < o2.val ? 1 : -1;
            }
        );

        BIT = new int[N+1];
        int[] ans = new int[Q+1];

        for(int i = 1; i <= N+Q; i++){
            if(nodes[i].pos != 0){
                int count = query(nodes[i].r) - query(nodes[i].l-1);
                ans[nodes[i].pos] = count;
            }
            else{
                update(nodes[i].r, N+1);
            }
        }

        for(int i = 1; i <= Q; i++){
            System.out.println(ans[i]);
        }

    }

    private void update(int idx, int N){
        while(idx < N){
            BIT[idx] += 1;
            idx += idx & (-idx);
        }
    }

    private int query(int idx){
        int sum = 0;
        while(idx > 0){
            sum += BIT[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }
}
