package com.kuldeep.algorithims;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AbsolutePermutation {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException{

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/ni3/solution.txt"));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);
            int[] result = new int[n];
            if(k == 0){
                for(int i = 0; i < n; i++){
                    result[i] = i+1;
                }
                for (int i = 0; i < result.length; i++) {
                    bufferedWriter.write(String.valueOf(result[i]));
                    if (i != result.length - 1) {
                        bufferedWriter.write(" ");
                    }
                }
            }
            else if(n % (2*k) != 0 || 2*k > n)
                bufferedWriter.write(String.valueOf(-1));
            else{
                for(int i = 1; i < n - k + 1; i++){
                    if(result[i-1] == 0 && result[i+k-1] == 0){
                        result[i-1] = i + k;
                        result[i+k-1] = i;
                    }
                }

                for (int i = 0; i < result.length; i++) {
                    bufferedWriter.write(String.valueOf(result[i]));
                    if (i != result.length - 1) {
                        bufferedWriter.write(" ");
                    }
                }
            }
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
