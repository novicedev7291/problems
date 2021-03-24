package com.kuldeep.data.structure;

import java.util.Arrays;

public class SQRTDecomposition {
    private int[] arr;
    private int[] blockSum;
    int blockSize;
    private int N;

    public SQRTDecomposition(int[] arr){
        this.arr = arr;
        this.N = arr.length;
        computeBlockSum(arr);
    }

    public void update(int idx, int val){
        int k = idx / blockSize;
        blockSum[k] = blockSum[k] - arr[idx] + val;
        arr[idx] = val;
    }

    public int query(int l, int r) throws IllegalArgumentException{
        int ans = 0;
        if(l < 0 || r >= N) throw new IllegalArgumentException("Range doesn't lie in array");
        while(l < r && l % blockSize != 0){
            ans += arr[l];
            l++;
        }
        while(l+blockSize <= r){
            ans += blockSum[l/blockSize];
            l += blockSize;
        }
        while(l <= r){
            ans += arr[l];
            l++;
        }
        return ans;
    }

    private void computeBlockSum(int[] a){
        blockSize = (int) Math.sqrt(((double) N)) + 1;
        blockSum = new int[blockSize];
        int i = 0;
        int k = -1;
        while(i < N){
            if(i % blockSize == 0){
                k++;
            }
            blockSum[k] += arr[i];
            i++;
        }

    }

    public void print(){
        Arrays.stream(blockSum).forEach(v -> System.out.printf("%d ", v));
    }
}