<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建新的管理员页面</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/newAdmin.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/limitsAdmin.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 

</head>
<body>
  <div class="newAdmin">
    <div class="backButton" onclick="contentSet('admin/limitPage.do')">返回</div>
    <div class="two">
      <!-- 左边 -->
      <div class="leftContent">
        <span class="leftTitle">创建新的管理员</span>
        <ul>
          <li><span>姓名：</span><input type="text" id="adminName"/></li>
          <li><span>职位：</span><input type="text" value="请在权限栏进行设置" disabled="disabled"/></li>
          <li><span>账户：</span><input type="text"  id="adminAccount"/></li>
          <li><span>密码：</span><input type="text" id="adminPassword"/></li>
          <li><span>手机：</span><input type="text" id="adminPhone" placeholder="请填入有效号码"/></li>
          <li><span>邮箱：</span><input type="text" id="adminMail" placeholder="请填入有效邮箱"/></li>

        </ul>
        <div class="createAdmin" id="createAdmin">确认创建</div>
  
      </div>
      <!-- 右边-->
      <div class="rightContent">
        <span class="authSet">权限设置</span>
        <ul>
          <li><input name="limitSet" type="checkbox" value="2"/>商家管理员</li>
          <li><input name="limitSet" type="checkbox" value="3"/>菜品管理员</li>
          <li><input name="limitSet" type="checkbox" value="4"/>评价管理员</li>
          <li><input name="limitSet" type="checkbox" value="5"/>出货管理员</li>
          <li><input name="limitSet" type="checkbox" value="6"/>财务管理员</li>
          <li><input name="limitSet" type="checkbox" value="7"/>公告管理员</li>
          <li><input name="limitSet" type="checkbox" value="8"/>出纳管理员</li>
        </ul>
      </div>
    </div>
  </div>
</body>
</html>