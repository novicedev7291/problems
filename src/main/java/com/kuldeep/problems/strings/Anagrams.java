package com.kuldeep.problems.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagrams {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- != 0){
            int k = sc.nextInt();
            String first = sc.next();
            String second = sc.next();
            System.out.println(new Anagrams().areKAnagrams(first, second, k)? "YES":"NO");
        }
    }
    boolean areKAnagrams(String first, String second, int k) {
        int l1 = first.length();
        int l2 = second.length();
        if(l1 != l2) return false;
        Map<Character, Integer> countMap = new HashMap<>(l1);
        for(int i = 0; i < l1; i++){
            Character ch = first.charAt(i);
            if(countMap.containsKey(ch)){
                countMap.put(ch, countMap.get(ch) + 1);
            }
            else
                countMap.put(ch, 1);
        }
        int count = 0;
        for(int i = 0; i < l2; i++){
            Character ch = second.charAt(i);
            if(countMap.containsKey(ch)){
                if(countMap.get(ch) == 0) count+=1;
                else
                    countMap.put(ch, countMap.get(ch) - 1);
            }
            else
                count+=1;
        }

        System.out.printf("%d %d", count, k);

        return count <= k;
    }
}
