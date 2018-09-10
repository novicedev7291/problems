package com.kuldeep.problems;

import java.util.Scanner;

public class PrimeNumSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0){
            int sum = sc.nextInt();
            int num = sc.nextInt();
            int count = sc.nextInt();
            new PrimeNumSum().printHelper(sum, new int[sum],0,num + 1, count);
        }
    }

    public void printHelper(int sum, int[] nums,int ni, int num, int count){
        if(sum == 0 || count == 0) return;

        if(sum == num){
            nums[ni] = num;
            return;
        }

        if(checkIfPrime(num)){
            if(num < sum){
                nums[ni] = num;
                printHelper(sum - num, nums, ni+1,num + 1, count - 1);
            }
            else if(num > sum){
                return;
            }
        }
        else
            printHelper(sum, nums, ni,num + 1, count);

    }

    public boolean checkIfPrime(int num) {
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
