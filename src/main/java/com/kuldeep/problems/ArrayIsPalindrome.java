package com.kuldeep.problems;

public class ArrayIsPalindrome {
    public static void main(String[] args) {
        int[] arr = {1,2,3,3,12,1};
        System.out.println(new ArrayIsPalindrome().isPalindromic(arr));
    }

    public boolean isPalindromic(int[] arr){
        return isPalindromicHelper(arr, 0 , arr.length-1);
    }

    private boolean isPalindromicHelper(int[] arr, int start, int end){
        if(start >= end) return true;
        if(arr[start] != arr[end]) return false;
        return isPalindromicHelper(arr, start + 1, end - 1);
    }
}
