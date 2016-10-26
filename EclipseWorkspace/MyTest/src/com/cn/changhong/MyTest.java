package com.cn.changhong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyTest {
  public String aa(){
    String aa="";
    return aa;
  }
  public static void main(String[] args) {
    String date="";
    if(date == null || date.isEmpty()){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date nowDate = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(nowDate);
      calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-1);
      date = format.format(calendar.getTime());
    }
    System.out.println(date);
    
    List<String> list = new ArrayList<>();
    for(String a : list){
      System.out.println(a);
    }
  }
}