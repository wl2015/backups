<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'sure.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/right.css">
	<script src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
		function choose(orderformId){
			var a = parseInt($("#"+orderformId).val());
			if(a==0){
				alert("请选择配送员");
			}else{
				choosesendpeople(orderformId,a);
			}
		}
		function choosesendpeople(orderformId,peopleId){
		$.ajax({
				url:'CommonAction_chooseSendPeople',
				type:'post',
				dataType:'json',
				data:{
					orderformId:orderformId,peopleId:peopleId
				},
				success:function(res){
					alert("打印成功");
				}
		});
		}
	</script>
	<!-- 分页跳转 -->
	<script type="text/javascript">
		function reducepageCount(){
			if(parseInt($("#pageConut").html())>1){
				$("#pageConut2").val(parseInt($("#pageConut").html())-1);
				document.form1.submit();
			}	
		}
		function addpageCount(pageMax){
			if(parseInt($("#pageConut").html())<pageMax){
				$("#pageConut2").val(parseInt($("#pageConut").html())+1);
				document.form1.submit();
			}
		}
		function jumpPage(){
			$("#pageConut2").val(parseInt($("#jump").val()));
			document.form1.submit();
		}
	</script>
  </head>
  
  <body>
  <table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">已确认订单</td>
		</tr>
		<tr>
			<td class="td1" width="12%">订单名</td>
			<td class="td1" width="12%">食客</td>
			<td class="td1" width="12%">联系方式</td>
			<td class="td1" width="22%">地址(前缀)</td>
			<td class="td1" width="18%">送达时间</td>
			<td class="td1" width="12%">选择配送员</td>
			<td class="td1" width="12%">打印订单</td>
		</tr>
		<s:iterator var="o" value="ordermessagelist">
		<tr>
			<td class="td2"><s:a href="SuredAction_getSuredInfoById?orderformId=%{#o.orderformId}"><s:property value="#o.orderformId"/></s:a></td>
			<td class="td2">${o.name }</td>
			<td class="td2">${o.linkPhone }</td>
			<td class="td2">${o.frontaddress }</td>
			<td class="td2">${o.sendTime }</td>
			<td class="td2">
				<select id="${o.orderformId }">
					<option value="0"selected>请选择</option>
					<s:iterator var="p" value="choosesendpeoplelist">
						<option value="${p.peopleId }">${p.name }</option>
					</s:iterator>
				</select>
			</td>
			<td class="td2">
				<INPUT type="button" value="打印" onClick="choose('${o.orderformId }')"/>
			</td>
		</tr>
		</s:iterator>
		
		<tr>
			<td align="center" colspan="15"  height="50">
			<form name="form1" action="${pageContext.request.contextPath}/CommonAction_getEnsureOrderList">
			<div>
				<div style="float:right;margin-top:1px">
					<select onchange="jumpPage()" id="jump">
					<option value="0"selected>请选择</option>
					<s:iterator var="page" value="pagemessagelist">
						<option value="${page.count }">${page.countmessage }</option>
					</s:iterator>
					</select>
				</div>
				<div class="turn">转到：</div>
				<div class="front"><a onclick="addpageCount('${pageMax}')" href="javascript:void(0)">下一页</a></div>
				<div class="numPage" id="pageConut">${pageConut }</div>
				<input id="pageConut2" style="display: none" name="pageConut" value="${pageConut }"/> 
				<div class="front"><a onclick="reducepageCount()" href="javascript:void(0)">上一页</a></div>
			</div>
			</form>
			</td>
		</tr>
		
	</table>
  </body>
</html>
