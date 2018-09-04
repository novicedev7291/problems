package com.kuldeep.problems;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseUsingRecursion {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //int[] arr = {1,0,1};
        //System.out.println(new ReverseUsingRecursion().isPalindrome(arr, 0, arr.length-1));
        int t = sc.nextInt();
        while (t-- != 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            new ReverseUsingRecursion().reverse(arr, 0, n - 1);
            for(int i = 0; i < n; i++){
                System.out.printf("%s ", arr[i]);
            }
        }

    }

    public boolean isPalindrome(int[] arr, int l, int h){
        if(l >= h) return true;
        if(arr[l] != arr[h]) return false;
        return isPalindrome(arr, l + 1, h - 1);
    }

    public void reverse(int[] arr, int l, int h){
        if(l >= h) return;
        swap(arr, l, h);
        reverse(arr, l+1, h-1);
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
