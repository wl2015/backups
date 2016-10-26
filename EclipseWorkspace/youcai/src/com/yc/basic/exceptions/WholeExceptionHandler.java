package com.yc.basic.exceptions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 * @author Administrator
 *@param ex 系统抛出的异常
 */
public class WholeExceptionHandler implements HandlerExceptionResolver{

  protected Logger logger = Logger.getLogger(this.getClass());

  public ModelAndView resolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) { 
    //handler就是处理器适配器要执行的Handler对象（只有method）  
      
//      全局异常处理器处理思路：
//      解析出异常类型
//      如果该 异常类型是系统自定义的异常，直接取出异常信息，在错误页面展示  
//      如果该 异常类型不是系统 自定义的异常（运行异常runtime ），构造一个自
//
//    定义的异常类型（信息为"未知"）
      
     CustomException customException = null;
     SessionTimeoutException sessionTimeoutException = null;
     String errorMsg = null;
     
     
      if (ex instanceof CustomException) {
          customException = (CustomException)ex; 
          errorMsg = customException.getMessage();
      }else if (ex instanceof SessionTimeoutException) {
          sessionTimeoutException = (SessionTimeoutException)ex;
          errorMsg = sessionTimeoutException.getMessage();
      }else {
         customException = new CustomException("系统未知错误！请联系管理人员");
         errorMsg = customException.getMessage();
      }
      
      //错误信息
     
      
      ModelAndView modelAndView = new ModelAndView();
      
      //将错误信息传到页面
      modelAndView.addObject("errorMsg",errorMsg);
      
      //指向错误页面
      modelAndView.setViewName("WholeExceptionError");
      
      return modelAndView;
  }
}