package com.h5.basic.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class WholeExceptionHandler implements HandlerExceptionResolver{

	protected Logger logger = Logger.getLogger(this.getClass());

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> parameters = getErrorMessage(ex);
        
        String hdAccep = request.getHeader("accept");
        String hdXReq = request.getHeader("X-Requested-With");
        
        if ( (hdAccep != null && hdAccep.indexOf("application/json") > -1) || (hdXReq != null && hdXReq.indexOf("XMLHttpRequest") > -1) ) { //如果是异步请求
        	//JSON格式返回 
        	JSONObject obj = new JSONObject();
        	obj.put("code", parameters.get("code"));
        	obj.put("msg", parameters.get("msg"));
        	
        	response.setCharacterEncoding(request.getCharacterEncoding());
        	response.setContentType("application/json");
            try {
                response.getWriter().println(obj.toString());  
            } catch (IOException e) {  
            	logger.error("Exception happens when write error message to response.", e); 
            }
            return new ModelAndView();  
        } else {// 如果不是异步请求  
            // Apply HTTP status code for error views, if specified.  
            // Only apply it if we're processing a top-level request.  
        	return new ModelAndView(getNextPage(ex), parameters); 
        }
    }
    
    private Map<String, Object> getErrorMessage(Exception ex) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	if (ex instanceof SessionTimeoutException) {
    		/*parameters.put("code", Constants.AJAX_SESSION_TIMEOUT_CODE);
    		parameters.put("msg", Constants.SESSION_TIMEOUT_ALERT_MESSAGE);*/
    	} else if (ex instanceof ValidateFailException) {
    		logger.error("WholeExceptionHandler capture an exception", ex);
    		/*parameters.put("code", Constants.VALIDATE_FAILE_CODE);
    		parameters.put("msg", Constants.EXCEPTION_ALERT_MESSAGE);*/
    	} else {  // default exception message
        	logger.error("WholeExceptionHandler capture an exception", ex);
        	/*parameters.put("code", Constants.AJAX_EXCEPTION_CODE);
    		parameters.put("msg", Constants.EXCEPTION_ALERT_MESSAGE);*/
    	}
    	
    	return parameters;
    }
    
    private String getNextPage(Exception ex) {
    	if (ex instanceof SessionTimeoutException) {
    		return "redirect:login";
    	} else {  // default exception page
    		return "redirect:/500";
    	}
    }


}
