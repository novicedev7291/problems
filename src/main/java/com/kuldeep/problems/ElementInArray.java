package com.kuldeep.problems;

import java.util.Scanner;

public class ElementInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int Q = Integer.parseInt(arr[1]);
        String[] numbers = sc.nextLine().split(" ");

        if(numbers.length != N) System.exit(0);

        long[] queries = new long[Q];
        int i = 0;

        while(Q-- != 0){
            queries[i] = sc.nextLong();
            i++;
        }

        for(long query: queries){
            System.out.printf("%s %s", query,find(numbers, query, 0, N - 1 ) ? "YES":"NO");
            System.out.println();
        }

    }

    private static boolean find(String[] numbers, long num, int start, int end) {
        if(end < start) return false;
        int mid = start + (end - start)/2;
        if(num == Long.parseLong(numbers[mid])){
            return true;
        }
        else if(num > Long.parseLong(numbers[mid])){
            return find(numbers, num, mid+1, end);
        }else{
            return find(numbers, num, start, mid-1);
        }
    }
}
