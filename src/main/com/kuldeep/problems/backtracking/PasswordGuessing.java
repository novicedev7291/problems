package com.kuldeep.problems.backtracking;

import java.util.Scanner;

public class PasswordGuessing {
    static Scanner sc =  new Scanner(System.in);
    public static void main(String[] args) {
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- != 0){
            String s = sc.nextLine();
            new PasswordGuessing().printGuess(s);
        }
    }

    public void printGuess(String s){
        int l = s.length();
        char[] pwds = new char[l];
        printGuessHelper(s, 0, l, pwds, 0);
    }

    void printGuessHelper(String s, int si, int l, char[] pwds, int pi){

        if(si == l){
            for(int i = 0; i < l; i++){
                System.out.printf("%c",pwds[i]);
            }
            System.out.printf(" ");
            return;
        }


            char c = s.charAt(si);

            if(c >= 'A' && c <= 'Z'){
                pwds[pi] = (char) (97 + (c - 65));
                printGuessHelper(s, si + 1, l, pwds, pi + 1);
                pwds[pi] = c;
                printGuessHelper(s, si+1, l, pwds, pi+1);
            }
            else if(c >= 'a' && c <= 'z'){
                pwds[pi] = c;
                printGuessHelper(s, si + 1, l, pwds, pi + 1);
                pwds[pi] = (char) (65 + (c - 97));
                printGuessHelper(s, si+1, l, pwds, pi+1);
            }
            else{
                pwds[pi] = c;
                printGuessHelper(s, si + 1, l, pwds, pi+1);
            }
    }


}
