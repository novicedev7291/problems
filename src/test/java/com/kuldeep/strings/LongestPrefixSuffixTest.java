package com.kuldeep.strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class LongestPrefixSuffixTest {
    private final String pattern;
    private final int[] expected;

    public LongestPrefixSuffixTest(String pattern, int[] expected) {
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] testCases() {
        return new Object[][] {
                {"aabcabc", new int[]{0, 1, 0, 0, 1, 0, 0}},
                {"aaeab", new int[]{0, 1, 0, 1, 0}},
                {"abcde", new int[]{0,0,0,0,0}},
                {"aabaabaab", new int[]{0, 1, 0, 1, 2, 3, 4, 5, 6}}
        };
    }

    @Test
    public void shouldProduceExpectedLps() {
        assertThat(LongestPrefixSuffix.lps(pattern.toCharArray()))
                .isEqualTo(expected);
    }

}