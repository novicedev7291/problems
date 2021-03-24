package com.kuldeep.problems;

import java.util.Scanner;

public class CrashZero {
    public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            String str = s.nextLine();

            int length = str.length();

            int count = 0;

            for(int i = 0; i < length; i+=4){
                int j = 0;
                while(j < 4){
                    if(j == 0 && str.charAt(j+i) != 'Z'){
                        count++;
                    }
                    else if( j == 1 && str.charAt(j+i) != 'E'){
                        count++;
                    }else if(j == 2 && str.charAt(j+i) != 'R'){
                        count++;
                    }else if(j == 3 && str.charAt(j+i) != 'O'){
                        count++;
                    }
                    j++;
                }
            }

            System.out.println(count);

    }
}
