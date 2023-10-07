package com.kuldeep.strings;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StringMatchingTest {
    private final String haystack;
    private final String needle;
    private final int matchingIndex;

    public StringMatchingTest(String haystack, String needle, int matchingIndex) {
        this.haystack = haystack;
        this.needle = needle;
        this.matchingIndex= matchingIndex;
    }

    @Parameterized.Parameters(name = "{0} contains {1} at {2}")
    public static Object[][] testCases() {
        return new Object[][] {
                {"aaabaadaaeab", "aaeab", 7},
                {"aaaeab", "aaeab", 1},
                {"ababcabcabababd", "ababd", 10},
                {"ababcabcabababd", "ababde", -1},
                {"kuldeep", "sandeep", -1},
                {"aabcaabe", "aabd", -1},
                {"My name is Kuldeep Yadav", " Yad", 18},
                {"abcdabcdfabcdabcdeababcdeacd", "abcdeab", 13},
                {"", "", 0},
        };
    }

    @Test
    public void shouldHaystackContainsNeedle() {
        Assertions.assertThat(StringMatching.indexOf(haystack, needle)).isEqualTo(matchingIndex);
    }

}

