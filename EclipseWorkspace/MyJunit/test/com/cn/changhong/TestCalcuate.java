package com.cn.changhong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCalcuate {
  Calcuate calcuate;
  @Before
  public void setUp() throws Exception{
    calcuate = new Calcuate();
  }
  @Test
  public void testAdd(){
    int result = calcuate.add(12, 13);
    Assert.assertEquals("加法有问题", result, 25);
  }
  @Test 
  public void testMinus(){
    int result = calcuate.minus(23, 100);
    Assert.assertEquals("减法有问题", result,-77);
  }
  @Test
  public void testDivide(){
    int result = calcuate.divide(24, 12);
    Assert.assertEquals("除法有问题", result,2);
  }
  @Test
  public void testMul(){
    int result = calcuate.mul(20,5);
    Assert.assertEquals("乘法有问题", result,100);
  }
  
}
