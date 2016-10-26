<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.wedding321.image.CutImag" %>
<%@ page import="com.wedding321.utils.ErrorHandler" %>

<% 
/**
 * 创建用户图标头像
 * 
 */

	JSONObject info = null;
	try {
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		int w = Integer.parseInt(request.getParameter("w"));
		int h = Integer.parseInt(request.getParameter("h"));
		int mappingImgW = Integer.parseInt(request.getParameter("yuanW"));
		int mappingImgH = Integer.parseInt(request.getParameter("yuanH"));
		String imgPath = request.getParameter("imgPath");
		final String uploaderAppUrl = getServletContext().getContextPath() + "/";

		if (!imgPath.startsWith(uploaderAppUrl)) {
			throw new IllegalArgumentException("input image path does not belong to this server: " + imgPath);
		}
		
		int level2UrlEndIdx = imgPath.indexOf('/', uploaderAppUrl.length());
		if (level2UrlEndIdx > uploaderAppUrl.length()) {
			String level2Url = "/" + imgPath.substring(uploaderAppUrl.length(), level2UrlEndIdx);
			String rootPath = getServletContext().getRealPath(level2Url);
			if (rootPath == null) {
				throw new IllegalArgumentException("Can not find level 2 url path ('" + level2Url + "') for image in this server: " + imgPath);
			}
			String imgAbsPath = rootPath + imgPath.substring(level2UrlEndIdx);
			String logoFileUrlFromLevel2 = CutImag.saveToLogoVersion(imgAbsPath, x, y, w, h, mappingImgW, mappingImgH);
			info = new JSONObject();
			info.put("result", 0);
			info.put("url", getServletContext().getContextPath() + logoFileUrlFromLevel2);
		}
		else {
			throw new IllegalArgumentException("input image path does not belong to this server: " + imgPath);
		}
	}
	catch(Exception ex) {
		ErrorHandler.logError("Exception occurs when create user logo image.", ex);
		info = ErrorHandler.createErrorJSONInfo(ex.getMessage());
	}
	
	out.println(info.toString());
%>