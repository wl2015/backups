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
  <marquee direction="right" behavior="scroll">��ӭ�������յ��ϵͳ!</marquee>
  
  <center>
  ${param.user.user_id }
  	<table align="center">
  		<tr><td><s:a href="index.jsp"><font color="#FF6600">��ҳ</font></s:a></td></tr>
  		<tr><td><font color="">ѡ���Ͳ͵�ַ</font></td></tr>
  		
  		<tr><td><hr/></td></tr>
  		
  		
  		<s:form action="addressAction_searchAll.do" method="post">
  		<tr><td><font color="#FF6600">��һ���������ַ���ֵ���д��¥��С����¥������</font></td></tr>
  		<tr><td><div align="center">
  			<s:textfield  name="keyword" theme="simple"></s:textfield>
  			<s:submit value="key����" theme="simple" method="searchKey"/>
  			<s:submit value="All����" theme="simple"/>
  		</div></td></tr>
  		</s:form>
  		
  		
  		
   		<tr><td><s:property value="'<hr/>'" escape="false"/></td></tr>
  		
  		<tr><td><font color="#FF6600">ѡ�����͵�ַ</font></td></tr>
  		
  		<tr>
  			<td>
	  		<table align="center" width="500" border="1" bordercolor="black" cellpadding="0" cellspacing="0"
			style="border:1px solid black; border-collapse: collapse; line-height:30px; ">
		  		<tr>
				<!--<th>��ַ���</th>  -->	
					<th>��ַ����</th>
					
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
    		<input type="button" value=�ر�  style="width:100px;height:40px" onclick="window.location.href='addressOne.jsp'"/>
    		
    		</td>
	    </tr>
  		 		
  		
  	</table>
 </center>  
  </body>
</html>
