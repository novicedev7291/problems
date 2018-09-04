package com.kuldeep.problems;

public class FindIndexForInsertion {

    public static void main(String[] args) {
        int[] arr = {9, 10, 11, 12, 15};
        System.out.println(new FindIndexForInsertion().getIndex(arr, 15, 0, arr.length -1));
    }

    public int getIndex(int[] a, int k, int l, int h){
        if(h < l) return l;
        int mid = l + (h - l)/2;

        if(k == a[mid]) return mid+1;
        else if(k < a[mid]){
            return getIndex(a, k, l, mid - 1);
        }
        return getIndex(a, k, mid + 1, h);
    }
}
