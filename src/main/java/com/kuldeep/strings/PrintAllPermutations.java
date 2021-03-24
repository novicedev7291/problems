package com.kuldeep.strings;

import java.util.Arrays;

public class PrintAllPermutations {
    public static void main(String[] args) {

    }

    public void print(char[] s){
        print(s);
        System.out.println(Arrays.toString(s));
        char first = s[0];
        char last = s[s.length-1];
        char t = first;
        first = last;
        last = t;
        print(s);
    }

    public void printHelper(char[] s, int i, int j){

    }
}
