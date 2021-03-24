package com.kuldeep.problems;

public class CountZerosAtOneSide {
    public static void main(String[] args) {
        int[] arr = {0,0,0,0,0,0,0,12,14-1};
        int[] arr1 = {0,0,0,0,0};
        CountZerosAtOneSide o = new CountZerosAtOneSide();
        System.out.println(o.countNoOfTrailingZeros(arr));
        System.out.println(o.countNoOfZerosAtLast(arr1));
    }

    public int countNoOfTrailingZeros(int[] arr){
        int idx = new LastIndexOfDuplicateElementInSortedArray().getLastIdxOfDuplElement(arr, 0,0, arr.length - 1);
        return idx >= 0 ? idx + 1 : 0;
    }

    public int countNoOfZerosAtLast(int[] arr){
        int l = arr.length-1;
        int idx = getFirstIdxOfZero(arr, 0, l);
        return idx >= 0 ? l - idx + 1 : 0;
    }

    private int getFirstIdxOfZero(int[] arr, int start, int end){
        if(end < start) return -1;
        int mid = start + (end - start)/2;
        if(mid-1 >= start && 0 == arr[mid-1]){
            return getFirstIdxOfZero(arr, start, mid -1);
        }
        else if(0 == arr[mid]) return mid;
        else if(mid + 1 <= end && 0 == arr[mid+1]){
            return getFirstIdxOfZero(arr, mid + 1, end);
        }
        return -1;
    }


}
