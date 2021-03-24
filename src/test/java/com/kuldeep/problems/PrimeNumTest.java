package com.kuldeep.problems;

import org.junit.Assert;
import org.junit.Test;

public class PrimeNumTest {
    PrimeNumSum o = new PrimeNumSum();
    @Test
    public void shouldReturnTrueForPrimeNumber(){
        Assert.assertTrue(o.checkIfPrime(19));
    }

    @Test
    public void shouldReturnFalseForNonPrimeNumber(){
        Assert.assertTrue(!o.checkIfPrime(4));
    }
}
