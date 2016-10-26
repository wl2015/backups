package com.cn.changhong;

public class AssertTest {
	public int divide(int a, int b) throws Exception
    {
        if(0 == b)
        {
            throw new Exception("除数不能为0");            
        }
        return a / b;
    }
}
