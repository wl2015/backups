<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="../css/left.css"/>
	<script  language="javascript" type="text/javascript" src="../js/hover.js"></script>
	 <script src="../js/jquery-1.4.2.min.js"></script>
  </head>
  
<body topmargin="0" leftmargin="2" rightmargin="2" bottommargin="2" border="0">	
<div id="left" >
	<div  onClick="SwitchMenu('sub1')" class="menutitle">
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0" id="lt">
					<tr>
					<td><div class="f1"  onmouseover="f1('t1','sub1');" id="t1" onmouseout="f2('t1','sub1');" >
							<img src="../img/admin.png" style="width:20px;height:20px"/>
							系统管理
							</div></td>
					</tr>
				</TABLE>
	</div>

				<span id="sub1" class="submenu">
				<table cellspacing="0" cellpadding="7" width="168">
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="admin/admin.html" onclick="">管理员信息</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="admin/addAdmin.html">添加管理员</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="admin/noOpen.html">不营业期间图片设置</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="admin/addP.html">添加图片</a>
						</TD>
					</TR>
				</table> </span>
	

	<div  onClick="SwitchMenu('sub2')" class="menutitle">
				
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0">
					
					<tr>
						<td><div class="f1" onmouseover="f1('t2','sub2');" id="t2" onmouseout="f2('t2','sub2');">
						<img src="../img/inmana.png" style="width:20px;height:20px">
						库存单管理</div></td>
					</tr>
					
				</TABLE>

	</div>
			<span id="sub2" class="submenu">
					<table cellspacing="0" cellpadding="7" width="168">
						<TR>
							<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
								<img src="../img/tb.png" style="width:10px">
								<a class="menu" target="main" href="${pageContext.request.contextPath}/KucunAction_execute.do">库存管理</a>
							</TD>
						</TR>
					</table> </span>

	<div  onClick="SwitchMenu('sub3')" class="menutitle">
				
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0">
					
					<tr>
						<td><div class="f1" onmouseover="f1('t3','sub3');" id="t3" onmouseout="f2('t3','sub3');">
						<img src="../img/sure.png" style="width:20px;height:20px">
						订单管理</div></td>
					</tr>
					
				</TABLE>
	</div>		
				<span id="sub3" class="submenu">
				<table cellspacing="0" cellpadding="7" width="168">
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="WaiteSureAction_execute.do">未确认订单</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="${pageContext.request.contextPath}/CommonAction_getEnsureOrderList">已确认订单</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%"  bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="${pageContext.request.contextPath}/CommonAction_getDoneOrderList?deorderstate=0">出货单</a>
						</TD>
					</TR>
					
					<TR>
						<TD height=25 width="100%"  bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="TimeOutAction_execute.do">已过期订单</a>
						</TD>
					</TR>
				</table> </span>

	
	<div  onClick="SwitchMenu('sub4')" class="menutitle">
				
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0">
					
					<tr>
						<td><div class="f1" onmouseover="f1('t4','sub4');" id="t4" onmouseout="f2('t4','sub4');">
						<img src="../img/marki.png" style="width:20px;height:20px">
						配送员管理</div></td>
					</tr>
					
				</TABLE>
	</div>
				<span id="sub4" class="submenu">
				<table cellspacing="0" cellpadding="7" width="168">
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="SendPeopleAction_getallPeople.do">配送员管理</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%"  bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="marki/addpeople.jsp">添加配送员</a>
						</TD>
					</TR>
				</table> </span>

	<div  onClick="SwitchMenu('sub5')" class="menutitle">
				
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0">
					<tr>
						<td><div class="f1" onmouseover="f1('t5','sub5');" id="t5" onmouseout="f2('t5','sub5');">
						<img src="../img/food2.png" style="width:20px;height:20px">
						菜品信息管理</div></td>
					</tr>
				</TABLE>

	</div>
				<span id="sub5" class="submenu">
				<table cellspacing="0" cellpadding="7" width="168">
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="BackDishAction_getAllDish.do">菜品管理</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%"  bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="food/upload.jsp">添加菜品</a>
						</TD>
					</TR>
				</table> </span>

	<div  onClick="SwitchMenu('sub6')" class="menutitle">
				
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0">
					<tr>
						<td><div class="f1" onmouseover="f1('t6','sub6');" id="t6" onmouseout="f2('t6','sub6');">
						
						销售量及收入查询</div></td>
					</tr>
				</TABLE>

	</div>
				<span id="sub6" class="submenu">
				<table cellspacing="0" cellpadding="7" width="168">
					<TR>
						<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="ProfitAction_getDishName.do">菜品销售量查询</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%"  bgcolor="#fbead0" class="p1">
							<img src="../img/tb.png" style="width:10px">
							<a class="menu" target="main" href="qosi/income.jsp">收入查询</a>
						</TD>
					</TR>
				</table> </span>
	<div  onClick="SwitchMenu('sub7')" class="menutitle">
				
				<TABLE width="168" cellSpacing="0" cellPadding="0" border="0">
					
					<tr>
						<td><div class="f1" onmouseover="f1('t7','sub7');" id="t7" onmouseout="f2('t7','sub7');">
						可送达地址管理</div></td>
					</tr>
					
				</TABLE>

	</div>
			<span id="sub7" class="submenu">
					<table cellspacing="0" cellpadding="7" width="168">
						<TR>
							<TD height=25 width="100%" bgcolor="#fbead0" class="p1">
								<img src="../img/tb.png" style="width:10px">
								<a class="menu" target="main" href="${pageContext.request.contextPath}/AddressAction_AddressShow">地址管理</a>
							</TD>
						</TR>
					</table> </span>
				
</div>
</body>
</html>
