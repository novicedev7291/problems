package com.kuldeep.problems;

import java.util.HashSet;
import java.util.Set;

public class HasUniqueCharInString {
    public static void main(String[] args) {
        String str = "thisis";
        char[] arr = str.toCharArray();

        //System.out.println(uniqueCharSet.size() == arr.length? true:false);
        System.out.println(hasUniqueChars(arr));
        //System.out.println(isKthBitSet(786816, 19));
        //System.out.println(Integer.toBinaryString(786816));
        //System.out.println(isKthBitSet(786816, 9));
        //System.out.println(isKthBitSet(786816, 5));
    }

    private static boolean hasUniqueCharsUsingSet(char[] arr){
        Set<Character> uniqueCharSet = new HashSet<>(arr.length);

        for(int i = 0; i < arr.length; i++){
            if(uniqueCharSet.contains(arr[i])){
                return false;
            }
            uniqueCharSet.add(arr[i]);
        }
        return true;
    }

    private static boolean hasUniqueChars(char[] arr){
        int number = 0;
        for(int i = 0; i < arr.length; i++){
            byte pos = (byte)(((byte) arr[i] - 97) + 1);
            if(isKthBitSet(number, pos)){
                return false;
            }
            number = setKthBit(number, pos);
        }

        return true;
    }

    private static boolean isKthBitSet(int n, int k){
        return ((n >> (k - 1)) & 1) == 1;
    }

    private static int setKthBit(int n, int k){
        return (1 << (k - 1)) | n;
    }
}
