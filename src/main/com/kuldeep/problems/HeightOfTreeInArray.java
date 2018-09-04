package com.kuldeep.problems;

import java.util.Arrays;

/**
 * Value in given array is the parent of its index in array
 * i.e [1,4,4,2,-1,2] where 1 is parent of index 0, 4 is parent of index of 1, other 4 is parent index of 2, 2 is parent of 3
 * and so on, one thing to note here that -1 is the root of tree.
 */
public class HeightOfTreeInArray {
    int heighOfTreeFromParentArray(int parentArr[]) {
        int[] depths = new int[parentArr.length];
        for(int i = 0; i < parentArr.length; i++){
            depths[i] = 0;
        }

        for(int i = 0; i < parentArr.length; i++){
            heightHelper(parentArr, depths, i);
        }

        int height = 0;
        for(int i = 0; i < parentArr.length; i++){
            if(height < depths[i]){
                height = depths[i];
            }
        }
        return height;
    }

    void heightHelper(int[] arr, int[] depths,int index){
        if(index == arr.length) return;

        if(depths[index] != 0) return;

        if(arr[index] == -1){
            depths[index] = 1;
            return;
        }

        if(depths[index] == 0){
            heightHelper(arr, depths, arr[index]);
        }
        depths[index] = depths[arr[index]] + 1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, -1, 5, 6, 1};
        //int[] arr = {-1, 0, 0, 1, 1, 3, 5};
        System.out.println(new HeightOfTreeInArray().heighOfTreeFromParentArray(arr));
    }
}
