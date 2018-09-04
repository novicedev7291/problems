package com.kuldeep.problems;

public class ChoosyPrint {
    public static void main(String[] args) {
        //choosyPrint("this");
        //System.out.println((int)'$');
        System.out.println(count(5));
    }

    static int count(int n){
        int count = 0;
        while(n != 0){
            if((n & 1) == 1){
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    private static void choosyPrint(String s){
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(isVowel(ch)) continue;
            if(ch >= 'A' && ch <= 'Z' ){
                System.out.printf("%c%c", '$', 97 + ((int) ch) - 65);
            }else if(ch >= 'a' && ch <= 'z'){
                System.out.printf("%c%c", '$', ch);
            }
            else{
                System.out.printf("%c", ch);
            }
        }
    }

    private static boolean isVowel(char ch){
        if(ch == 'a' || ch =='A') return true;
        else if (ch == 'e' || ch == 'E') return true;
        else if (ch == 'I' || ch == 'i') return true;
        else if (ch == 'o' || ch == 'O') return true;
        else if (ch == 'u' || ch == 'U') return true;
        return false;
    }
}
