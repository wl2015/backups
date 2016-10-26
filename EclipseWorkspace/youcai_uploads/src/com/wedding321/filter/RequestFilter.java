package com.wedding321.filter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.wedding321.utils.ErrorHandler;

public class RequestFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(RequestFilter.class);

	private static final String paramName = "tempContextUrl";
	
	private static final String UEDITOR_JSP_NAME = "upload_editor_file.jsp";
	
	private static String preTempContextUrl;
	
	private static List<String> uploadJspNames;
	
	/**
	 * 需要更好的过滤
	 * */
	
	@Override
	public void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("uploader requestFilter destroy");
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chan) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String requestUrl = request.getHeader("Referer");
		String requestUri = request.getRequestURI();
		String requestJspPageName = requestUri.substring(requestUri.lastIndexOf("/") + 1);
		
		if (UEDITOR_JSP_NAME.equals(requestJspPageName)) {
			chan.doFilter(req, resp);
			return ;
		}
		
		// 循环判断如果是上传则过滤掉非本站发送的请求
		for (String uploadJspName : uploadJspNames) {
			if (requestJspPageName.equals(uploadJspName)) {
				
				if (isAllowedRequest(requestUrl)) {
					chan.doFilter(req, resp);
					return ;
				} else {
					logger.error("[外部网站的上传请求,请求地址:" + requestUrl + "]");
					return ;
				}
			}
		}
		
		// 请求图片
		if (requestUrl != null) {
			// 下载图片请求
			
			if (isAllowedRequest(requestUrl)) {
				chan.doFilter(req, resp);
				return ;
			} else {
				logger.error("[外部网站的图片请求,请求地址:" + requestUrl + "]");
				PrintWriter writer = resp.getWriter();
				writer.print(ErrorHandler.getUnknownWebSiteResponse(request.getContextPath()));
				writer.flush();
				return ;
			}
		} else {
			// 针对文本编辑框的文件下载处理
//			System.out.println("请求有问题:" + request.getRequestURI());
//			String requestHost = request.getHeader("host");
//			logger.error("[外部网站的图片请求,请求host:" + requestHost + "]");
//			PrintWriter writer = resp.getWriter();
//			writer.print(ErrorHandler.getUnknownWebSiteResponse(request.getContextPath()));
//			writer.flush();
			chan.doFilter(req, resp);// 没有实现图片下载防盗链功能
			return ;
		}
		
	}

	private boolean isAllowedRequest(String requestUrl) {
		
		if(requestUrl == null) {
			return false;
		}
		
		int httpHeaderLength = 7;
		String tempContextUrl = requestUrl.substring(httpHeaderLength, requestUrl.indexOf("/", httpHeaderLength));
		
		if (tempContextUrl.equals(preTempContextUrl)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		File pathRootFile = new File(config.getServletContext().getRealPath("/"));
		uploadJspNames = new ArrayList<String>();
		for (File f : pathRootFile.listFiles()) {
			if (f.isFile()) {
				uploadJspNames.add(f.getName());
			}
		}
		preTempContextUrl = config.getInitParameter(paramName);
	}

}
