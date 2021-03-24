package com.kuldeep.problems.strings;

public class StringToInt {
    public static void main(String[] args) {
        System.out.println(new StringToInt().stringToInt("280"));
    }

    int stringToInt(String s) {
        int l = s.length();
        int negative = 1, i = 0;
        if(l > 0 && (int)s.charAt(0) == 45){
            negative = -1;
            i = 1;
        }

        int num = 0;
        for(; i < l; i++){
            char ch = s.charAt(i);
            if(!(ch >= '0' && ch <= '9')) return -1;
            num = num * 10 + (ch - 48);
        }

        return num * negative;
    }
}
