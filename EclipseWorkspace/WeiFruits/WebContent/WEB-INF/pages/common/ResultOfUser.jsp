<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
</head>
<body>
  <h3 id="error_info">${result}，<a href="<%=path%>/user/toHome">点击这里</a>返回</h3>
  
  <script>
/*     function UserError(){
      var user_iframe = window.parent.document.getElementById('iframepage');
      $('a').attr('href', user_iframe.src);
    } */
  </script>
</body>
</html>