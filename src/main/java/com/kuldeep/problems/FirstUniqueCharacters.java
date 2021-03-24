package com.kuldeep.problems;

public class FirstUniqueCharacters {
    public static void main(String[] args) {
        System.out.println(getFirstUniqueIndex("abcddcba".toCharArray()));
    }

    private static int getFirstUniqueIndex(char[] arr){
        int[] indexArr = new int[26];
        for(int i = 0; i < arr.length; i++){
            int pos = (int) arr[i] - 97;
            incrementKthIndex(indexArr, pos);
        }

        for(int i = 0; i < arr.length; i++){
            int pos = (int) arr[i] - 97;
            if(indexArr[pos] == 1){
                return i;
            }
        }

        return -1;
    }

    private static void incrementKthIndex(int[] arr, int k){
        arr[k] = arr[k] + 1;
    }

    private static boolean isKthBitSet(int n, int k){
        return ((n >> (k - 1)) & 1) == 1;
    }

    private static int setKthBit(int n, int k){
        return (1 << (k - 1)) | n;
    }
}
