package com.kuldeep.algorithims;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LISTest {

    @Test
    public void shouldReturnMaxLengthOfLISInGivenArray() {
        int[] arr = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};

        assertEquals(6, new LIS().maxLengthLIS(arr));
    }

}