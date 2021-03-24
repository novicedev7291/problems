package com.kuldeep.problems.dq;

import com.kuldeep.problems.FirstIdxOfDuplicateItemInSortedArray;

/**
 * Good observation here is that majority element will be on mid for sure.
 */
public class MajorityElementInArray {
    public static void main(String[] args) {
        int[] a = {1,2,3,3,3,3,10};
        new MajorityElementInArray().printMajority(a);
    }

    void printMajority(int a[]){
        int n = a.length;
        int mid = 0 + (n - 1 + 0)/2;
        int firstIdx = new FirstIdxOfDuplicateItemInSortedArray().getFirstIdx(a, a[mid], 0, n-1);

        if(a[mid] == a[firstIdx + n/2]){
            System.out.println(a[mid]);
        }
        else{
            System.out.println("NONE");
        }
    }
}
