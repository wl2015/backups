<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myIF1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="css/menuList.css" />
 <link rel="stylesheet" type="text/css" href="css/layout.css" />
 <link rel="stylesheet" type="text/css" href="css/menuList_1.css" />
 <link rel="stylesheet" type="text/css" href="css/layout_1.css" />

  </head>
  
 <body style="Font-size:63%" leftmargin="0">

<%-- <div class="head">
	
		<div class="float_left pt-1"><img src="img/home-page.png" style="width:7em"/></div>
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyListAction_MyListDisplay')">我的订单</div>
		<!-- <div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyValueAction_NotDiscounted')">优惠券</div> -->
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
		<div class="float_left color-2 font-s-3 pl-1">我的资料</div>
</div>


<div class="body">
<s:form action="UserInfoAction_saveOrUpdatePhone" method="post">
<center>
	
<div class="body11">
	<div class="body22 font-s-3">输入手机号码
		<input type="text" value="${user.userName }" name="user.userName" />
	</div>
</div>

<div class="body11"></div>
<div class="border0"></div>	
<div class="pt-4 pb-3">
	<input type="submit" value="提交" name="Update" class="button" />
	<input type="button" value="返回" name="Back" class="button2" onclick="location.replace('UserInfoAction_UserInfo')"/>
</div>
	
	<s:hidden name="user.userId"></s:hidden>
	
</center>	
</s:form>
	
	<div style="clear:both"></div>
</div>

<div class="border"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div> --%>

<div class="head">
		<div class="float_left pt-1 shouye"><a href="../order/shouye.html" target="_parent">首页</a></div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('MyListAction_MyListDisplay')">订单</div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
		<div class="float-1 color-2 font-s-3 pl-2 pt-6">资料</div>
</div>
<s:form action="UserInfoAction_saveOrUpdatePhone" method="post" >
<div class="body">
	<div class="margin-4 font-s-2">输入手机号码</div>
	<div><input type="text" value="${user.user_name }" name="user.userName" class="input1 input-text2 margin-l-6" style="width:80%"/></div>
	<div style="height:12em;margin-top:-2em">
		<div><input type="submit" value="提交" name="Update" class="tijiao"/></div>
		<input type="button" value="返回" name="Back" class="fanhui" onclick="location.replace('UserInfoAction_UserInfo')"/>
	</div>
</div>
<s:hidden name="user.userId"></s:hidden>
</s:form>
<div class="footer">
	<iframe src="../order/footer.html" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div>



</body>
</html>
