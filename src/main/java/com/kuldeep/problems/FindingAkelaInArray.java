package com.kuldeep.problems;

public class FindingAkelaInArray {
    public static void main(String[] args) {
        int[] arr = {1,1,2, 2, 4, 7, 7};
        System.out.println(new FindingAkelaInArray().findAkelaInLogN(arr));
    }

    int findAkelaInLogN(int arr[]) {
        return findAkelaHelper(arr, 0, arr.length-1);
    }

    int findAkelaHelper(int arr[], int l, int h){
        if(h < l) return 0;
        int n = h - l + 1;
        int mid = l + (h - l)/2;
        if(n == 3){
            if(arr[mid] == arr[mid+1]) return arr[l];
            else
                return arr[h];
        }
        if((mid + 1) <= h && arr[mid] == arr[mid+1]){
            return findAkelaHelper(arr, mid + 2, h);
        }else if((mid - 1) >= l && arr[mid] == arr[mid - 1]){
            return findAkelaHelper(arr, l, mid - 2);
        }
        return arr[mid];
    }


}
