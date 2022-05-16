package com.kuldeep.sorting;

import java.util.Arrays;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class CountSorting {
    public static void main(String[] args) {
        int[] arr = {7,7,7,7,7,7,7,7,7,7,6,7,7,7,7,7,7,5,7,3, 4, 1, 2, 3, 2, 1, 5, 6, 7, 5};
        arr = sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static int[] sort(int[] arr) {
        int max = arr[0];
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] count = new int[max + 1];

        for (int ele : arr) {
            count[ele] += 1;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i-1];
        }

        int[] temp = new int[n];
        for (int j : arr) {
            temp[count[j] - 1] = j;
            count[j]--;
        }

        return temp;
    }
}
