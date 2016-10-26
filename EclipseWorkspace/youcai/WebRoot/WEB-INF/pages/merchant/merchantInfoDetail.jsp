<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/mInfo.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.min.js" ></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.form.js" ></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/merchantInfo.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/common.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/inputcheck.js"></script> 
</head>
<body>
			<div class="inner">
				<div class="div1">店铺信息</div>
				<div class="div2">
					<strong>店铺名称:</strong><span>${merchant.shopName}</span>
					
				</div>
				<div class="div2">
					<strong>店铺地址:</strong><span>${merchant.merchantAddress}</span>
					
				</div>
				<div class="div2">
					<strong>店铺名称:</strong><span>${merchant.shopName}</span>
					
				</div>
				<div class="div2">
					<strong>店铺联系人:</strong><span>${merchant.bossName}</span>
					
				</div>
				<div class="div2">
					<strong>店铺联系电话:</strong><span>${merchant.linkPhone}</span>
				</div>
					
				<div class="div2">
					<strong>店铺邮箱:</strong><span>${merchant.merchantMail}</span>
					
				</div>
				<div class="div3">
					<strong class="strong3">店铺简介:</strong>
					<span id="textIntro">${merchant.merchantIntro}</span>
					
					<!-- 弹出的编辑框 -->
					<div class="showEdit">
						<div class="cancel">取消修改</div>
						<textarea></textarea>
						<span class="showNum">还可以输入<strong>200</strong>字</span>
						<div class="sure">确定</div>
					</div>
				</div>
				<div class="div4">
					<span id="showmodifyIntro-BTN">编辑店铺简介</span>
					<span id="showModifyPass-BTN">修改密码</span>
					<!-- 弹出的修改密码框 -->
					<form id="modifyPassForm">
					<div class="modifyPassword">
						 <div class="cancel">取消修改</div>
						 <span><label for="name">原密码</label></span><input type="text" id="oldPassword" name="oldPassword"><br/>
						 <span><label for="name">新密码</label></span><input type="text" id="newPassword" name="newPassword" /><br/>
						 <span><label for="name">确认新密码</label></span><input type="text" id="repeatNewPassword" name="repeatNewPassword"/><br/>
						 <div id="modifyPass-BTN">确认修改</div>
					</div>
					
					</form>
				</div>
				</div>
			
</body>
</html>