package com.kuldeep.problems.strings;

import java.util.HashMap;
import java.util.Map;

public class EqualHalves {
    public static void main(String[] args) {
        String s = "abcdaacd";
        System.out.println(new EqualHalves().hasEqualHalves(s));
    }

    boolean hasEqualHalves(String s) {
        int l =  s.length();
        int i,j, mid;
        i = 0;
        mid = l % 2 == 0 ? l/2 - 1 : l/2;
        int fH = l % 2 == 0 ? mid + 1 : mid;
        j = mid + 1;
        Map<Character, Integer> countMap = new HashMap<>(l/2);
        while(i < fH){
            Character ch = s.charAt(i);
            if(countMap.containsKey(ch)){
                countMap.put(ch, countMap.get(ch) + 1);
            }
            else
                countMap.put(ch, 1);
            i++;
        }
         while(j < l){
            Character ch = s.charAt(j);
             if(countMap.containsKey(ch) && countMap.get(ch) > 0){
                 countMap.put(ch, countMap.get(ch) - 1);
             }
             j++;
         }

         int count = 0;
         for(Map.Entry<Character, Integer> e : countMap.entrySet()){
             count+=e.getValue();
         }

        return count == 0;
    }
}
