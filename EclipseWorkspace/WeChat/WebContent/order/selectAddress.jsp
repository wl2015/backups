<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addressOne.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		a{
			text-decoration:none;
			color:blue;
		}
		a:hover{
			text-decoration:underline;
		}
	</style>
  </head>
  
  <body>
  <marquee direction="right" behavior="scroll">欢迎来到慢烧点餐系统!</marquee>
  
  <center>
  ${param.user.user_id }
  	<table align="center">
  		<tr><td><s:a href="index.jsp"><font color="#FF6600">首页</font></s:a></td></tr>
  		<tr><td><font color="">选择送餐地址</font></td></tr>
  		
  		<tr><td><hr/></td></tr>
  		
  		
  		<s:form action="addressAction_searchAll.do" method="post">
  		<tr><td><font color="#FF6600">第一步：输入地址（街道，写字楼，小区，楼盘名）</font></td></tr>
  		<tr><td><div align="center">
  			<s:textfield  name="keyword" theme="simple"></s:textfield>
  			<s:submit value="key搜索" theme="simple" method="searchKey"/>
  			<s:submit value="All搜索" theme="simple"/>
  		</div></td></tr>
  		</s:form>
  		
  		
  		
   		<tr><td><s:property value="'<hr/>'" escape="false"/></td></tr>
  		
  		<tr><td><font color="#FF6600">选择配送地址</font></td></tr>
  		
  		<tr>
  			<td>
	  		<table align="center" width="500" border="1" bordercolor="black" cellpadding="0" cellspacing="0"
			style="border:1px solid black; border-collapse: collapse; line-height:30px; ">
		  		<tr>
				<!--<th>地址编号</th>  -->	
					<th>地址名称</th>
					
				</tr>
		  		
		  		<s:iterator var="a" value="addressList">
					<tr>
						<!-- <td><s:property value="#a.frontaddress_id"/></td>	 -->
						<td><s:a href="addressAction_getAddress.do?fronAddress.frontaddress_id=%{#a.frontaddress_id}"><s:property value="#a.front_address"/> </s:a></td>
						
					</tr>
				
				</s:iterator>
	  		</table>
	  		</td>
  		</tr>
  		<tr><td><hr/></td></tr>
  		
  			
	  	<tr>
  			<td>
    		<input type="button" value=关闭  style="width:100px;height:40px" onclick="window.location.href='addressOne.jsp'"/>
    		
    		</td>
	    </tr>
  		 		
  		
  	</table>
 </center>  
  </body>
</html>
