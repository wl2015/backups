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
<title>商家主页</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/mInfo.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js" ></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.form.js" ></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/merchantIndex.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/merchantInfo.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/common.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/inputcheck.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>res/js/calendar.js" ></script>

</head>
<body>
	<audio id="myaudio" hidden="true">
	  <source src="<%=basePath %>res/forYoucai.mp3" type="audio/mpeg">
	  <source src="<%=basePath %>res/forYoucai.wav" type="audio/wav">	
   </audio>   
	
	<div class="header">
		<span>
			<a href="<%=basePath %>merchant/backToLogin.do">退出登录</a>
		</span>
		<span class="welcome">
			欢迎来到有菜~~~
		</span>
	</div>
	<!-- 双飞翼布局 -->
	
	<div class="content">
	    <!-- center -->
	    <div class="middle">
			<div class="inner">
				<%-- <iframe src="<%=basePath %>merchant/getMerchantInfo.do" scrolling="no" frameBorder="0" height="100%" width="100%" name="center"></iframe> --%>
				<div class="div1">店铺信息</div>
				<div class="div2">
					<strong>名称：</strong><span>${merchant.shopName}</span>
					
				</div>
				<div class="div2">
					<strong>地址：</strong><span>${merchant.merchantAddress}</span>
					
				</div>
				<div class="div2">
					<strong>联系人：</strong><span>${merchant.bossName}</span>
					
				</div>
				<div class="div2">
					<strong>联系电话：</strong><span>${merchant.linkPhone}</span>
				</div>
					
				<div class="div2">
					<strong>邮箱：</strong><span>${merchant.merchantMail}</span>
					
				</div>
				<div class="div3">
					<strong class="strong3">简介：</strong>
					<span id="textIntro">${merchant.merchantIntro}</span>
					
					<!--弹出的编辑框   --> 
					<div class="showEdit">
						<div class="cancel">取消修改</div>
						<textarea rows="" cols=""></textarea>
						<span class="showNum">还可以输入<strong>200</strong>字</span>
						<div class="sure">确定</div>
					</div>
				</div>
				<div class="div4">
					<span id="showmodifyIntro-BTN">编辑店铺简介</span>
					<span id="showModifyPass-BTN">修改密码</span>
				</div>
				
				<!-- 弹出的修改密码框 -->
					<form id="modifyPassForm">
					<div class="modifyPassword">
						 <div class="cancel">取消修改</div>
						 <span><label for="name">原密码</label></span><input type="password" id="oldPassword" /><br/>
						 <span><label for="name">新密码</label></span><input type="password" id="newPassword"  /><br/>
						 <span><label for="name">确认新密码</label></span><input type="password" id="repeatNewPassword" /><br/>
						 <div id="modifyPass-BTN">确认修改</div>
					</div>
					
					</form>
			</div>
		</div>
		<!--  -->
		<div class="left">

		<form method="post" id="fileForm" enctype="multipart/form-data">
			<div class="mImage">
				<img src="${merchant.merchantLogo}"  title="点击图标修改商标" id="logo"/>
			</div> 
			<input type="file" id="Uploader_img"  name="Upload_img" style="display: none;"/>

		</form>

			<div class="list">
				
				<ul>
					<li>
						<a href="javascript:void(0)" onclick="centerSet('<%=basePath %>merchant/toMessage.do')">系统消息</a>
						<img class="img1" src="<%=basePath%>res/img/letter.png"/>
						<span class="letterText" id="letterNum">${messageNumber }</span>
					</li>
					<li>
						<a href="javascript:void(0)" onclick="centerSet('<%=basePath%>merchant//toRefundMessage.do')">客户退单消息</a>
						<img class="img2" src="<%=basePath%>res/img/message.png"/>
						<span class="letterText" id="refundNum">${refundMessageNumber }</span>
					</li>
					<li><a href="javascript:void(0)" onclick="centerSet('<%=basePath%>merchant/toMerchantHistoryOrder.do')">历史订单</a></li>
					<li><a href="javascript:void(0)" onclick="centerSet('<%=basePath%>merchant/toMerchantProcesOrder.do')">未完成订单</a></li>
					<li>
						<a href="javascript:void(0)" onclick="centerSet('<%=basePath%>merchant/toMerchantPushOrder.do')">抢订单</a>
						<img class="img3" src="<%=basePath%>res/img/message.png"/>
						<span class="letterText" id="pushOrderNum">${pushOrderNumber }</span>
					</li>
					<li><a href="javascript:void(0)" onclick="centerSet('<%=basePath%>merchant/toMerchantInventory.do')">库存管理</a></li>
					<li><a href="javascript:void(0)" onclick="centerSet('<%=basePath%>merchant/toMerchantAdvance.do')">查看销售额</a></li>
				</ul>
			</div>
		</div>
		<div class="right">
		<div class="img">
				<img src="<%=basePath%>res/img/logo.png">
			</div>

		</div>
	</div>
	
	<div class="footer">
		<div class="version">
			<p>版权申明....版权申明....版权申明....版权申明....版权申明....版权申明....
			版权申明....版权申明....版权申明....版权申明....版权申明....</p>
		</div>
	</div>
</body>
</html>