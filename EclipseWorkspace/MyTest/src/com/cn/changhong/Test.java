package com.cn.changhong;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Test {
  public static void main(String[] args) {
    String time = "2016-10-26";
    Test test = new Test();
    System.out.println(test.changeDateByDays(time, -20));
  }
  public String changeDateByDays(String date, int dayCount){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date returnDate = new Date();
    try {
        Date nowTime = format.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(Calendar.DAY_OF_MONTH, dayCount);
        returnDate = calendar.getTime();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return format.format(returnDate);
  }
}

