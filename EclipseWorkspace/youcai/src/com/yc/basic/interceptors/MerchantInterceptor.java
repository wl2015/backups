package com.yc.basic.interceptors;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yc.util.Constants;


/**
 * 商家登录拦截器
 * @author Paul Iverson
 *
 */
public class MerchantInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = Logger.getLogger(MerchantInterceptor.class);
	
	private List<String> allowUrls;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI();
		if (requestUrl.startsWith(request.getContextPath() + "/res/")){
			return true;
		}
		if (requestUrl.startsWith(request.getContextPath() + "/easyui/")){
      return true;
    }
		
		for(String url : allowUrls){
			if (requestUrl.endsWith(url)){
				return true;
			}
		}
		
		if (request.getSession().getAttribute(Constants.LOGIN_SESSION_MERCHANT) != null){
			return true;
		}else{
			log.debug("Session timout when merchant access " + requestUrl+"\n\n\n");
        	
        	String hdAccep = request.getHeader("accept");
            String hdXReq = request.getHeader("X-Requested-With");
            if ( (hdAccep != null && hdAccep.indexOf("application/json") > -1) || (hdXReq != null && hdXReq.indexOf("XMLHttpRequest") > -1) ) { //如果是异步请求
            	//JSON格式返回 
            	JSONObject obj = new JSONObject();
            	obj.put("code", Constants.AJAX_SESSION_TIMEOUT_CODE);
            	obj.put("msg", Constants.SESSION_TIMEOUT_ALERT_MESSAGE);
            	//obj.put("message", "登录信息超时，请先登录后再操作！");
            	
            	response.setCharacterEncoding(request.getCharacterEncoding());
            	response.setContentType("application/json");
                try {
                    response.getWriter().println(obj.toString());  
                } catch (IOException e) {  
                	log.error("Exception happens when write error message to response.", e); 
                }
            } else {
            	log.debug("\n\n\n\n-----redirct");
                response.sendRedirect(request.getContextPath() + "/merchant/toMerchantLogin.do");
            }
            
            return false;
		}
	}
	
	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}
}
