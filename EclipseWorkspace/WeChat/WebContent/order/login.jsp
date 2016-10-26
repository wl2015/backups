<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
  <link rel="stylesheet" type="text/css" href="../css/other.css" />
   <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
<title>开始点餐</title>

<script  type="text/javascript"> 
	function checklogin(){
		var name=document.getElementById("textfield").value;
		var pass=document.getElementById("textfield2").value;
		if(name == "" || pass== ""){
			alert("用户名和密码不能为空");
			return false;
		}
		if(isNaN(name)){
			alert("用户名必须为数字");
			return false;
		}
		return true;
	}
</script>
</head>
<body style="Font-size:63%" leftmargin="0" topmargin="0">
<form name="loginForm" action="LoginAction_isLogined" method="post" onSubmit="return checklogin()">
	<div class="bodys-1">
		<div class="name">手机</div>
		<div><input type="text" class="input-name" id="textfield" name="user.user_name"/></div>
		<div class="name">密码</div>
		<div><input type="password" class="input-name"  id="textfield2" name="user.password"/></div>
		<div class="regist" onclick="location.href='regist.jsp'">注册</div>
		<div><input type="submit" name="Submit" value="登录" class="denglu" onclick=""></div>
		<div style="height:10em"></div>
		<div class="footer">
		<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
		</div>
	</div>
</form>
</body>
</html>