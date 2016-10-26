<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myIF.jsp' starting page</title>
    
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


	
  </head>
  
  <body style="Font-size:63%" leftmargin="0">

<%-- 
 <div class="head">
		<div class="float_left pt-1"><img src="img/home-page.png" style="width:7em"/></div>
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyListAction_MyListDisplay')">我的订单</div>
		<!-- <div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyValueAction_NotDiscounted')">优惠券</div> -->
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
		<div class="float_left color-2 font-s-3 pl-1">我的资料</div>
</div>
<div class="body">
<center>

	<c:forEach var="u" items="${requestScope.userinfolist }" varStatus="status">
<div class="body11">
<div class="body20 font-s-3">用户名：${u.name }</div>
<div class="body21 font-s-3"></div>

</div>
<div class="body11"></div>
<div class="border0"></div>	
<div class="body11">
<div class="body20 font-s-3">注册时间：</div>
<div class="body21 font-s-3">${u.registTime }</div>

</div>
<div class="body11"></div>
<div class="border0"></div>
<div class="body11">
<div class="body20 font-s-3">手机：</div>
<div class="body21 font-s-3">${u.userName }</div>
<div class="body3 font-s-3">
<a href="${pageContext.request.contextPath }/UserInfoAction_getUseridPhone?userId=${u.userId}" style="color:blue;font-size:16px">绑定手机</a></div>

</div>
<div class="body11"></div>
<div class="border0"></div>	
<div class="body11">
<div class="body20 font-s-3">Email：</div>
<div class="body21 font-s-3">${u.email }</div>
<div class="body3 font-s-3">
<a href="${pageContext.request.contextPath }/UserInfoAction_getUserid?userId=${u.userId}" style="color:blue;font-size:16px">编辑邮箱</a></div>

</div>
<div class="body11"></div>
<div class="border0"></div>
<div class="body11">
<div class="body20 font-s-3">密码：</div>
<div class="body21 font-s-3">*********</div>
<div class="body3 font-s-3">
<a href="${pageContext.request.contextPath }/UserInfoAction_getUseridPass?userId=${u.userId}" style="color:blue;font-size:16px">修改密码</a></div>
	
</div>
	</c:forEach>
<div class="border0"></div>	
<div class="body11"></div>
</center>		
	<div style="clear:both"></div>
</div>
<div class="border"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin-top:0px;padding:0px"></iframe>
</div> --%>

<div class="head">
		<div class="float_left pt-1 shouye"><a href="../order/shouye.html" target="_parent">首页</a></div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('MyListAction_MyListDisplay')">订单</div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
		<div class="float-1 color-2 font-s-3 pl-2 pt-6">资料</div>
</div>
<div class="body">
<center>
<c:forEach var="u" items="${requestScope.userinfolist }" varStatus="status">
	<div class="body5">
	<div class="body6 font-s-3">用户名:</div>
	<div class="body7 font-s-3">${u.name }</div>
	</div>
	<div class="body5"></div>
	<div class="border0"></div>	

	<div class="body5">
	<div class="body6 font-s-3">注册时间:</div>
	<div class="body7 font-s-3">${u.regist_time }</div>
	</div>
	<div class="body5"></div>
	<div class="border0"></div>

	<div class="body5">
	<div class="body6 font-s-3">手机:</div>
	<div class="body7 font-s-3">${u.user_name }</div>
	<div class="body8 font-s-3"><a href="${pageContext.request.contextPath }/UserInfoAction_getUseridPhone?userId=${u.user_id}" style="color:blue;font-size:16px">绑定手机</a></div>
	</div>

	<div class="body5"></div>
	<div class="border0"></div>	
	
	<div class="body5">
	<div class="body6 font-s-3">Email:</div>
	<div class="body7 font-s-3">${u.email }</div>
	<div class="body8 font-s-3"><a href="${pageContext.request.contextPath }/UserInfoAction_getUserid?userId=${u.user_id}" style="color:blue;font-size:16px">编辑邮箱</a></div>
	</div>

	<div class="body5"></div>
	<div class="border0"></div>

	<div class="body5">
	<div class="body6 font-s-3">密码:</div>
	<div class="body7 font-s-3">*********</div>
	<div class="body8 font-s-3"><a href="${pageContext.request.contextPath }/UserInfoAction_getUseridPass?userId=${u.user_id}" style="color:blue;font-size:16px">修改密码</a></div>
	</div>
	
	<div class="body5"></div>
	<div class="border0"></div>	
	</c:forEach>
</center>		
	<div class="footer">
	<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
	</div>
</div>

</body>
</html>
