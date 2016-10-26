package com.cn.changhong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author admin
 * @version 0.0.1
 * */
public class play {
	/**
	 * @author admin
	 * @param aaa
	 * @return null
	 * */
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = dateFormat.parse("20160811");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 2);
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			System.out.println(dateFormat.format(calendar.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
