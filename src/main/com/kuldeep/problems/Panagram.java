package com.kuldeep.problems;

import java.util.Scanner;

public class Panagram {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();

        while(t-- > 0){
            String nums = s.nextLine();
            String str = s.nextLine();
            String[] numsArr = nums.split(" ");
            int numsSum = 0;
            for(int i = 0; i < numsArr.length; i++){
                numsSum+= Integer.parseInt(numsArr[i]);
            }
            int stringSum = 0, checksum = 0;
            for(int i = 0; i < str.length(); i++){
                int pos = ((int) str.charAt(i) - 97) + 1;
                if(isKthBitSet(checksum, pos)){
                    continue;
                }
                stringSum += pos;
                checksum = setKthBit(checksum, pos);
            }
            System.out.println(numsSum);
            System.out.println(stringSum);
            System.out.println(numsSum - stringSum);
        }
    }

    private static boolean isKthBitSet(int n, int k){
        return ((n >> (k - 1)) & 1) == 1;
    }

    private static int setKthBit(int n, int k){
        return (1 << (k - 1)) | n;
    }
}
