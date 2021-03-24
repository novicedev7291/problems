package com.kuldeep.problems;

import java.util.HashMap;
import java.util.Map;

public class CheckAnagrams {
    public static void main(String[] args) {
        System.out.println(areAnagrams("algorithm","ALGORITHM"));
    }

    private static boolean areAnagramsByNumberMethod(String first, String second){
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

    static boolean areAnagrams(String first, String second) {
        if(first.length() != second.length()) return false;
        int[] index = new int[256];
        for(int i = 0; i < first.length(); i++){
            char c = first.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                index[(int) c] += 1;
                index[((int)c) + 32] +=1;
            }
            else if(c >= 'a' && c <= 'z'){
                index[(int) c] += 1;
                index[((int)c) - 32] +=1;
            }
        }

        for(int i = 0; i < second.length(); i++){
            // If index does not contain char from second string
            char c = second.charAt(i);
            int upper = 0;
            int lower = 0;
            if(c >= 'A' && c <= 'Z') {
                lower = ((int)c) + 32;
                upper = (int) c;
            }
            else if(c >= 'a' && c <= 'z'){
                upper = ((int)c) - 32;
                lower = (int) c;
            }
            if(index[lower] == 0 || index[upper] == 0){
                return false;
            }
            // Count chars in first and second string, eventually if second string has more char
            // it will make it 0 and from above case we know not anangram
            index[lower] -= 1;
            index[upper] -= 1;
        }

        return true;

    }

    int absoluteDistinctValues(int a[])
    {
        Map<Integer, Integer> index = new HashMap();
        for(int i = 0; i < a.length; i++){
            if(index.containsKey(a[i])){
                Integer count = index.get(a[i]);
                index.put(a[i], count+1);
            }
            else
                index.put(a[i], 1);
        }

        int count = 0;
        for(int i = 0; i < a.length; i++){
            if(index.containsKey(Math.abs(a[i])) && (index.get(Math.abs(a[i])).equals(index.get(a[i])) && index.get(a[i]) == 1)){
                count++;
            }
        }
        return count;
    }

    private static boolean isKthBitSet(int n, int k){
        return ((n >> (k - 1)) & 1) == 1;
    }

    private static int setKthBit(int n, int k){
        return (1 << (k - 1)) | n;
    }
}
