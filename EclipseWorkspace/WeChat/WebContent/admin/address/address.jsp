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
    
    <title>My JSP 'address.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/right.css">
	<script type="text/javascript" src="js/hover.js"></script>
	<script src="js/jquery-1.4.2.min.js"></script>
	<!-- 点击修改地址后，则，出现文本框， -->
	<script type="text/javascript">
	function xiugai(a,b,c,d){
		var s=document.getElementById(c).value;
		if(s=="修改"){
			$("#"+d).val($("#"+a).html());
			document.getElementById(a).style.display="none";
			document.getElementById(b).style.display="block";
			document.getElementById(b).value=document.getElementById(a).innerHtml;
			document.getElementById(c).value="保存";
		}
		else if(s=="保存"){
			$.ajax({
				url:'AddressAction_UpdateFrontaddress',
				type:'post',
				dataType:'json',
				data:{
					frontaddressId:d,frontaddress:$("#"+d).val()
				},
				success:function(res){
					alert("修改成功");
				}
			});
			$("#"+a).html($("#"+d).val());
			document.getElementById(a).style.display="block";
			document.getElementById(b).style.display="none";
			document.getElementById(c).value="修改";	
		}
	}
	function deleteF(df){
			$.ajax({
				url:'AddressAction_DeleteFrontAddress',
				type:'post',
				dataType:'json',
				data:{
					frontaddressId:df
				},
				success:function(res){
					$("#f"+df).hide();
					alert("删除成功");
				}
			});
		}
	function addfrontaddress(){
			
			$.ajax({
				url:'AddressAction_AddFrontAddress',
				type:'post',
				dataType:'json',
				data:{
					frontaddress:$("#address").val()
				},
				success:function(res){
					$("#shuru").hide();
					alert("添加成功");
					location.reload();
				}
			});
	}
	function quxiao(){
		$("#shuru").hide();
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
		<table width="100%" cellspacing="2" cellpadding="2" bgcolor="#fbead0">
			<tr>	
				<td class="header" style="width:100%" colspan="3">
					<span style="width:40%">地址管理</span>
					<form action="${pageContext.request.contextPath}/AddressAction_AddressShow">
					<span  style="width:30%;text-align:center">
						<span>请输入查询地址：</span>
						<span><input name="keyword" type="text" value="${keyword }"/></span>
						<input type="text" style="display: none" name="state" value="1"/>
						<span><input type="submit" value="查询"></span>
					</span>
					</form>
					<span  style="width:30%;text-align:right">
						<input type="button" value="添加地址" onclick="add('shuru');"/>
					</span>
				</td>
			</tr>
			<tr>
				<td class="td1-1">地址名称</td>
				<td class="td1">修改地址</td>
				<td class="td1">删除地址</td>
			</tr>
			<tr style="display:none" id="shuru">
				<td class="td1-1">
					<span><input type="text" size="70%" style="font-size:16px" id="address"/></span>
				</td>
				<td class="td1"><input type="button" value="确认" onclick="addfrontaddress()"/></td>
				<td class="td1"><input type="button" value="取消" onclick="quxiao();"/></td>
			</tr>
			<s:iterator var="f" value="frontaddresslist">
			<tr id="f${f.frontaddress_id }">
				<td class="td2-2">
				<span id="a${f.frontaddress_id }">${f.front_address }</span>
				<span style="display:none" id="b${f.frontaddress_id }">
					<input type="text" size="70%" style="font-size:16px" id="${f.frontaddress_id }" />	
				</span>
				</td>
				<td class="td2"><input type="button" value="修改" onclick="xiugai('a${f.frontaddress_id }','b${f.frontaddress_id }','c${f.frontaddress_id }','${f.frontaddress_id }');" id="c${f.frontaddress_id }"/></td>
				<td class="td2"><input type="button" onclick="deleteF('${f.frontaddress_id }')" value="删除"/></td>
			</tr>
			</s:iterator>
			<tr>
			<td align="center" colspan="15"  height="50">
			<div>
				<form name="form1" action="${pageContext.request.contextPath}/AddressAction_AddressShow">
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
				<input style="display: none" name="keyword" value="${keyword }"/>
				<input style="display: none" name="state" value="${state }"/>
				<div class="front"><a href="javascript:void(0)" onclick="reducepageCount()">上一页</a></div>
				</form>
			</div>
			</td>
		</tr>
		</table>
	</body>
</html>
