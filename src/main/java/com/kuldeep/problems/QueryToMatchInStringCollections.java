package com.kuldeep.problems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QueryToMatchInStringCollections {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("string_matches_collections_input"));

        int n = Integer.parseInt(scanner.nextLine().trim());

        String[] collections = new String[n];

        for (int stringsItr = 0; stringsItr < n; stringsItr++) {
            String stringsItem = scanner.nextLine();
            collections[stringsItr] = stringsItem;
        }

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            String queryString = scanner.nextLine();

            int res = findSuffix(collections, queryString);
            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    static int findSuffix(String[] collections, String query){
        int count = 0;
        for(String string: collections){
            count+=checkIfMatches(string, query);
        }
        return count;
    }

    private static int checkIfMatches(String string, String query) {
        return string.equals(query) ? 1 : 0;
    }
}
