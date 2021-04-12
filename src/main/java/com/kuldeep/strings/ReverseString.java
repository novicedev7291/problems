package com.kuldeep.strings;

import java.util.Random;

class ReverseString {
    private char[] arr;

    public String reverseString(String s) {
        arr = s.toCharArray();
        reverseString(0, arr.length - 1);
        return new String(arr);
    }

    private void reverseString(int start, int lastIndex) {
        if(start == arr.length) {
            return;
        }
        char ch = arr[start];

        reverseString(start + 1, lastIndex - 1);

        arr[lastIndex] = ch;
    }
}

class ReverseStringApp {
    public static void main(String[] args) {
//        ReverseString obj = new ReverseString();
//        System.out.println(obj.reverseString("hahahaha"));

        Random rand = new Random();
        System.out.println(rand.nextInt(100) + 1);
    }
}
