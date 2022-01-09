package com.vognev.textilewebproject;

import org.junit.Assert;
import org.junit.Test;
/**
 * textilewebproject  07/10/2021-6:41
 */

public class SimpleTest {
    @Test
    public void test(){
       int a=2;
       int b=23;
        Assert.assertEquals(46,a*b);
        Assert.assertEquals(25,a+b);
    }
    // тестирование exception
    @Test(expected = ArithmeticException.class)
    public void error(){
        int i=0;
        int ii=1/i;
    }
}
