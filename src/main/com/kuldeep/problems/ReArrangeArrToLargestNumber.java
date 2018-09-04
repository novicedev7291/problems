package com.kuldeep.problems;

import java.util.Arrays;
import java.util.Comparator;

public class ReArrangeArrToLargestNumber {
    public static void main(String[] args) {
        Integer[] arr = {0,0,3};
        boolean isNegative = false;
        for(Integer n : arr){
            if(n < 0){
                isNegative = true;
                break;
            }
        }
        Arrays.sort(arr, new LargesNumberComparater());
        for(Integer n: arr){
            System.out.printf("%d",n);
        }
    }

}

class LargesNumberComparater implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1.equals(0)){
            return 1;
        }else if(o2.equals(0)){
            return -1;
        }
        int d1 = countDigits(o1);
        int d2 = countDigits(o2);
        int num1 = Math.abs((int)(o1*Math.pow(10, d2) + o2));
        int num2 = Math.abs((int)(o2*Math.pow(10, d1) + o1));
        return num2 - num1;
    }

    private int countDigits(Integer num) {
        return (int)(Math.log10(num)) + 1;
    }
}
