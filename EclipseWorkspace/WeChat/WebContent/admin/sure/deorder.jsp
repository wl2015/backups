<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'deorder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/right.css">
</head>
	<script src="js/jquery-1.4.2.min.js"></script>
	<script  language="javascript" type="text/javascript">
	function change1(s1,s2){
		document.getElementById(s1).style.color="white";
		document.getElementById(s2).style.color="#9c460a";
		}
		function change2(s1,s2){
		document.getElementById(s2).style.color="white";
		document.getElementById(s1).style.color="#9c460a";
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
  <body>
  	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr  class="header">
			<td colspan="5">
				<span style="font-size:15px">
				<a href="${pageContext.request.contextPath}/CommonAction_getDoneOrderList?deorderstate=0" style="color:white" onclick="change1('s1','s2')" id="s1">当天出货单</a>
				</span>
				<span>&nbsp;&nbsp;</span>
				<span style="font-size:15px">
				<a href="${pageContext.request.contextPath}/CommonAction_getDoneOrderList?deorderstate=1" onclick="change2('s1','s2')" id="s2">查看全部出货单</a>
				</span>
			</td>
			<td colspan="3" align="right" >
			<form action="${pageContext.request.contextPath}/CommonAction_getDoneOrderList">
			<select name="deorderstate">
				<option value="2"selected>按日期查询</option> 
				<option value="3">按用户名查询</option>
			</select>
			<input type="text" value="用户名/日期" name="querycondition" class="input1"
			onfocus="if(this.value=='用户名/日期'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='用户名/日期')
			{this.value='用户名/日期';this.style.color='gray';}"/>
			<input type="submit" value="查询" class="mr"/>
			</form>
			</td>
			
		</tr>
		<tr>
			<td class="td1" width="5%">订单名</td>
			<td class="td1" width="10%">用户名</td>
			<td class="td1" width="10%">食客</td>
			<td class="td1" width="10%">联系方式</td>
			<td class="td1" width="23%">地址(前缀)</td>
			<td class="td1" width="16%">生成时间</td>
			<td class="td1" width="16%">送达时间</td>
			<td class="td1" width="10%">配送员</td>
		</tr>
		<s:iterator var="d" value="doneorderlist">
		<tr>
			<td class="td2"><a href="detailD.html" target="main">${d.orderformId }</a></td>
			<td class="td2">${d.userName }</td>
			<td class="td2">${d.name }</td>
			<td class="td2">${d.linkPhone }</td>
			<td class="td2">${d.frontAddress }</td>
			<td class="td2">${d.orderTime }</td>
			<td class="td2">${d.sendTime }</td>
			<td class="td2">${d.sendName }</td>
		</tr>
		</s:iterator>
		<tr>
			<td align="center" colspan="15"  height="50">
			<div>
				<form name="form1" action="${pageContext.request.contextPath}/CommonAction_getDoneOrderList">
				<div style="float:right;margin-top:1px">
					<select onchange="jumpPage()" id="jump">
						<option value="0"selected>请选择</option>
						<s:iterator var="page" value="pagemessagelist">
							<option value="${page.count }">${page.countmessage }</option>
						</s:iterator>
					</select>
				</div>
				<div class="turn">转到：</div>
				<div class="front"><a href="javascript:void(0)" onclick="addpageCount('${pageMax}')">下一页</a></div>
				<div class="numPage" id="pageConut">${pageConut }</div>
				<input id="pageConut2" style="display: none" name="pageConut" value="${pageConut }"/>
				<input style="display: none" name="deorderstate" value="${deorderstate }"/>
				<input style="display: none" name="querycondition" value="${querycondition }"/>
				<div class="front"><a href="javascript:void(0)" onclick="reducepageCount()">上一页</a></div>
				</form>
			</div>
			</td>
		</tr>
	</table>
  </body>
</html>
