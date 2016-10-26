package com.yc.util;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

import javax.servlet.http.HttpServletResponse;

 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.yc.bean.Dish;

 
 
 
public class StringUtil extends StringUtils {
     
    private static Log log = LogFactory.getLog(StringUtil.class);
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

 
    /**
     * 本方法封装了往前台设置的header,contentType等信息
     * @param message           需要传给前台的数据
     * @param type              指定传给前台的数据格式,如"html","json"等
     * @param response          HttpServletResponse对象
     * @throws IOException
     * @createDate 2010-12-31 17:55:41
     */
    public static void writeToWeb(String message, String type, HttpServletResponse response) throws IOException{
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/" + type +"; charset=utf-8");
        response.getWriter().write(message);
        response.getWriter().close();
    }
    
    
    public static Gson gson;   
  
    public static List<Dish> jsonToList(String jsonStr){
      if(gson==null){
        gson=new Gson();
    }
      List<Dish> objList=null;
      if(gson!=null){
          java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<List<Dish>>(){}.getType();
          objList=gson.fromJson(jsonStr, type);
      }
      return objList;
  }

}
