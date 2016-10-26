<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>

  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
  <script type="text/javascript" src="res/js/jQuery/jquery.form.js"></script>
  <script type="text/javascript" src="res/js/admin/ajaxfileupload.js" ></script>
  <script type="text/javascript" src="res/js/admin/imgUpload.js"></script>
 
   <style type="text/css">  
  #preview{width:100px;height:100px;border:1px solid #000;overflow:hidden;}  
  #imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}  
  </style>  
  </head>
  
  <body>
  <div id="preview">  
      <img alt="图片预览" id="imghead" width=100 height=100 border=0 src="">  
  </div>  
  <br>
   <form action="" method="post" id="uploader" enctype="multipart/form-data">
    <input onchange="previewImage(this)" id="uploadField" type="file" name="file"/>
    <input type="hidden" name="dir" value="media"/>
  </form>
  <button id="upload" onclick="doUpload()">提交</button>
  <br><br><br><br><br><br>
  图片上传：
  
  </body>
</html>
