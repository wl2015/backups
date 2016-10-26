<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>菜单列表管理界面</title>
<link rel="stylesheet" type="text/css" href="../css/right.css"></link>
<link rel="stylesheet" type="text/css" href="css/right.css"></link>


<script  language="javascript" type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script  language="javascript" type="text/javascript" src="../js/hover.js"></script>


<script type="text/javascript">
	function jumpage(){		
		this.form1.submit();		
	}
</script>

</head>
<body>
	<!-- 添加菜品 -->
	
	<table width="100%" cellspacing="2" cellpadding="4">
		<tr>
			<td colspan="15" class="header">菜品管理</td>
		</tr>
		<tr>
			<td class="td1" width="15%">菜品名</td>
			<td class="td1" width="10%">口味</td>
			<td class="td1" width="10%">价格</td>
			<td class="td1" width="30%">图片</td>
			<td class="td1" width="12%">修改</td>
			<td class="td1" width="13%">删除</td>
		</tr>
		<s:iterator var="di" value="dishList">
		<tr>
		<!-- 菜品名 -->
			<td class="td2"><s:property value="#di.dish_name"/></td>
		<!-- 菜品口味 -->
			<td class="td2"><s:property value="#di.dish_taste"/></td>
		<!-- 菜品价格 -->	
			<td class="td2"><s:property value="#di.price"/></td>
		<!-- 菜品图片 -->	
			<td class="td2"><img src="<%=path %>/<s:property value="#di.pic"/>"  width="150px" height="150px"/></td>
			<td class="td2">
				<s:a href="BackDishAction_getDishByid?dish.dish_id=%{#di.dish_id}&pageNum=%{pageNum}">修改</s:a>				
				
			</td>
			<td class="td2">
			<s:a href="BackDishAction_deleteDish?dish.dish_id=%{#di.dish_id}&pageNum=%{pageNum}" onclick="return window.confirm('确定要删除吗？')">删除该菜品</s:a>
				<!--<INPUT onClick="return window.confirm('确定要删除吗？')" type="submit" value="删除">  -->
			</td>
			
		</tr>
		</s:iterator >
	<tr>
	<td colspan="15" height="50">    
	<div style="float:right;margin-top:0px"> 
		   <form  id="form1" action="BackDishAction_getAllDish?pageNum=%{#pageNum}" method="post"> 
		   <s:select list="pageList" name="pageNum" listKey="key" listValue="value" label="转到" onchange="jumpage();" theme="simple"></s:select>
		   </form>
	 </div>
	 <div class="turn">转到：</div>
	 <div class="front">
	  		<s:if test=" pageNum != totalpage ">			
			<s:a href="BackDishAction_getAllDish?pageNum=%{pageNum+1}">下一页</s:a>
			</s:if>
	 </div>
	 <div class="numPage"><s:property value="pageNum"/></div>
	 <div class="front">
			<s:if test=" pageNum != 1">
			<s:a href="BackDishAction_getAllDish?pageNum=%{pageNum-1}">上一页</s:a>
			</s:if>
	</div>		
		
     </td></tr>
 	</table>
</body>
</html>