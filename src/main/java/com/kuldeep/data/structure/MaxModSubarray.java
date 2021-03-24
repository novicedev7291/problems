package com.kuldeep.data.structure;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class MaxModSubarray {
    // Complete the maximumSum function below.
    static long maximumSum(long[] a, long m) {
        long prefix[] = new long[a.length];
        TreeSet<Long> sortedM = new TreeSet();
        prefix[0] = a[0] % m;
        sortedM.add(prefix[0]);
        long modSoFar = prefix[0] % m;
        for(int i = 1; i < a.length; i++){
            prefix[i] = (a[i] % m + prefix[i-1]) % m;
            Long res = sortedM.higher(prefix[i]);
            if(res != null)
                modSoFar = Math.max(modSoFar, (prefix[i] - res + m) % m);
            sortedM.add(prefix[i]);
        }
        return modSoFar;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

           /* bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();*/
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
