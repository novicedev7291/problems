package com.kuldeep.algorithims;

import static java.lang.Math.min;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class LevenshteinDistance {
    private final String str1;
    private final String str2;

    public LevenshteinDistance(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    /**
     * This method takes O(min(n, M)) space where n & m is the length of two strings respectively.
     * @param str1 First string
     * @param str2 Second string
     * @return edit distance
     */
    private int calculateMinDistance2(String str1, String str2) {
        String small = str1.length() < str2.length() ? str1 : str2;
        String big = str1.length() > str2.length() ? str1 : str2;

        int[] evenEdits = new int[small.length() + 1];
        int[] oddEdits = new int[small.length() + 1];

        for ( int j = 1; j < small.length() + 1; j++) evenEdits[j] = j;

        int[] currentEdits;
        int[] prevEdits;
        for ( int i = 1; i < big.length() + 1; i++ ){
            if (i % 2 == 1) {
                currentEdits = oddEdits;
                prevEdits = evenEdits;
            }else {
                currentEdits = evenEdits;
                prevEdits = oddEdits;
            }
            currentEdits[0] = i;

            for (int j = 1; j < small.length() + 1; j++) {
                if ( big.charAt(i - 1) == small.charAt(j - 1)) {
                    currentEdits[j] = prevEdits[j-1];
                }else {
                    currentEdits[j] = 1 + min(
                            prevEdits[j-1],
                            min(prevEdits[j], currentEdits[j-1])
                    );
                }
            }
        }

        return big.length() % 2 == 0 ? evenEdits[small.length()] : oddEdits[small.length()];
    }

    /**
     * This method takes O(n*m) space
     * @param str1 first string
     * @param str2 second string
     * @return edit distance i.e insert, delete, replace
     */
    private int calculateMinDistance(String str1, String str2) {
        int n = str1.length(), m = str2.length();

        // (0,0) would be for empty string
        int[][] editTable = new int[n+1][m+1];

        for (int i = 1; i < n + 1; i++) editTable[i][0] = i;

        for (int j = 1; j < m + 1; j++) editTable[0][j] = j;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    editTable[i][j] = editTable[i-1][j-1];
                }else {
                    editTable[i][j] = 1 + min(
                            editTable[i-1][j-1],
                            min(editTable[i][j-1], editTable[i-1][j])
                    );
                }
            }
        }

        return editTable[n][m];
    }

    public int getMinDistance() {
        return calculateMinDistance2(str1, str2);
    }
}
