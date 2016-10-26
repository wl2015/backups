<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
  <link rel="stylesheet" type="text/css" href="../css/layout.css" />
   <link rel="stylesheet" type="text/css" href="css/menuList.css" />
  <link rel="stylesheet" type="text/css" href="css/layout.css" />
  <script language="javascript"></script>
<title>开始点餐</title>
<script  type="text/javascript"> 
	function checkNewAddress(){
		var address=document.getElementById("t1").value;
		var name=document.getElementById("t2").value;
		var phone=document.getElementById("t3").value;
		if(name == "" || address == "" || phone == ""){
			alert("必选项不能为空");
			return false;
		}
		if(isNaN(phone)){
			alert("手机号必须为数字");
			return false;
		}
		
		return true;
	}

</script>
</head>
<body style="Font-size:63%" leftmargin="0">

<div class="head">
<iframe src="head.html" width="100%" frameborder="0"></iframe>
</div>
<s:form action="addressAction_saveOrUpdate" method="post" onSubmit="return checkNewAddress()">
<div class="margin">
	<div class="font-s-2">选餐地址（必选）</div>
	<div style="font-size:16px;color:blue;margin-top:10px"><s:property  value="%{fronAddress.front_address}"/></div>
	  			<!-- 隐藏的传值用法 -->
	<s:hidden name="userInfo.frontaddress" value="%{fronAddress.front_address}"></s:hidden>
				<!-- 想获取用户的ID -->
	<s:hidden   name="userInfo.user_id" value="%{#session.loginUser.user_id}"></s:hidden>
	<a href="choose_first2.jsp" style="color:white;"><div class="border-8">选择送餐地址</div></a>
	<div class="border-6"></div>
	<div class="font-s-2">补全地址信息（必填）</div>
	<input type="text" class="input-text input1" name="userInfo.add_address" id="t1"/>
	<div class="font-s-1">（门牌号、楼名、单元、楼层、房间号、公司名等）</div>
	<div class="border-6"></div>

	<div class="font-s-2">食客姓名（必填）</div>
	<input type="text" class="input-text input1" name="userInfo.name" id="t2"/>
	<div class="border-6"></div>

	<div class="font-s-2 pb-1">食客性别</div>
	<div class="font-s-2">
	<s:radio list="#{'1':'先生', '0':'女士'}" name="userInfo.sex" listKey="key" listValue="value" value="1" theme="simple"></s:radio>
	</div>
	<div class="border-6"></div>

	<div class="font-s-2">联系电话（必填）</div>
	<div><input type="text" class="input-text input1" name="userInfo.link_phone" id="t3"/></div>
	
	<div style="height:12em;margin-top:-2em">
		<div>
		<input type="submit" value="提交"   class="tijiao"/>
		</div>
		<div class="fanhui"  onclick="location.href='first.jsp'">返回</div>
	</div>
</div>
</s:form>
	<div class="footer">
	<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no leftMargin="0"></iframe>
	</div>
</body>
</html>