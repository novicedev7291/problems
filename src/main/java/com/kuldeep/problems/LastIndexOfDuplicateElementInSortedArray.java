package com.kuldeep.problems;

public class LastIndexOfDuplicateElementInSortedArray {
    public static void main(String[] args) {
        int[] arr = {1,2, 2, 2, 2, 4, 4};
        System.out.println(new LastIndexOfDuplicateElementInSortedArray().getLastIdxOfDuplElement(arr, 4,0, arr.length-1));
    }

    public int getLastIdxOfDuplElement(int[] arr, int num, int start, int end) {
        if(end < start) return -1;
        int mid = start + (end - start)/2;

        if(mid+1 <= end && num >= arr[mid+1]){
            return getLastIdxOfDuplElement(arr, num, mid+1, end);
        }else if(num == arr[mid]){
            return mid;
        }
        else if(mid-1 >= start && num <= arr[mid-1]){
            return getLastIdxOfDuplElement(arr, num, start, mid - 1);
        }
        return -1;

    }
}
