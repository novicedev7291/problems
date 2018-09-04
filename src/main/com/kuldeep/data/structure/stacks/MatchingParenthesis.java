package com.kuldeep.data.structure.stacks;

import java.util.Stack;

public class MatchingParenthesis {

    public static void main(String[] args) {
        char[] s = {'(','(', '{', '[','(','(','(',')',')',')','{','}',']', '}',')',')'};
        System.out.println(new MatchingParenthesis().validateParenthesis(s));
    }

    boolean validateParenthesis(char[] s){
        Stack<Character> stk = new Stack<>();

        for(int i = 0; i < s.length; i++){
            char temp = s[i];
            if(temp == '(' || temp == '{' || temp == '['){
                stk.push(temp);
            }
            else if(temp == ')' || temp == '}' || temp == ']'){
                if(stk.isEmpty()) return false;
                char c = stk.pop();

                if(temp == ')' && c != '(') return false;
                else if(temp == '}' && c != '{') return false;
                else if(temp == ']' && c != '[') return false;
            }
        }

        if(!stk.isEmpty()) return false;

        return true;
    }
}
