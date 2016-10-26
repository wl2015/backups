<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送员修改界面</title>
<link rel="stylesheet" type="text/css" href="../css/right.css">
<link rel="stylesheet" type="text/css" href="css/right.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function getMes(){
	if(document.getElementById('mes').value!='') {
		alert(document.getElementById('mes').value);
	}
}


function checkMobile(){ 
    var sMobile = document.getElementById("telephone").value;
    if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){ 
        alert("不是完整的11位手机号或者正确的手机号前七位"); 
        document.getElementById("telephone").focus(); 
        return false; 
    }
    return true; 
} 
function checkeForm(){
	if(document.getElementById("name").value ==''){
 		
 		alert("姓名不能为空");
	   	return false;
 	}
 	if(document.getElementById("telephone").value ==''){
 		alert("联系方式不能为空");
	   	return false;
 	}
 	if(document.getElementById("weiXNumber").value==''){
 		alert("微信号不能为空");
	   	return false;
 	}
 	
    //return false;
	return checkMobile();
}
</script>
</head>
<body onload="getMes();">
   	<s:form action="SendPeopleAction_updateSendpeople.do?pageNum=%{pageNum}" method="post">
       <table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">
			<div style="float:left">配送员管理</div>
			<div style="float:right"><s:a href="SendPeopleAction_getallPeople.do?pageNum=%{pageNum}">返回配送员列表</s:a></div>
			</td>
		</tr>
		
		<tr>
			<td class="td3">姓名:</td>
			<td class="td4"><s:textfield id="name" name="operSendPeople.name" size="30" maxlength="30" theme="simple"/></td>
		</tr>
		<tr>
			<td class="td3">性别:</td>
			<td class="td4"><s:radio name="operSendPeople.sex" list="%{#{'男':'男','女':'女'}}" value="operSendPeople.sex" theme="simple" /></td>
		</tr>
		<tr>
			<td class="td3">联系方式:</td>
			<td class="td4"><s:textfield id="telephone" name="operSendPeople.linkWay" size="30" maxlength="30" theme="simple"/></td>
		</tr>
		<tr>
			<td class="td3">微信号:</td>
			<td class="td4"><s:textfield id="weiXNumber" name="operSendPeople.weiXNumber" size="30" maxlength="30" theme="simple"/></td>
		</tr>
		<tr>
			<td class="td3"></td>
			<td class="td4">
				<s:submit name="commit" value="修改" onclick="return checkeForm();"/> 
			</td>
		</tr>

	</table>
	 <s:hidden name="operSendPeople.peopleId" value="%{operSendPeople.peopleId}"></s:hidden>
     </s:form>
</body>
</html>