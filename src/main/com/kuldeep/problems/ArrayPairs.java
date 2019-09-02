package com.kuldeep.problems;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArrayPairs {
    static int[] tree;
    static int[] a;
    static long solve(int[] arr) {
        int n = arr.length;
        a = arr;
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        tree = new int[max_size];
        buildTree(0, 0, n-1, max_size);
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

    static void buildTree(int node, int start, int end, int size){
        if(start == end){
            tree[node] = a[start];
        }
        else{
            int mid = start + (end - start) / 2;
            buildTree(2*node+1, start, mid, size);
            buildTree(2*node+2, mid+1, end, size);
            tree[node] = Math.max(tree[2*node+1], tree[2*node+2]);
        }
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

