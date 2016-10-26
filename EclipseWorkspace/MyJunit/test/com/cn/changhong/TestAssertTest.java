package com.cn.changhong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAssertTest {
	AssertTest assertTest;
	
	@Before
	public void setUp()throws Exception{
		assertTest = new AssertTest();
	}
	
	@Test
	public void testDivide(){
		int result = 0;
        try
        {
            result = assertTest.divide(12, 3);
            Assert.assertTrue(false);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            
        }
        Assert.assertEquals(4, result);// 第一个参数是期望值，第二个参数是要验证的值
	}
}
