package com.cn.changhong.zhidao.util;

import java.sql.Timestamp;

public class TimeTest {
	
	public static void main(String[] args)
	{
		
		
		
	String tsStr = "2011-05-09 9:49:45";
	String tsStr1 = "2011-05-09 10:10:45";
	try {
		Timestamp	ts = Timestamp.valueOf(tsStr);
		Timestamp	ts1 = Timestamp.valueOf(tsStr1);
		String[] time= TimeConvert.convertMinutes(ts, ts1);
		for(int i=0;i<time.length;i++)
			System.out.println(time[i]);
		
//		System.out.println(ts.getHours());
//		System.out.println(ts.getMinutes());
//		System.out.println(19/15);
//		
//		
//		int stm=3;
//		for(int i=8;i<11;i++)
//		{
//			
//			for(int j=1;j<6;j++)
//			{
//				
//				if(i*10+stm > 102)
//					break;
//				if(stm>=5)
//				{
//					stm =1;
//					
//					break;
//				}
//				else {
//					System.out.println(i+" "+stm);
//					stm++;
//					
//				}
//			}
//	
//		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
