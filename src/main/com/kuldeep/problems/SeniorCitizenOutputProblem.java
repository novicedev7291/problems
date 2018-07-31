package com.kuldeep.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeniorCitizenOutputProblem {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();
        List<String> input = new ArrayList<>(t);
        while(t-- > 0){
            input.add(s.nextLine());
        }
        process(input);

    }

    private static void process(List<String> input) {
        for(String v: input){
            byte gender = (byte) v.charAt(0);
            int l = v.length();
            int age = getAge(v, l-1,2);

            if(((gender == 70 || gender == 102) && age >= 58) || ((gender == 77 || gender == 109) && age >= 60)){
                System.out.println("Senior Citizen");
            }
            else{
                System.out.println("Not A Senior Citizen");
            }
        }
    }

    private static int getAge(String v, int startIdx, int endIdx) {
        int age = 0;

        for(int i = startIdx, j = 0; j < (startIdx - endIdx)+1 ; i--, j++){
            int digit = v.charAt(i) - '0';
            age =  age + digit * pow(10, j);
        }

        return age;
    }

    private static int pow(int n, int power){
        int result = 1;
        for(int i = 0; i < power; i++){
            result = result * n;
        }

        return result;
    }
}
