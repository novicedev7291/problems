package com.kuldeep.problems;

public class FrequencyInArray {
    public static void main(String[] args) {
        int[] arr = {1,3,5,6,8};
        System.out.println(new FrequencyInArray().count(arr, 2));
    }

    public int count(int[] arr, int k){
        int l = arr.length - 1;
        int firstIdx = new FirstIdxOfDuplicateItemInSortedArray().getFirstIdx(arr, k, 0, l);
        int lastIdx = new LastIndexOfDuplicateElementInSortedArray().getLastIdxOfDuplElement(arr, k, 0, l);
        if(firstIdx < 0 || lastIdx <0) return 0;
        return lastIdx - firstIdx + 1;
    }

}
