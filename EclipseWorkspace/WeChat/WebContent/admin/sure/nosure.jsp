<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'nosure.jsp' starting page</title>
    <meta http-equiv=refresh content="10" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/right.css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	 <!--<script type="text/javascript" src="js/fresh.js"></script>-->

	<script type="text/javascript">
		function jumpage(){		
	
		this.form1.submit();		
	}
	</script>
	
  </head>
  <body>  
	<table id="itemContainer" width="100%" cellspacing="2" cellpadding="4">
		 <tr>
			<td colspan="15" class="header">未确认订单</td>
		</tr>
		
		<tr>
			<td class="td1">订单号</td>
			<td class="td1">食客姓名</td>
			<td class="td1">联系方式</td>
			<td class="td1">生成时间</td>
			<td class="td1">送餐时间</td>
			<td class="td1">确认订单</td>
			<td class="td1">删除订单</td>
		</tr>
		
		<s:iterator var="o" value="orderform">
		<tr>
			<td class="td2"><s:a href="WaiteSureAction_getOrderInfoById?orderformId=%{#o.orderformId}&pageNum=%{pageNum}"><s:property value="#o.orderformId"/></s:a></td>
			<td class="td2"><s:property value="#o.name"/></td>
			<td class="td2"><s:property value="#o.linkPhone"/></td>
			<td class="td2">20<s:property value="#o.orderTime"/></td>
			<td class="td2">20<s:property value="#o.sendTime"/></td>
				
			<td class="td2">
			<s:a href="WaiteSureAction_UpdateOrder?orderid=%{#o.orderformId}&pageNum=%{pageNum}">
				<INPUT onClick="return window.confirm('要确认订单吗？')" type="submit" value="确认">
			</s:a>
			</td>
			<td class="td2">
		
			<s:a href="WaiteSureAction_DeleteOrderformById?orderformId=%{#o.orderformId}&pageNum=%{pageNum}">
				<INPUT onClick="return window.confirm('确定要删除吗？')" type="submit" value="删除">
			</s:a>
			</td>
		</tr>
		</s:iterator>
		
		<tr>
			<td align="center" colspan="15"  height="80">
			<div style="border: solid, 1px, red;  float:right">
			<s:a href="WaiteSureAction_execute?pageNum=1"><input type="button" value="首页" style="border:none"/></s:a>
			<s:if test=" pageNum != 1">
			
			<s:a href="WaiteSureAction_execute?pageNum=%{pageNum-1}">上一页</s:a>
			</s:if>
			<font size=4 ><s:property value="pageNum"/></font>
			
			<s:if test=" pageNum != totalpage ">			
			<s:a href="WaiteSureAction_execute?pageNum=%{pageNum+1}">下一页</s:a>
			</s:if>
			
			<s:a href="WaiteSureAction_execute?pageNum=%{totalpage}"><input type="button" value="尾页" style="border:none"/></s:a>
	
	 </div>
	 
	<div style="border: solid, 1px, red; margin-right:20px; float:right">
      <font size="2">共<s:property value="totalnum"/>条
                    <!-- 每页<s:property value="pageSize"/>条 -->
                     页次：<s:property value="pageNum"/>/<s:property value="totalpage"/></font>
                     <form  id="form1" action="WaiteSureAction_execute?pageNum=%{#pageNum}" method="post">
   						<div> <s:select list="pageList"  name="pageNum"  listKey="key"  listValue="value"  label="转到" onchange="jumpage();"></s:select></div>
     				</form>	
     </div>
			</td>
		</tr>
	</table>
	</body>
</html>