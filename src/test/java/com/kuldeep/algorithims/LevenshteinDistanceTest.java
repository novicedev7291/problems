package com.kuldeep.algorithims;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class LevenshteinDistanceTest {

    @Test
    public void shouldMatchEditDistanceBetweenGivenStrings() {
        String str1 = "yabd";
        String str2 = "abc";

        LevenshteinDistance distanceUtil = new LevenshteinDistance(str1, str2);

        assertEquals(2, distanceUtil.getMinDistance());
    }

    @Test
    public void shouldGiveZeroForEmptyStrings() {
        LevenshteinDistance distanceUtil = new LevenshteinDistance("","");
        assertEquals(0, distanceUtil.getMinDistance());
    }
}
