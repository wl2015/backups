package com.yc.controller;

import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yc.bean.TestUser;
import com.yc.service.TestUserService;
import com.yc.util.Constants;
import com.yc.util.InfoMation;
import com.yc.util.ResultCode;


/**
 *
 *用户控制层 
 * @author 
 *
 */
@Controller
@RequestMapping("/test")
public class TestUserController {

  @Autowired
  private TestUserService userService;

  //登录测试页面
  @RequestMapping("/test")
  public String test(HttpServletRequest request) {
    return "testLogin";
  }
  //注册测试页面
  @RequestMapping("/regist")
  public String regist(HttpServletRequest request) {
    return "testRegist";
  }

  /**
   * 用户注册
   * 
   * @param request
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping("/userRegist")
  @ResponseBody
  // 返回json
  public Map<String, Object> userRegist(HttpServletRequest request)
      throws Exception {
    TestUser user = new TestUser();
    Map<String, Object> result = new HashMap<String, Object>();
    
    String phone = request.getParameter("phoneNum");
    String password = request.getParameter("passWord");
    String nickname = request.getParameter("nickName");
    
    System.out.println("用户手机号" + phone + "密码" + password + "姓名:" + nickname);
    

//    User u = userService.queryUserforName(phone);//根据账号查找该用户是否存在
//    if (u != null) {
//      result.put("data", ResultCode.SUCCESS);
//      result.put("respcode", ResultCode.SUCCESS);
//      result.put("errorcode", ResultCode.USEREXIST);
//      result.put("message", "!");
//    } else {
      user.setU_phone(phone);
      user.setU_password(password);
      user.setU_name(nickname);
     
      
      try {
        userService.userRegist(user);
        result.put("data", ResultCode.SUCCESS);
        result.put("respcode", ResultCode.SUCCESS);
        result.put("errorcode", "");
        result.put("message", "注册成功");
      } catch (Exception e) {
        e.printStackTrace();
        result.put("data", "");
        result.put("respcode", ResultCode.FAIL);
        result.put("errorcode", ResultCode.FAIL);
        result.put("message", "注册失败!");
      }
    
    return result;
  }

  /**
   * 用户登录
   * 
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "userLogin")
  public String userLogin(HttpServletRequest request)
      throws Exception {
    
    TestUser user = new TestUser();
    
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
    user.setU_phone(phone);
    user.setU_password(password);
    TestUser u = userService.queryUserForPhone(phone);
    if (u != null) {

      TestUser users = userService.userLogin(user);
      
      if (users != null) {
        request.getSession().setAttribute("user", users);
        System.out.println(users.getU_id());
        System.out.println(users.getU_phone());
        return "testSuccess";
      } else {
        System.out.println("登录异常");
        return "testLogin";
      }

    } else {
     System.out.println("查无此人");
     return "testLogin";
    }

  }


}
