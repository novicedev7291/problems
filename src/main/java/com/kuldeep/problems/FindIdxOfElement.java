package com.kuldeep.problems;

public class FindIdxOfElement {
    public static void main(String[] args) {
        System.out.println(new FindIdxOfElement().getFactorial(0));
    }

    public int findingIdx(int[] a, int k){
        return findIdxHelper(a, k, 0);
    }

    private int findIdxHelper(int[] arr, int num, int idx){
        if(idx == arr.length) return -1;
        if(arr[idx] == num) return idx;
        return findIdxHelper(arr, num, idx + 1);
    }

    long getFactorial(int N)
    {
        if(N == 1) return 1;
        return N * getFactorial(N-1);
    }
}
