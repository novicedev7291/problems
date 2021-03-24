package com.kuldeep.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestStringOutput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        List<String> input = new ArrayList<>();
        while(t-- > 0){
            input.add(sc.nextLine());
        }

        for(int i = 0; i< input.size(); i++){
            System.out.println("Case " + (i+1) + ": " + longestString(input.get(i)));
        }
    }

    private static String longestString(String s) {
        int length = s.length();
        int startIdx=0;

        for(int i = 0; i < length; i++){
            if(s.charAt(i) == ' ' && (length - i) <= length/2){
                return s.substring(startIdx, i);
            }else if(s.charAt(i) == ' '){
                startIdx = i + 1;
            }
        }

        return s.substring(startIdx, length);
    }
}
