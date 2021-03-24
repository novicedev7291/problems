package com.kuldeep.problems.strings;

import java.util.ArrayList;
import java.util.List;

public class UniqueChars {
    public static void main(String[] args) {
        String s = new UniqueChars().uniqueCharString("abrakadabra");
        System.out.println(s);
    }
    String uniqueCharString(String s) {
        int[] arr = new int[256];

        for(int i = 0 ; i < s.length(); i++){
            if((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                    || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')){
                arr[(int) s.charAt(i)] += 1;
            }
        }

        List<Character> uniqueChars = new ArrayList(s.length());
        for(int i = 0; i < s.length(); i++){
            if(arr[(int) s.charAt(i)] == 1){
                uniqueChars.add(s.charAt(i));
            }
        }

        char[] chars = new char[uniqueChars.size()];
        for(int i = 0; i < uniqueChars.size(); i++){
            chars[i] = uniqueChars.get(i);
        }


        return String.valueOf(chars);
    }
}
