package com.kuldeep.algorithims;

public class FifteenTiles {
    public static void main(String[] args) {
        int[] a = {2, 5, 1, 3, 4, 7, 8, 6};
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N - 1  ; i++)
        {
            for (int j = i + 1; j < N ; j++)
            {
                if (a[i] > a[j])
                    count++;
            }
        }
        System.out.println(count);
    }
}
