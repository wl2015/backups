package com.h5.basic.interceptors;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.h5.util.Constants;



public class AdminInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = Logger.getLogger(AdminInterceptor.class);
	
	private List<String> allowUrls;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String requestUrl = request.getRequestURI();
		
		if (requestUrl.startsWith(request.getContextPath() + "/res/")) {
			return true;
		}
        
        for(String url : allowUrls) {
        	
            if(requestUrl.endsWith(url)) {
            	if (log.isDebugEnabled()) {
            		log.debug("This url is marked as allowed:" + requestUrl);
            	}
                return true;  
            }
        }
 
        if(request.getSession().getAttribute(Constants.LOGINUSER_SESSION_NAME_ADMIN) != null) {  
            return true;
        } else {
        	
        	log.error("Session timout when admin access " + requestUrl);
        	
        	String hdAccep = request.getHeader("accept");
            String hdXReq = request.getHeader("X-Requested-With");
            
            if ( (hdAccep != null && hdAccep.indexOf("application/json") > -1) || (hdXReq != null && hdXReq.indexOf("XMLHttpRequest") > -1) ) { //如果是异步请求
            	//JSON格式返回 
            	JSONObject obj = new JSONObject();
            	obj.put("msg", Constants.SESSION_TIMEOUT_ALERT_MESSAGE);
            	obj.put("message", "登录信息超时，请先登录后再操作！");
            	
            	response.setCharacterEncoding(request.getCharacterEncoding());
            	response.setContentType("application/json");
                try {  
                    response.getWriter().println(obj.toString());  
                } catch (IOException e) {  
                	log.error("Exception happens when write error message to response.", e); 
                }   
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/login");
            }
            
            return false;
        }
	}

	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}

}
