package com.yc.basic.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yc.bean.User;

public class UserLoginInterceptor implements HandlerInterceptor{
  private static final String[] IGNORE_URI = {"/userLogin.jsp", "/toOrderDish.do",
    "/orderDish.jsp","/chooseSendAddress.jsp", "/userRegist.jsp", "/toLogin.do",
    "/toRegist.do", "/toChooseSendAddress.do","/userRegist.do", "/isExsit.do", 
    "/putAddressInformationIntoSession.do","/updateShoppingCar.do", "/userLogin.do",
    "/toForgetPassword.do","/forgetPassword.jsp","/toSendProveMessage.do",
    "/toFindPassword.do","/findPassword.jsp","/saveNewPassword.do",
    "/successForFindPassword.jsp","/clearShoppingCar.do","/toUserLogin.do",
    "/getMessageCode.do","/getDishOrDishGroupListByTypeName.do",
    "/getAllDishes.do","/getAllDishGroups.do"};
  @Override
  public void afterCompletion(HttpServletRequest arg0,
      HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
      Object arg2, ModelAndView arg3) throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object object) throws Exception {
    boolean flag = false;
    String url = request.getRequestURL().toString();
    for (String s : IGNORE_URI) {
      if (url.contains(s)) {
        flag = true;
        break;
      }//if (url.contains(s))
    }//for (String s : IGNORE_URI)
    if (!flag) {
      Cookie[] cookies = request.getCookies();
      Cookie myCookie=null;
      for(int i=0;i<cookies.length;i++) {
        if(cookies[i].getName().equals("userId")) {
          myCookie=cookies[i];
          break;
         }
        }
      if (myCookie != null) {
        flag = true;
      }//if (user.getUserName() != null)
      else{
        request.getRequestDispatcher("/WEB-INF/pages/user/userLogin.jsp").forward(request, response);
        //response.sendRedirect(request.getContextPath()+"/userLogin.jsp");
      }
    }//if (!flag)
    return flag;
  }
}