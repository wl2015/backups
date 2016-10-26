<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath %>>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="res/css/user/successForApplyRefund.css">
</head>
<body>
    <div class="box">
    <div class="success">修改密码成功！</div>
    <div class="timeOut">
      <span id="time">5</span>
      秒后自动跳转到登录页面
    </div>
</body>
<script>
  function fun (){
    var num = parseInt(document.getElementById("time").innerHTML);
    if(num > 0){
      window.setTimeout("fun()",1000);
      document.getElementById("time").innerHTML = num - 1;
    }
    else{
      window.location='<%=path%>/user/toLogin.do';
    }
  }
  fun();
</script>
</html>