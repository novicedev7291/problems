package com.kuldeep.problems;


import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArrayPairs {
    static int[] tree;
    static int[] a;
    static long solve1(int[] arr) {
        int n = arr.length;
        a = arr;
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        tree = new int[max_size];
        buildTree(0, 0, n-1);
        long count = 0;
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                long mul = (long)arr[i]*(long)arr[j];
                if(mul <= findMax(0, i, j, 0, max_size-1)){
                    count++;
                }
            }
        }
        return count;
    }

    static int findMax(int node, int l, int r, int start, int end){
        if(l >= start && r <= end){
            return tree[node];
        }
        if(l < start || r > end){
            return Integer.MIN_VALUE;
        }
        int mid = start + (end - start) / 2;
        int maxL = findMax(2*node+1, l, r, start, mid);
        int maxR = findMax(2*node+2, l, r, mid+1, end);
        return Math.max(maxL, maxR);
    }

    static void buildTree(int node, int start, int end){
        if(start == end){
            tree[node] = a[start];
        }
        else{
            int mid = start + (end - start) / 2;
            buildTree(2*node+1, start, mid);
            buildTree(2*node+2, mid+1, end);
            tree[node] = Math.max(tree[2*node+1], tree[2*node+2]);
        }
    }

    static long solve(int[] arr){
        n = arr.length;
        return solveHelper(arr, 0, n-1);

    }
    static int n;
    static long solveHelper(int[] arr, int start, int end){
        if(start >= end || start >= n || end < 0){
            return 0;
        }
        System.out.println(start+" "+ end);

        int max = arr[start];
        int k = start;
        for(int i = start; i <= end; i++){
            if(arr[i] > max){
                k = i;
                max = arr[i];
            }
        }

        //count pairs in left array
        int countLeft = 0;
        for(int i = start; i < k; i++){
            for(int j = i+1; j <= k; j++){
                long mul = (long)arr[i]*(long)arr[j];
                if(mul <= max){
                    countLeft++;
                }
            }
        }

        //count pairs in left array
        int countRight = 0;
        for(int i = k+1; i < end; i++){
            for(int j = i+1; j <= end; j++){
                long mul = (long)arr[i]*(long)arr[j];
                if(mul <= max){
                    countRight++;
                }
            }
        }
        int count = countRight + countLeft;
        return count +solveHelper(arr, start, k-1) + solveHelper(arr, k+1, end );
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < arrCount; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        long result = solve(arr);

        System.out.println(result);

        scanner.close();
    }
}

