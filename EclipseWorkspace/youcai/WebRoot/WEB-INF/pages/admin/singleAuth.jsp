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
<title>单个管理员权限设置页面</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/singleAuth.css">


  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/limitsAdmin.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/singleAuth.js?verson=2"></script>
  <script type="text/javascript">
  $(document).ready(function(){
    

          /**
           * 获取管理员信息，设置权限复选框默认选中
           */

  });
  </script>

</head>
<body>
  <div class="singleAuth">
    <div class="backButton" onclick="contentSet('admin/limitPage.do')">返回</div>
    <div class="two">
      <!-- 左边 -->
      <div class="leftContent">
        <span class="leftTitle">管理员个人信息</span>
        <ul>
          <li><span>编号：</span><input  type="text" id="adminId" value="${admin.adminId}" disabled/></li>
          <li><span>姓名：</span><input class="canEdit" type="text" id="adminName" value="${admin.adminName}" disabled/></li>
          <li><span>职位：</span>
            <c:forEach var="limits" items="${admin.limitsList}" varStatus="status">
              ${limits.limit }
            </c:forEach>
            <input type="hidden" id="limit" value="${admin.limitsList}">
          </li>
          <li><span>账户：</span><input class="canEdit" type="text" id="adminAccount"  value="${admin.adminAccount}" disabled/></li>
          <li><span>密码：</span><input class="canEdit" type="text" id="adminPassword" value="${admin.adminPassword}" disabled/></li>
          <li><span>手机：</span><input class="canEdit" type="text" id="adminPhone" value="${admin.adminPhone}" disabled/></li>
          <li><span>邮箱：</span><input class="canEdit" type="text" id="adminMail" value="${admin.adminMail}" disabled/></li>
          <li><span>创建时间：</span><input type="text" value="${admin.createTime}" disabled/></li>
        </ul>
        <div class="edit">编辑信息</div>
        <div class="saveEdit" id="updateAdmin">保存修改</div>
        <div class="setoff">注销admin</div>
      </div>
      <!-- 右边-->
      <div class="rightContent">
        <span class="authSet">权限设置</span>
        <ul>
          <li><input type="checkbox" name="limitSet" id="2" value="2"/>商家管理员</li>
          <li><input type="checkbox" name="limitSet" id="3" value="3"/>菜品管理员</li>
          <li><input type="checkbox" name="limitSet" id="4" value="4"/>评价管理员</li>
          <li><input type="checkbox" name="limitSet" id="5" value="5"/>出货管理员</li>
          <li><input type="checkbox" name="limitSet" id="6" value="6"/>财务管理员</li>
          <li><input type="checkbox" name="limitSet" id="7" value="7"/>公告管理员</li>
          <li><input type="checkbox" name="limitSet" id="8" value="8"/>出纳管理员</li>
        </ul>
      </div>
    </div>
  </div>
</body>
</html>