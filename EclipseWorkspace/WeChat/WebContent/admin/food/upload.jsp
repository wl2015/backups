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
<title>添加菜品页面</title>
<link  type="text/css" rel="stylesheet" href="../../css/right.css">
<link  type="text/css" rel="stylesheet" href="css/right.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function getMes(){
	if(document.getElementById('mes').value!='') {
		alert(document.getElementById('mes').value);
	}
	
}
function yulan(){	
	var filePath =document.getElementById('myFile').value;
	//alert(filePath);
	var fileText =filePath.substring(filePath.lastIndexOf("."),filePath.length);
	var fileName =fileText.toLowerCase();
	//alert(fileName);
	if ((fileName!='.jpg')&&(fileName!='.gif')&&(fileName!='.jpeg')&&(fileName!='.png')&&(fileName!='.bmp'))
	{
		alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 ！");
		document.form1.myFile.focus();
				
	}
}
function chckeForm(){
	//alert("mmmmmmmmmmmmmmmm");
	//alert(document.getElementById("name").value);
 	if(document.getElementById("name").value ==''){
 		//alert("mmmmmmmmmmmmmmmm1");
 		alert("菜品名不能为空");
	   	return false;
 	}
 	if(document.getElementById("taste").value ==''){
 		alert("口味不能为空");
	   	return false;
 	}
 	if(document.getElementById("price").value==''){
 		alert("价格不能为空");
	   	return false;
 	}
 	if(document.getElementById("myFile").value==''){
 		alert("图片不能为空");
	   	return false;
 	}
 	if(document.getElementById("intro").value==''){
 		alert("菜品介绍不能为空");
	   	return false;
 	}
    
    
	if(isNaN(document.getElementById("price").value)){
	   	alert("价格必须是数字");
	   	return false;
	 }
	   
	  return true;
}  
</script>
</head>
<body onload="getMes();">
 <center>  

  <s:form id="form1" action ="BackDishAction_uploadDish" method ="POST" enctype ="multipart/form-data" theme="simple">  
  <table width="100%" cellspacing="2" cellpadding="4">
		<tr>
			<td colspan="15" class="header">添加菜品</td>
		</tr>
		<tr>
			<td class="td3">菜品名:</td>
			<td class="td4"><s:textfield id="name" name="dish.dish_name" size="30" maxlength="30" theme="simple"/></td>
		</tr>
		<tr>
			<td class="td3">口味:</td>
			<td class="td4"> <s:textfield id="taste" name="dish.dish_taste" size="30" maxlength="30" theme="simple"/></td>
		</tr>
		<tr>
			<td class="td3">价格:</td>
			<td class="td4"> <s:textfield id="price" name="dish.price" size="30" maxlength="30" theme="simple"/></td>
		</tr>
		<tr>
			<td class="td3">图片:</td>
			<td class="td4"> <s:file id="myFile" name ="myFile" size="30" theme="simple" onchange="yulan();"/>  </td>
			
		</tr>
		
		<tr>
			<td class="td3">菜品介绍:</td>
			<td class="td4">
				 <s:textarea  id="intro" name="dish.dish_intro" rows="5" cols="30" theme="simple"/>
			</td>
		</tr>
		<tr>
			<td class="td3"></td>
			<td class="td4">
			<s:submit value="添加" theme="simple" onclick="return chckeForm();"/>	
					
			</td>
		</tr>
	</table>
        
          
    </s:form>    
  
	<s:hidden id="mes" name="returnMes"/>
 </center>


</body>
</html>