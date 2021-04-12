package com.kuldeep.algorithims;

import org.junit.Assert;
import org.junit.Test;

public class UglyNumberTest {
    @Test
    public void fifteenShouldBeUglyNumber() {
        Assert.assertTrue(isUgly(15));
    }

    private boolean isUgly(int num) {
        num = maxDivide(num, 2);
        num = maxDivide(num, 3);
        num = maxDivide(num, 5);

        return num == 1;
    }

    private int maxDivide(int a, int b) {
        while(a % b == 0) {
            a = a / b;
        }

        return a;
    }
}
