package com.kuldeep.data.structure;

import java.util.Arrays;

class SQRTDecomposition {
    private int[] arr;
    private int[] blockSum;
    private int N;

    SQRTDecomposition(int[] arr){
        this.arr = arr;
        this.N = arr.length;
        computeBlockSum(arr);
    }

    private void computeBlockSum(int[] a){
        int blockSize = (int) Math.sqrt(((double) N)) + 1;
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

public class BlockSumSQRTDecomp{
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        SQRTDecomposition o = new SQRTDecomposition(a);
        o.print();
    }
}
