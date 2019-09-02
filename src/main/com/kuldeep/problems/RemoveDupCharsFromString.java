package com.kuldeep.problems;

import java.util.HashSet;
import java.util.Set;

public class RemoveDupCharsFromString {

    public static void main(String[] args) {
        removeDupChars("apple".toCharArray());
    }

    private static void removeDupChars(char[] arr){
        HashSet a;
        int number = 0, j = 0, count = 0;
        for(int i = 0; i < arr.length; i++){
            byte pos = (byte)(((byte) arr[i] - 97) + 1);
            if(isKthBitSet(number, pos)){
                continue;
            }
            count++;
            number = setKthBit(number, pos);
        }

        System.out.println(count);

        /*for(int i = 0; i < newArr.length; i++){
            if(newArr[i] != '\0'){
                count++;
            }
        }

        arr = null;
        arr = new char[count];

        for(int i = 0; i < count; i++){
            arr[i] = newArr[i];
        }*/


    }

    private static boolean isKthBitSet(int n, int k){
        return ((n >> (k - 1)) & 1) == 1;
    }

    private static int setKthBit(int n, int k){
        return (1 << (k - 1)) | n;
    }
}
