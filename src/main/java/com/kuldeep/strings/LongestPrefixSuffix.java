package com.kuldeep.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class LongestPrefixSuffix {

    /**
     * This method returns a table which holds the Longest Prefix Suffix for
     * a given string. <br/>
     * For example, given a string aabcabc, the method will produce below:<br/>
     * <table>
     *     <tr>
     *     <th>a</th>
     *     <th>a</th>
     *     <th>b</th>
     *     <th>c</th>
     *     <th>a</th>
     *     <th>b</th>
     *     <th>c</th>
     *     </tr>
     *     <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td>0</td>
     *     <td>0</td>
     *     <td>2</td>
     *     <td>1</td>
     *     <td>1</td>
     *     </tr>
     * </table>
     * @param pattern
     */
    public static int[] lps(char[] pattern) {
        int[] pos = new int[pattern.length];
        Arrays.fill(pos, 0);

        int len = 0;
        int i = 1;

        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++;
                pos[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = pos[len - 1];
                } else {
                    i++;
                }
            }
        }
        return pos;
    }

}
