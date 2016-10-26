package com.cn.freemall.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.freemall.bean.UserBean;
import com.cn.freemall.service.UserService;


@Controller
@RequestMapping("/test")
public class TestController {
  @Inject
  public UserService userService;
  
  @RequestMapping(value="/toTest")
  public String toTest(){
    return "/test/startPage";
  }
  
  /***
   * Ajax传值测试
   * */
  @RequestMapping(value="/ajaxtest",method=RequestMethod.POST)
  @ResponseBody
  public UserBean ajaxtest(String state,String name)throws Exception{
    System.out.println(state+name);
    UserBean userBean = new UserBean();
    userBean.setName("wla");
    return userBean;
  }
}
