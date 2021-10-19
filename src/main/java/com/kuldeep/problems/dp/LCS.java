package com.kuldeep.problems.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * In given two strings find out the longest sequence of chars which are in both strings.
 */
public class LCS {
    private static final Logger log = LoggerFactory.getLogger(LCS.class);
    char[] ga;
    char[] gb;
    int[][] dp;

    int lcsLen(char[] a, char[] b) {
        ga = a;
        gb = b;
        dp = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            Arrays.fill(dp[i], 0);
        }
        return lcsLenHelper(0, 0);
    }

    int lcsLenHelper(int ai, int bi) {
        if (ai == ga.length || bi == gb.length) return 0;

        if (dp[ai][bi] != 0) return dp[ai][bi];

        if (ga[ai] == gb[bi]) {
            return dp[ai][bi] = 1 + lcsLenHelper(ai + 1, bi + 1);
        }
        return dp[ai][bi] = Math.max(lcsLenHelper(ai + 1, bi), lcsLenHelper(ai, bi + 1));
    }

    private String iterativeLCS(String str1, String str2) {
        int n = str1.length(), m = str2.length();

        String[][] dp = new String[n + 1][m + 1];

        for (int i = 0; i < m + 1; i++) dp[0][i] = "";
        for (int i = 0; i < n + 1; i++) dp[i][0] = "";

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    if (dp[i - 1][j].length() > dp[i][j - 1].length()) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }

        return dp[n][m];
    }

    private String createLCSFrom(List<List<Tuple>> dp) {
        int n = dp.size(), m = dp.get(0).size();

        Tuple lastCell = dp.get(n - 1).get(m - 1);

        List<Character> chars = new ArrayList<>();
        while(lastCell.s1Idx != null && lastCell.s2Idx != null) {
            if(lastCell.ch != null) {
                chars.add(lastCell.ch);
            }
            lastCell = dp.get(lastCell.s1Idx).get(lastCell.s2Idx);
        }

        Collections.reverse(chars);

        return chars.stream()
                    .map(String::valueOf)
                    .collect(joining());
    }

    private String iterativeSpaceOptimizedLCS(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        List<List<Tuple>> dp = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            List<Tuple> columns = new ArrayList<>(m+1);
            for (int j = 0; j < m + 1; j++) {
                columns.add(j, new Tuple(null, 0, null, null));
            }
            dp.add(i, columns);
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    Tuple prevCell = dp.get(i - 1).get(j - 1);
                    dp.get(i).set(j, new Tuple(str1.charAt(i - 1), prevCell.len + 1, i - 1, j - 1));
                } else {
                    Tuple topCell = dp.get(i - 1).get(j);
                    Tuple leftCell = dp.get(i).get(j - 1);

                    int rowIdx = i - 1, colIdx = j, len = topCell.len;
                    if (leftCell.len > topCell.len) {
                        rowIdx = i;
                        colIdx = j - 1;
                        len = leftCell.len;
                    }

                    Tuple element = new Tuple(null, len, rowIdx, colIdx);
                    dp.get(i).set(j, element);
                }
            }
        }

        return createLCSFrom(dp);
    }

    String lcs(String str1, String str2) {
        return iterativeSpaceOptimizedLCS(str1, str2);
    }

    static final class Tuple {
        Character ch;
        int len;
        Integer s1Idx;
        Integer s2Idx;

        public Tuple(Character ch, int len, Integer s1Idx, Integer s2Idx) {
            this.ch = ch;
            this.len = len;
            this.s1Idx = s1Idx;
            this.s2Idx = s2Idx;
        }
    }

    public static void main(String[] args) {
        String s1 = "pancakes";
        String s2 = "pcaves";

        log.info("LCS of {} and {} is {}", s1, s2, new LCS().lcs(s1, s2));
    }
}
