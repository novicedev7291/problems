package com.kuldeep.problems.backtracking;

public class EqualHalvesInArray {
    int	len;
    boolean hasEqualSumPartitions(int arr[]) {
        len = arr.length;
        return hasSumPartitionHelper(arr, 0, 0, 0);
    }
    boolean hasSumPartitionHelper(int arr[], int idx, int fps, int sps){
        if(idx == len) return false;
        boolean fp = hasSumPartitionHelper(arr, idx + 1, fps + arr[idx], sps);
        boolean sp = hasSumPartitionHelper(arr, idx + 1, fps, sps + arr[idx]);
        System.out.printf("%d, %d", fps, sps);
        System.out.println();
        if(fps != 0 && sps != 0 && fps == sps) return true;
        return fp && sp;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        System.out.println(new EqualHalvesInArray().hasEqualSumPartitions(arr));
    }
}
