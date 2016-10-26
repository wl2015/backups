package com.yc.basic.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 测试拦截器
 * @author Administrator
 *
 */
public class HandlerInterceptTest implements HandlerInterceptor{

    //进入Handler方法之前执行
    //用于身份认证、身份授权（查资料？）
    //比如身份,如果不通过表示用户没有登录，需要此方法拦截不再向下执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {

      //获取请求的url
        String url = request.getRequestURI();
        //判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
        //这里公开地址是登陆提交的地址
        if(url.indexOf("login.action")>=0){
          //如果进行登陆提交，放行
          return true;
        }
        
        //判断session
        HttpSession session  = request.getSession();
        //从session中取出用户身份信息
        String username = (String) session.getAttribute("username");
        
        if(username != null){
          //身份存在，放行
          return true;
        }
        
        //执行这里表示用户身份需要认证，跳转登陆页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        
        //return false表示拦截，不向下执行
        //return true表示放行
        return false;
    }

    //进入Handler方法之后，返回ModelAndView之前执行
    //应用场景从 ModelAndView出发：将公用的模型数据在这里传到视图（如公共导航），
    //也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptTest。。。postHandle");
        
    }

    //执行Handler后，执行此方法
    //应用场景：统一的异常处理，同一日之处理
    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("HandlerInterceptTest。。。afterCompletion");

    }

}
