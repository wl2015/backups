package com.cn.changhong.zhidao.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TimeConvert {
	
	
	public static String[]  convertMinutes(Timestamp starttime,Timestamp endtime)
	{
		String[]  times = new String[20];
		int flag=0;
		int start_h = starttime.getHours();
		int start_m =minutesort( starttime.getMinutes());
		int end_h= endtime.getHours();
		int end_m= minutesort(endtime.getMinutes());
		int end_time = end_h*10+minutesort(end_m);
		for(int i=start_h;i<=end_h;i++)
		{
			
			for(int j=1;j<6;j++)
			{
				
				if(i*10+start_m > end_time)
					break;
				if(start_m>=5)
				{
					start_m =1;
					
					break;
				}
				else {
//					System.out.println(i+""+start_m);
					times[flag++]=i+""+start_m;
					start_m++;
					
				}
			}
	
		}
		
		List<String> list = new ArrayList<>();
		for(String time:times){
            if(time!=null && time.length()!=0){
                list.add(time);
            }
        }
        times = list.toArray(new String[0]);
		return times;
		
	}
	
	public static int minutesort(int minute)
	{
		return minute/15+1;
	}

}
