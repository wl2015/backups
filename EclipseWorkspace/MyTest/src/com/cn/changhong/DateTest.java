package com.cn.changhong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
  public static void main(String[] args) {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    try {
      Date date = format.parse("20160832");
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
//      calendar.add(Calendar.DAY_OF_MONTH, 0);
      System.out.println(format.format(calendar.getTime()));
      String time = format.format(date);
      System.out.println(time.substring(5, 6));
      System.out.println(format.format(calendar.getTime()));
      calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-2);
      System.out.println(format.format(calendar.getTime()));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
