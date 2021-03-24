package com.kuldeep.problems;

public class StringToInt {
    public static void main(String[] args) {
        String s = "-200";
        char[] arr = s.toCharArray();
        int negative = 1, num = 0;

        for(int i = 0; i < s.length(); i++){
            int aV = (int)s.charAt(i);
            if(aV == 45){
                negative = -1;
                continue;
            }
            if(aV >= 48 && aV <= 58){
                num = num * 10 + (aV - 48);
            }else{
                return;
            }
        }

        System.out.println(num*negative);

    }

}
