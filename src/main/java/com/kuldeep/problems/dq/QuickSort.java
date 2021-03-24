package com.kuldeep.problems.dq;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {10 ,4, 5, 5, 4, 1, 1, 3, 3, 2, 2};
        int[] arr = {20, 19 ,10, 1 ,5, 4, 3, 2, 18, 20, 9, 7, 8, 6, 12, 11, 13, 14, 16, 15, 17};
        new QuickSort().quicksortArray(arr);
        System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(new com.kuldeep.sorting.QuickSort(arr).sort()));
    }

    public void quicksortArray(int[] arr){
        quickSortHelper(arr,0, arr.length-1);
    }

    public void quickSortHelper(int[] arr, int l, int h){
        if(h <= l) return;
        int pivot = partition(arr, l, h);
        quickSortHelper(arr, l, pivot );
        quickSortHelper(arr, pivot+1, h);
    }

    private int partition(int[] arr, int l, int h) {
        int start = l, end = h;
        int pivot = l + (h - l)/2;

        if(arr[pivot] < arr[l]){
            pivot = l;
        }else if(arr[pivot] > arr[h]){
            pivot = h;
        }

        while (true){
            while (arr[start] < arr[pivot]){
                start++;
            }

            while (arr[end] > arr[pivot]){
                end--;
            }

            if(start >= end) return end;

            swap(arr, start, end);
            start++;
            end--;
        }

    }

    private void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }


}
