package com.yc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author android_djf
 *
 */
public class TimeUtil {

  public static String getCurrentDate(){
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    String date=sdf.format(new Date());
    return date;
  }
  
  /**
   * 在date日期基础上添加days天
   * @param date
   * @param days
   * @return
   */
  public static Date addDays(Date date, int days)
  {
   Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   cal.add(Calendar.DATE, days); //minus number would decrement the days
   return cal.getTime();
  }
  
  /**
   * 获取明天的日期
   * @return
   */
  public static String getTomorrowDate(){
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    String date=sdf.format(addDays(new Date(),1));
    return date;
  }
  
  
  public static String getCurrentTime(){
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    String currentTime=sdf.format(new Date());
    return currentTime;
  }
  
  public static  String getCurrentTimeAndDate(){
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime=sdf.format(new Date());
    return currentTime;
  }
}
