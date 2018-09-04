package com.kuldeep.problems;

public class FirstIdxOfDuplicateItemInSortedArray {
    public static void main(String[] args) {
        FirstIdxOfDuplicateItemInSortedArray o = new FirstIdxOfDuplicateItemInSortedArray();
        int[] arr = {1, 1, 1, 2, 2, 4};
        System.out.println(o.getFirstIdx(arr, 2, 0, arr.length-1));

    }

    public int getFirstIdx(int[] arr, int num, int start, int end){
        if(end < start) return -1;

        int mid = start + (end - start)/2;

        if(mid-1 >= start && num <= arr[mid-1]){
            //Possible first index of item can always be in left first
            return getFirstIdx(arr, num, start, mid -1 );
        }
        else if(num == arr[mid]){
            return mid;
        }
        else if(mid + 1 <= end && num >= arr[mid+1]){
            return getFirstIdx(arr, num, mid+1, end);
        }
        return -1;
    }
}
