package com.kuldeep.problems.arrays;

import java.util.ArrayList;

public class FindTwoNonRepeating {
    public static void main(String[] args) {
        int[] a = {1,1,2,3,4,4,5,5};
        new FindTwoNonRepeating().print(a);
    }

    public void print(int[] nums){
        int n = nums.length;
        int x = 0 , y = 0;

        if(n == 0) return;

        int xor = nums[0];

        for(int i = 1; i < n; i++){
            xor ^= nums[i];
        }

        int msb = xor & ~(xor-1);

        for(int i = 0; i< n; i++){
            if((nums[i] & msb) > 0){
                x = x ^ nums[i];
            }
            else
                y = y ^ nums[i];
        }

        System.out.printf("%d, %d", x, y);
    }
}
