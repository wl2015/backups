package com.yc.basic.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SiteUrlInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SiteUrlInterceptor.class);
	
	private static final String[] needChangeUrl = {
		".html", ".htm", ".xml", ".json"
	};
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String requestServletPath = request.getServletPath();
		logger.debug("\n\n\n----path: " + requestServletPath);
		if (requestServletPath.startsWith("/res/")) {
			return true;
		}
		
		for (String end : needChangeUrl) {
			if (requestServletPath.endsWith(end)) {
				requestServletPath = requestServletPath.replace(end, "");
				request.getRequestDispatcher(requestServletPath).forward(request, response);
				return false;
			}
		}
		
		return true;
	}
	
}
