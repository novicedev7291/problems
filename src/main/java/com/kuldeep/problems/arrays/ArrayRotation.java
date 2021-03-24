package com.kuldeep.problems.arrays;

import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        /*int[] a = {1};
        new ArrayRotation().rotate(a, 2);
        System.out.println(Arrays.toString(a));*/
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] s = new int[m][n];
        int j;
        for(int i = 0; i< m; i++){
            String chars = sc.nextLine();
            for(j = 0; j < n; j++){
                if(chars.charAt(j) == '.'){
                    s[i][j] = 0;
                }
                else
                    s[i][j] = 1;
            }
        }



    }

    public void rotate(int[] nums, int k) {
        int n = nums.length, i, j;
        int d = gcd(n, k%n);

        for(i = n-1; i > n - d ; i--){
            int temp = nums[i];
            j = i;

            while(true){
                int l = j - k;
                if(l < 0)
                    l = n + l;

                if(l == i)
                    break;
                nums[j] = nums[l];
                j = l;
            }
            nums[j] = temp;
        }
    }

    int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}
