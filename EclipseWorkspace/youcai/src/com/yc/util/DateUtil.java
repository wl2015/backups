package com.yc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * 时间工具
 * @author Paul Iverson
 * */
public class DateUtil {

  private static final String DATE_PATTERN_NORMAL = "yyyy-MM-dd HH:mm:ss";
  private static final String DATE_PATTERN_SHORT_DT = "yyyy-MM-dd";
  /**
   * 正午的小时数
   * */
  private static final int HIGH_NOON_TIME = 12;
  
  /**
   * 傍晚的小时数
   * */
  private static final int AT_DUSK_TIME = 19;
  
  /**
   * 返回当前时间的的毫秒数/1000
   * */
  public static int convertCurrentDTTMtoInt() {
    
    return convertDTTMtoInt(new Date());
  }
  
  /**
   * 得到指定时间Date的毫秒数/1000
   * */
  private static int convertDTTMtoInt(Date givenDTTM) {
    
    return (int) (givenDTTM.getTime() / 1000);
  }
  
  /**
   * 得到INT时间对应的时间Date对象
   * */
  public static Date convertIntToDTTM(int dttmInt) {
    return new Date((long) dttmInt * 1000);
  }

  /**
   * 格式化时间为yyyy-MM-dd
   * @param dateValue 指定时间的毫秒数/1000
   * */
  public static String formatDTString(int dateValue) {
    DateFormat df = new SimpleDateFormat(DATE_PATTERN_SHORT_DT);
    return df.format(convertIntToDTTM(dateValue));
  }
  
  
  /**
   * 将当前时间转换成----年--月--日 中午
   * @param millis
   * @return
   */
  public static String getDateStr() {
    
    long millis = System.currentTimeMillis();
      Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        Formatter ft=new Formatter(Locale.CHINA);
        return ft.format("%1$tY年%1$tm月%1$td日 中午", cal).toString();
  }
  
  
  /**
   * 格式化时间为yyyy-MM-dd HH:mm:ss
   * */
  public static String formatToDTTMString(int dateValue) {
    DateFormat df = new SimpleDateFormat(DATE_PATTERN_NORMAL);
    return df.format(convertIntToDTTM(dateValue));
  }
  
  /**
   * 把String(yyyy-MM-dd)类型的时间格式化为Date
   * */
  public static Date formatStringToDateDT(String dtStr) {
    DateFormat df = new SimpleDateFormat(DATE_PATTERN_SHORT_DT);
    try {
      return df.parse(dtStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Input string " + dtStr + 
          " can not be parsed to Date correctly.", e);
    }
  }
  
  /**
   * 把String(yyyy-MM-dd HH:mm:ss)类型的时间格式化为Date
   * */
  public static Date formatStringToDateDTTM(String dtStr) {
    DateFormat df = new SimpleDateFormat(DATE_PATTERN_NORMAL);
    try {
      return df.parse(dtStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Input string " + dtStr + 
          " can not be parsed to Date correctly.", e);
    }
  }
  
  /**
   * 把String(yyyy-MM-dd)类型的时间转换为int(默认为指定天数0点的秒数)
   * */
  public static int decodeFromDTString(String dtStr) {
    return convertDTTMtoInt(formatStringToDateDT(dtStr));
  }
  
  /**
   * 把String(yyyy-MM-dd HH:mm:ss)类型的时间转换为int
   * */
  public static int getHourOfDayOfInt(String dtStr){
    
    Date date = formatStringToDateDTTM(dtStr);
    int value = convertDTTMtoInt(date);
    return value;
  }
  
  /**
   * 得到今天0点的时间
   * */
  public static int getTodayBeforeDawnTime() {
    return getHourOfDayTime(0);
  }
  
  /**
   * 得到今天12点的时间
   * */
  public static int getTodayHighNoonTime() {
    return getHourOfDayTime(HIGH_NOON_TIME);
  }
  
  /**
   * 得到今天19点的时间
   * */
  public static int getTodayAtDuskTime() {
    return getHourOfDayTime(AT_DUSK_TIME);
  }
  
  /**
   * 得到今天24点的时间
   * */
  public static int getTodayLateAtNightTime() {
    return getHourOfDayTime(24);
  }
  
  /**
   * 获得当前指定小时的秒数
   * */
  public static int getHourOfDayTime(int hourOfDay) {
    Calendar curr = Calendar.getInstance();
    
    curr.set(Calendar.HOUR_OF_DAY, hourOfDay);
    curr.set(Calendar.MINUTE, 0);
    curr.set(Calendar.SECOND, 0);
    return (int) (curr.getTimeInMillis() / 1000);
  }
  
  /**
   * 根据yyy-MM(年-月)时间格式获取一个月内的时间数组
   * @param String time(yyyy-MM)
   * @return Integer[指定时间开始这天0点的时间, 指定时间下个月当天的0点的时间]
   * */
  public static Integer[] getOneMonthTimeByYMStr(String time) {
    time = time.trim();
    String[] times = time.split("-");
    
    int year = Integer.parseInt(times[0]);
    int month = Integer.parseInt(times[1]);
    
    return getOneMonthTimeByYMD(year, month, 1);
  }
  
  /**
   * 获取指定年月日一个月内的秒数数组
   * @param int year, int month, int date
   * @return Integer[指定时间开始这天0点的时间, 指定时间下个月当天的0点的时间]
   * */
  public static Integer[] getOneMonthTimeByYMD(int year, int month, int date) {
    int beginTime = getTimeByYMD(year, month, date);
    month = month + 1;
    if (month > 12) {
      month = 1;
      year = year + 1;
    }
    int endTime = getTimeByYMD(year, month, date);
    
    return new Integer[]{beginTime, endTime};
  }
  
  /**
   * 获得指定年月日的秒数
   * @param int year, int month, int date
   * @return int
   * */
  public static int getTimeByYMD(int year, int month, int date) {
    Calendar curr = Calendar.getInstance();
    
    curr.set(year, month, date);
//    curr.set(Calendar.HOUR_OF_DAY, 0);
//    curr.set(Calendar.MINUTE, 0);
//    curr.set(Calendar.SECOND, 0);
    
    return (int) (curr.getTimeInMillis() / 1000);
  }
  
  /**
   * 时间转换
   * @param dateStr:"2014-03-30"格式的字符串
   * @param timeStr:"中午"或者"晚上"
   * @return 指定字符串的整数时间
   * */
  public static int getThisSystemTimeBySpecialDateStr(String dateStr, String timeStr) {
    dateStr = dateStr.trim();
    timeStr = timeStr.trim();
    int hourOfDay;
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    try {
       date = df.parse(dateStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Input string " + dateStr + 
          " can not be parsed to Date correctly.", e);
    }
    
    if (timeStr.equals("中午")) {
      hourOfDay = HIGH_NOON_TIME;
    } else {
      hourOfDay = AT_DUSK_TIME;
    }
    
    Calendar curr = Calendar.getInstance();
    curr.setTime(date);
    curr.set(Calendar.HOUR_OF_DAY, hourOfDay);
    
    return (int) (curr.getTimeInMillis() / 1000);
  }
  
  /**
   * 根据"年-月-日 中午"或者"年-月-日 下午"获得在该系统中指定的时间的秒数
   * */
  public static int getThisSystemTimeBySpecialDateStr(String dateStr) {
    dateStr = dateStr.trim();
    String datas[] = dateStr.replaceAll(" +", ">").split(">");
    dateStr = datas[0];
    int hourOfDay;
    
    // 得到yyyy-MM-dd的Date对象
    Date date = formatStringToDateDT(dateStr);
    
    if (datas[1].equals("中午")) {
      hourOfDay = HIGH_NOON_TIME;
    } else {
      hourOfDay = AT_DUSK_TIME;
    }
    
    Calendar curr = Calendar.getInstance();
    curr.setTime(date);
    curr.set(Calendar.HOUR_OF_DAY, hourOfDay);
    
    return (int) (curr.getTimeInMillis() / 1000);
  }
  
  /**
   * 根据"yyy年MM月dd日 中午"或者"yyy年MM月dd日 下午"获得在该系统中指定的时间的秒数
   * */
  public static int getThisSystemTimeBySpecialDateStrZh(String dateStr) throws Exception {
    dateStr = java.net.URLDecoder.decode(dateStr, "utf-8");
    dateStr = dateStr.trim();
    String dates[] = dateStr.replaceAll(" +", ">").split(">");
    
    return getThisSystemTimeBySpecialDateStrZh(dates[0], dates[1]);
  }
  
  /**
   * 时间转换
   * @param dateStr:"2014年03月30日"格式的字符串
   * @param timeStr:"中午"或者"晚上"
   * @return 指定字符串的整数时间
   * */
  public static int getThisSystemTimeBySpecialDateStrZh(String dateStr, String timeStr) {
    dateStr = dateStr.trim();
    timeStr = timeStr.trim();
    int hourOfDay;
    
    DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
    Date date;
    try {
       date = df.parse(dateStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Input string " + dateStr + 
          " can not be parsed to Date correctly.", e);
    }
    
    if (timeStr.equals("中午")) {
      hourOfDay = HIGH_NOON_TIME;
    } else {
      hourOfDay = AT_DUSK_TIME;
    }
    
    Calendar curr = Calendar.getInstance();
    curr.setTime(date);
    curr.set(Calendar.HOUR_OF_DAY, hourOfDay);
    
    return (int) (curr.getTimeInMillis() / 1000);
  }
  
  public static void main(String[] args) {
    /*String t = " 2014-09-27 中午   ";

    System.out.println(getTodayHighNoonTime());
    
    System.out.println(getThisSystemTimeBySpecialDateStr(t));
    
    t = " 2014-09-27 晚上   ";

    System.out.println(getTodayAtDuskTime());
    
    System.out.println(getThisSystemTimeBySpecialDateStr(t));
    */
    
    System.out.println(getDateStr());
  }
}
