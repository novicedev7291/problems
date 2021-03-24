package com.kuldeep.data.structure;

import com.kuldeep.data.structure.SQRTDecomposition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SQRTDecompositionTest {
    private SQRTDecomposition o;

    @Before
    public void setup(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11};
        o = new SQRTDecomposition(arr);
    }

    @Test
    public void shouldMatchFirstNNumbersSum(){
        Assert.assertEquals(o.query(0,7), (8*9)/2);
    }

    @Test
    public void shouldNotMatchAfterDeletion_FirstNNumbersSum(){
        o.update(4, 3);
        Assert.assertNotEquals(o.query(0, 6), (7*8)/2);
    }

    @Test
    public void shouldMatchRangeSum(){
        int expected = 18;
        Assert.assertEquals(o.query(2, 5), expected);
    }

    @Test
    public void shouldMatchRangeAfterUpdate(){
        int expected = 15;
        o.update(4, 2);
        Assert.assertEquals(o.query(2,5), expected);
    }
}
