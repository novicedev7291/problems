package com.kuldeep.data.structure;

import java.util.Arrays;

public class BITTree {
    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 0, 0, 0};
        BITTree o = new BITTree(arr);
        System.out.println();
        System.out.println(o.querySum(5));
        o.update(2, 1);
        o.update(4, 1);
        o.printTree();
        //System.out.println(o.rangeSum(2, 5));
        //System.out.println(o.readSingle(4));
        //System.out.println(o.readFreqIndex(10));
    }

    private int[] tree;
    private int[] a;
    int N;

    public BITTree(int[] a){
        this.N = a.length + 1;
        this.a = new int[N];
        System.arraycopy(a, 0, this.a, 1, N-1);
        this.tree = new int[N];
        System.arraycopy(this.a, 1, tree, 1, N-1);
        buildTree();
    }

    private void buildTree(){
        for(int i = 1; i < N; i++){
            int idx = i + (i & -i);
            if(idx < N){
                tree[idx] += tree[i];
            }
        }
    }

    public void update(int idx, int val){
        while(idx < N){
            tree[idx] += val;
            idx += idx & - idx;
        }
    }

    public int querySum(int idx){
        int sum = 0;
        if(idx <= 0 || idx > N) return sum;
        sum += tree[idx];
        int idx2 = idx - (idx & -idx);
        while(idx2 > 0){
            sum += tree[idx2];
            idx2 = idx2 - (idx2 & -idx2);
        }
        return sum;
    }

    public int readSingle(int idx){
        int sum = tree[idx];
        if(idx > 0){
            int y = idx - 1;
            int z = idx - (idx & -idx);
            while(y != z){
                sum -= tree[y];
                y = y - (y & -y);
            }
        }
        return sum;
        //return querySum(idx) - querySum(idx-1);
    }

    public int readFreqIndex(int cumFre){
        return readFreIdx(cumFre, (int) Math.pow(2, (int) (Math.log10(N-1) / Math.log10(2))));
    }

    private int readFreIdx(int cumFre, int bitMask){
        int index = 0;
        while(bitMask != 0){
            int tIdx = index + bitMask;
            bitMask = bitMask >> 1;
            if(tIdx >= N) continue;
            else if(cumFre == tree[tIdx]) return tIdx;
            else{
                index = tIdx;
                cumFre -= tree[tIdx];
            }
        }
        if(cumFre != 0) return -1;
        return index;
    }

    public int rangeSum(int fromIdx, int toIdx){
            return querySum(toIdx) - querySum(fromIdx-1);
    }

    public void printTree(){
        Arrays.stream(tree).forEach(i -> System.out.printf("%d ", i));
    }
}
