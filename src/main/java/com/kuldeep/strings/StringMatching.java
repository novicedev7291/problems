package com.kuldeep.strings;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class StringMatching {
    public static int indexOf(String haystack, String needle) {
        assert haystack.length() >= needle.length();

        char[] chars = needle.toCharArray();
        int[] lps = LongestPrefixSuffix.lps(chars);

        int i = 0;
        int j = 0;

        while (i < haystack.length() && j < chars.length) {
            if (haystack.charAt(i) == chars[j]) {
                i++;
                j++;
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                    continue;
                }
                i++;
            }
        }

        return j == chars.length ? (i - j) : -1;
    }
}
