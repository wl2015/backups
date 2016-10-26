<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家登录</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/mLogin.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/login.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/inputcheck.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/common.min.js"></script>
</head>

<body>
	<div class="mLogin">
		<div class="left">
			<img src="<%=basePath%>res/img/logo.png"/>
		</div>
		<div class="right">
				<div class="box">
					<span><label for="name">商户名</label></span><input type="text" name="name" id="name" placeholder="登录手机号" onKeyup="value=value.replace(/[^\d]/g,'');" />
				</div>
				<div class="box">
					<span><label for="password">密码</label></span><input type="password" name="password" id="password" placeholder="请输入密码" />
				</div>				
			<span class="spanL"><input type="submit" value="登录" class="SUBMIT_BTN"/></span>
			<span class="spanA">
				<a href="<%=basePath%>merchant/toResetPassword.do">忘记密码？</a>
				<a href="<%=basePath%>merchant/toMerchantRegist.do">去注册</a>
			</span>
			
		
		</div>
	</div>
</body>
</html>
