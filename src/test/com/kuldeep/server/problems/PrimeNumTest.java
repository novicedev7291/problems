package com.kuldeep.server.problems;

import com.kuldeep.problems.PrimeNumSum;
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
