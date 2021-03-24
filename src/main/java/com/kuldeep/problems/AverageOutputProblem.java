package com.kuldeep.problems;

import java.util.Scanner;

public class AverageOutputProblem {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while(s.hasNextLine()){
            String str = s.nextLine();
            String[] numbers = str.split(" ");

            Float a = Float.parseFloat(numbers[0]);
            Float b = Float.parseFloat(numbers[1]);
            Float c = Float.parseFloat(numbers[2]);

            if((a < 0 || a > 300) || (b < 0 || b > 300) || (c < 0 || c > 300)){
                break;
            }
            float avg = (a+b+c)/numbers.length;
            System.out.println(String.format("%.3f",Math.round(avg*1000)/1000.0f));
        }

    }
}
