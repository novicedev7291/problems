package com.kuldeep.problems;

public class CheckAnagrams {
    public static void main(String[] args) {
        System.out.println(areAnagrams("PINK","PARK"));
    }

    private static boolean areAnagrams(String first, String second){
        if(first.length() != second.length()) return false;

        int number = 0;
        first = first.toUpperCase();
        second = second.toUpperCase();
        for(int i = 0; i < first.length(); i++){
            number = setKthBit(number, (byte)(((byte) first.charAt(i) - 65) + 1));
        }

        for(int i = 0 ; i < second.length(); i++){
            if(!isKthBitSet(number, (byte)(((byte) second.charAt(i) - 65) + 1))) return false;
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
