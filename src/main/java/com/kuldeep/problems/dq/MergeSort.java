package com.kuldeep.problems.dq;

import java.util.Arrays;

public class MergeSort {
    private int[] aux;
    public static void main(String[] args) {
        int[] arr = {20, 19 ,10, 1 ,5, 4, 3, 2, 18, 20, 9, 7, 8, 6, 12, 11, 13, 14, 16, 15, 17};
        new MergeSort().mergesortArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void mergesortArray(int arr[]){
        mergeSort(arr, 0, arr.length-1);
    }

    void mergeSort(int[] arr, int l, int h){
        if(l >= h) return;
        int mid = l + (h - l)/2;
        mergeSort(arr, l, mid );
        mergeSort(arr, mid+1, h);
        merge(arr, l, mid, h);
    }

    private void merge(int[] arr, int l, int mid, int h) {
        int i = l, j = mid + 1, k = l;
        int[] aux = Arrays.copyOf(arr,arr.length);
        while (i <= mid && j <= h){
            if(aux[i] < aux[j]){
                arr[k] = aux[i];
                i++;
            }else{
                arr[k] = aux[j];
                j++;
            }
            k++;
        }

        while(i <= mid){
            arr[k] = aux[i];
            i++;
            k++;
        }

    }
}
