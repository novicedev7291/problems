package com.kuldeep.problems;

public class SumOfArrayOfElements {
    public static void main(String[] args) {
        int[] arr = {};
        System.out.println(new SumOfArrayOfElements().sumArrayElements(arr));
    }

    public int sumArrayElements(int[] a){
        if(a.length == 0) return 0;
        return sumArrayElementsHelper(a, 0);
    }

    private int sumArrayElementsHelper(int[] arr, int idx){
        if(idx == arr.length - 1) return arr[idx];
        return arr[idx] + sumArrayElementsHelper(arr, idx + 1);
    }
}
