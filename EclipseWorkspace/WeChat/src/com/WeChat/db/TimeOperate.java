package com.WeChat.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeOperate {
	/**
	 * 转换日期格式
	 * */
	public String changeTimeFormat(Date d){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String time = f.format(d);
 		return time;
	}
	/**
	 * 得到num天后的日期
	 * */
	public String addDaysbyNum(Date d, int num){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date d2 = new Date(d.getTime()+num*24*3600*1000);
		String time = f.format(d2);
 		return time;
	}
	/**
	 * 得到num天前的日期
	 * */
	public String reduceDaysbyNum(Date d, int num){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date d2 = new Date(d.getTime()-num*24*3600*1000);
		String time = f.format(d2);
 		return time;
	}
	/**
	 * 过去某天距现在有多少天
	 * */
	public int getTimeDifferenceToToday(String time){
		int num=0;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String ss = f.format(d);
		try {
			long i = (f.parse(ss).getTime()-f.parse(time).getTime())/(24*3600*1000);
			num = (int)i;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return num;
	}
}
