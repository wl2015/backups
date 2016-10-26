package com.yc.basic.interceptors;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yc.bean.Limits;
import com.yc.util.Constants;


/**
 * 管理员后台登录拦截器
 * @author Administrator
 *
 */
public class AdminLoginIntercept implements HandlerInterceptor{
    
    
    //商家管理员
    private static final String[] MANAGEMER_ADMIN_IGNORE_URI = {"/manageMer/show.do", "/manageMer/showVerify.do",
        "/manageMer/queryMerchants.do","/manageMer/queryMerchantsByKeyWords.do", "/manageMer/getMerchantById.do", 
        "/manageMer/getVerifyMerchantById.do","/manageMer/deleteMerchant.do", "/manageMer/queryVerifyMerchants.do",
        "/manageMer/verifyMerchant.do","/adminLogin.do","/adminIndex.do"};
    //菜品管理员
    private static final String[] DISH_ADMIN_IGNORE_URI = {"/dish/show.do", "/dish/queryDishByKeywords.do",
        "/dish/add.do","/dish/getDishById.do", "/dish/queryDishs.do", "/dish/addDish.do",
        "/dish/deleteDish.do", "/dish/updateDish.do","/adminIndex.do"};
    //评论管理员
    private static final String[] COMMENT_ADMIN_IGNORE_URI = {"/comment/addComment.do", "/comment/goodShow.do",
        "/comment/badShow.do","/comment/doAddComment.do", "/comment/updateComment.do", "/comment/deleteComment.do","/adminIndex.do"};
    //公告管理员
    private static final String[] MESSAGE_ADMIN_IGNORE_URI = {"/message.do", "/writeMessage.do","/adminIndex.do"};
    //出货管理员
    private static final String[] SALES_ADMIN_IGNORE_URI = {"/sales.do", "/addSales.do","/adminIndex.do"};
    //财务管理员
    private static final String[] FINANCE_ADMIN_IGNORE_URI = {"/finance.do", "/queryFinance.do","/queryFinance.do",
         "/createAdvanceList.do","/queryRefundOrderList.do","/getRefundOrder.do","/payCreateAdvance.do","/payRefundOrder.do","/adminIndex.do"};
    //出纳管理员
    private static final String[] ADVANCE_ADMIN_IGNORE_URI = {"/advance.do", "/queryAdvance.do",
        "/queryAdvanceDetail.do","/createAdvance.do","/adminIndex.do"};
    
    
    private static final Logger log = Logger.getLogger(AdminLoginIntercept.class);
    
    private List<String> allowUrls;
    
    int temp = 0;//0代表找到url ；1代表没找到url

    //进入Handler方法之前执行
    //用于身份认证、身份授权（查资料？）
    //比如身份,如果不通过表示用户没有登录，需要此方法拦截不再向下执行
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {

        //获取请求的url
        String requestUrl = request.getRequestURI();
        //判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
        for(String url : allowUrls){
            if (requestUrl.endsWith(url)){
              return true;
            }
        }
        
        //如果session值没又管理员信息，增跳转到登录界面
        if (request.getSession().getAttribute(Constants.LOGIN_SESSION_ADMIN) != null){
            //权限判断
            List<Limits> limitList =  (List<Limits>)request.getSession().getAttribute(Constants.ADMIN_LIMITS);
            for (Limits limits : limitList) {
                if (limits.getLimitId() == Constants.LIMITS_SUPERADMIN) {//如果是超级管理员
                    return true;
                }
                else if (limits.getLimitId() == Constants.LIMITS_MERCHANTADMIN) {//如果是商家管理员
                    for (String s : MANAGEMER_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }else if (limits.getLimitId() == Constants.LIMITS_DISHSADMIN) {//如果是菜品管理员
                    for (String s : DISH_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }else if (limits.getLimitId() == Constants.LIMITS_COMMENTSADMIN) {//如果是评论管理员
                    for (String s : COMMENT_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }else if (limits.getLimitId() == Constants.LIMITS_SALESADMIN) {//如果是出货管理员
                    for (String s : SALES_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }else if (limits.getLimitId() == Constants.LIMITS_FINANCEADMIN) {//如果是财务管理员
                    for (String s : FINANCE_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }else if (limits.getLimitId() == Constants.LIMITS_MESSAGEADMIN) {//如果是公告管理员
                    for (String s : MESSAGE_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }else if (limits.getLimitId() == Constants.LIMITS_ADVANCEADMIN) {//如果是预付款管理员
                    for (String s : ADVANCE_ADMIN_IGNORE_URI) {
                        if (requestUrl.contains(s)) {
                            temp = 0;
                           return true;
                        }
                        else {
                            temp = 1;
                        }
                     }
                }
            }
            
            if (temp == 1) {
                //跳转权限拦截页面
                request.getRequestDispatcher("/WEB-INF/pages/limitsError.jsp").forward(request, response);
            }
          }else{
              //页面内部跳转
              //request.getRequestDispatcher("/WEB-INF/pages/admin/adminLogin.jsp").forward(request, response);
              //页面重定向
              //response.sendRedirect("/youcai/admin/login.do");
              String hdAccep = request.getHeader("accept");
              String hdXReq = request.getHeader("X-Requested-With");// X-Requested-With请求头用于在服务器端判断request来自Ajax请求还是传统请求。
              if ( (hdAccep != null && hdAccep.indexOf("application/json") > -1) 
                      || (hdXReq != null && hdXReq.indexOf("XMLHttpRequest") > -1) ) { //如果是异步请求
                //JSON格式返回 
                JSONObject obj = new JSONObject();
                obj.put("code", Constants.AJAX_SESSION_TIMEOUT_CODE);
                obj.put("msg", Constants.SESSION_TIMEOUT_ALERT_MESSAGE);
                
                response.setCharacterEncoding(request.getCharacterEncoding());
                response.setContentType("application/json");//返回json
                
                  try {
                      response.getWriter().println(obj.toString());//直接将json内容在页面显示  
                  } catch (IOException e) {  
                    log.error("Exception happens when write error message to response.", e); 
                  }
              }else {
                log.debug("\n\n\n\n-----redirct");
                  
                request.getRequestDispatcher("/WEB-INF/pages/admin/adminLogin.jsp").forward(request, response);
              }
              log.debug("url被拦截器拦截，跳转到登录页面");
          }
        
        //return false表示拦截，不向下执行
        //return true表示放行
        return false;
    }

    //进入Handler方法之后，返回ModelAndView之前执行
    //应用场景从 ModelAndView出发：将公用的模型数据在这里传到视图（如公共导航），
    //也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("AdminIntercept。。。postHandle");
        


    }

    //执行Handler后，执行此方法
    //应用场景：统一的异常处理，同一日之处理
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        //System.out.println("AdminIntercept。。。afterCompletion");
       
    }

    
    public void setAllowUrls(List<String> allowUrls) {
        this.allowUrls = allowUrls;
    }
}
