<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="com.wedding321.utils.ErrorHandler" %>
<%@ page import="com.wedding321.upload.*" %>

<%
response.setContentType("text/html; charset=UTF-8");

if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError("请选择要上传的文件。"));
	return;
}


String fileType = request.getParameter("dir");

if (fileType == null) {
	//out.println(getError("未指定上传文件类型。"));
	fileType = "image";
}

List<String> fileAccessList = null;
try {
	fileAccessList = FileUploader.getInstance().saveUploads(request, fileType);
	JSONObject obj = new JSONObject();
	obj.put("error", 0);	
	obj.put("url", fileAccessList.get(0));	
	
	out.println(obj.toString());
	System.out.println(obj.toString());
	
	
} catch(UploadException ex) {
	ErrorHandler.logError("Fail to upload file in upload_json.jsp", ex);
	out.println(getError(ex.getShowMessage()));
}

%>

<%!
private String getError(String message) {
	return ErrorHandler.createErrorJSONInfo(message).toString();
}
%>