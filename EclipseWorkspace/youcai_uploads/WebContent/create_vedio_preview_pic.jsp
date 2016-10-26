<%@page import="com.wedding321.vedio.YoukuVedioPreviewCreater"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.wedding321.upload.UploadException"%>
<%@page import="com.wedding321.utils.ErrorHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
PrintWriter writer = response.getWriter();
String previewPath;
String basePath = request.getContextPath();
System.out.println(request.getParameter("vedioUrl"));
if (request.getParameter("vedioUrl") == null) {
	writer.print(ErrorHandler.getUnknownWebSiteResponse(basePath));
	writer.flush();
	return ;
}
String rootPath = application.getRealPath("/");
try {
	YoukuVedioPreviewCreater creater = new YoukuVedioPreviewCreater(rootPath, request.getParameter("vedioUrl"));
	try {
		String fileFullPath = creater.genarateVedioPreview();
		previewPath = basePath + "/" + fileFullPath;
		System.out.println("previewPath:" + previewPath);
		writer.print(previewPath);
	} catch(Exception ex) {
		writer.print(creater.getGenarateFailedPreviewPic(basePath));
		writer.flush();
	}
} catch(Exception e) {
	writer.print(ErrorHandler.getUnknownWebSiteResponse(basePath));
	writer.flush();
	return ;
}
%>