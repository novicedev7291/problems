package com.kuldeep.server.operator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mario on 20/07/17.
 */
public class XORSwapTest {

    @Test
    public void swapUsingXOROperator() {
        int x = 8, y = 10;
        assertEquals((x ^ y) ^ x, 10);
        assertEquals((x ^ y) ^ y, 8);
    }
}
