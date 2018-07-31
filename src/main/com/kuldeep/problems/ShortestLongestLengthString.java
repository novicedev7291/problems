package com.kuldeep.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShortestLongestLengthString {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();
        List<String> input = new ArrayList<>(t);
        while (t-- > 0){
            input.add(s.nextLine());
        }

        for(String line: input) {
            String[] temps = line.split(" ");

            int n = Integer.parseInt(temps[0]);
            int shortest = 20, longest = 0;
            for (int i = 1; i < temps.length; i++) {
                int l = temps[i].length();

                if (l < shortest) {
                    shortest = l;
                }
                if (l >= longest) {
                    longest = l;
                }
            }

            System.out.println(String.format("%d %d", shortest, longest));
        }

    }
}
