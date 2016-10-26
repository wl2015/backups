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
<link rel="stylesheet" type="text/css" href="../css/right.css">
<link rel="stylesheet" type="text/css" href="css/right.css"></link>

<title>配送员列表管理</title>



<script type="text/javascript">
	function jumpage(){	
		//alert("nnnnn");	
		this.form1.submit();		
	}
</script>
</head>
<body>

	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">配送员管理</td>
		</tr>
		<tr>
			<td class="td1" width="10%">序号</td>
			<td class="td1" width="15%">姓名</td>
			<td class="td1" width="10%">性别</td>
			<td class="td1" width="23%">联系方式</td>
			<td class="td1" width="22%">微信号</td>
			<td class="td1" width="10%">修改</td>
			<td class="td1" width="10%">删除</td>
		</tr>
		 <s:iterator var="sp" value="sendPeopleList" status="status">
          <tr>
			<td class="td2"><s:property value="#status.count"/></td>
			<td class="td2"><s:property value="#sp.name"/></td>
			<td class="td2"><s:property value="#sp.sex"/></td>
			<td class="td2"><s:property value="#sp.linkWay"/></td>
			<td class="td2"><s:property value="#sp.linkWay"/></td>
			<td class="td2">
				<s:a href="SendPeopleAction_getsendPeopleById.do?operSendPeople.peopleId=%{#sp.peopleId}&oper=update&pageNum=%{pageNum}"><s:property value="'修改'"/></s:a>
			</td>
			<td class="td2">
			<s:a href="SendPeopleAction_deleteSendPeople.do?operSendPeople.peopleId=%{#sp.peopleId}&pageNum=%{pageNum}" onclick="return window.confirm('确定要删除吗？')"><s:property value="'删除'"/></s:a>				
			</td>

		</tr>
		 </s:iterator>
		<tr> 
		<td colspan="15"  height="50">
		<div style="float:right;">
			<s:form id="form1" theme="simple">
			<span><s:select list="pageList" name="pageNum" listKey="key" listValue="value" onchange="jumpage();" theme="simple"></s:select></span>
			</s:form>
		</div>	
      	<div class="turn">转到：</div>   	
      	<div class="front">
	      	<s:if test=" pageNum != totalpage ">			
			<s:a href="SendPeopleAction_getallPeople?pageNum=%{pageNum+1}">下一页</s:a>
			</s:if>
		</div>
      	<div class="numPage"><s:property value="pageNum"/></div>
      	<div class="front">
			<s:if test=" pageNum != 1">			
			<s:a href="SendPeopleAction_getallPeople?pageNum=%{pageNum-1}">上一页</s:a>
			</s:if>
		</div>
			
		
			
		
		</td>
       
        </tr>	
	</table>
</body>
</html>