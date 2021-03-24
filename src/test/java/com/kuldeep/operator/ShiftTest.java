package com.kuldeep.operator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShiftTest {
    @Test
    public void shouldPass8MultipleTest(){
        Map<Integer, Boolean> o1 = new HashMap<>();
        Map<Integer, Boolean> o2 = new HashMap<>();

        for(int i = 1; i < 1073741824 ; i=i*5){
            o1.put(i, isMultipleOf8(i));
            o2.put(i, isMultipleOfEight(i));
        }

        for (Integer number: o1.keySet()
             ) {
            try{
                Assert.assertEquals(o1.get(number), o2.get(number));
            }catch (AssertionError e){
                System.out.println("Conflicting number is : " + number);
            }
        }

    }

    boolean isMultipleOfEight(int n)
    {
        if(n == 0 || n < 8) return false;
        return (n & 7) == 0 ? true: false;
    }

    boolean isMultipleOf8(int n){
        if(n == 0 || n < 8) return false;
        return n % 8 == 0? true: false;
    }
}
