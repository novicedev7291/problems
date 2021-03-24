package com.kuldeep.problems;

public class FlyingFactorial {
    public static void main(String[] args) {
        int len =12;
        System.out.println((int) Math.floor(Math.sqrt(len)));
        System.out.println((int) Math.ceil(Math.sqrt(len)));
    }
    public void print(int n,int num){
        while (true){
            long f = factorial(num);
            if(f == 0) break;
            long nZeros = countZeros(f);
            if(nZeros == n){
                System.out.printf("%d ", num);
            }
            else if(nZeros > n){
                break;
            }
            num = num + 1;
        }
    }

    private long factorial(long num) {
        if(num == 0) return 1;
        if(num == 1) return 1;
        return num * factorial(num-1);
    }

    private long countZeros(long num){
        int count = 0;
        while (num > 0){
            long d = num % 10;
            if(d == 0){
                count++;
            }
            else{
                break;
            }
            num = num /10;
        }
        return count;
    }
}
