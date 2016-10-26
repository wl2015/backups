<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
  <link rel="stylesheet" type="text/css" href="../css/other.css" />
   <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
  <script  language="javascript" type="text/javascript" src="../js/order.js"></script>
<title>开始点餐</title>

<script  type="text/javascript"> 
	function checkRegist(){
		var name=document.getElementById("textfield").value;
		var pass=document.getElementById("textfield2").value;
		var pass2=document.getElementById("textfield3").value;
		if(name == "" || pass== "" || pass2== ""){
			alert("手机号和密码不能为空");
			return false;
		}
		if(isNaN(name)){
			alert("手机号必须为数字");
			return false;
		}
		if(pass != pass2){
			alert("两次密码必须相同");
			return false;
		}
		return true;
	}
</script>
</head>
<body style="Font-size:63%" leftmargin="0">
	<div class="head2">
	<iframe src="head2.html" width="100%" frameborder="0" scrolling=no ></iframe>
	</div>
	<div class="bodys-1">
	
	<form action="RegistAction_isRegist" method="post" onSubmit="return checkRegist()">
		<div class="name">手机</div>
		<div><input type="text" class="input-name" name="user.user_name" id="textfield"/></div>
		<div class="name">密码</div>
		<div><input type="password" class="input-name" name="user.password" id="textfield2"/></div>
		<div class="name">确认密码</div>
		<div><input type="password" class="input-name" name="passWord2" id="textfield3"/></div>
		<div class="regist" onclick="location.href='login.jsp'" >返回</div>
		<div><input type="submit" value="注册" class="denglu" /></div>
		<div style="height:10em"></div>
		<div class="footer">
		<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
		</div>
	</form>
	</div>
</body>
</html>