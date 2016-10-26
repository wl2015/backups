<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/authorization.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/limitsAdmin.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 
</head>
<body>
  <div class="authorization">
    <div class="newButton" onclick="contentSet('admin/addAdmin.do')">创建admin</div>
    <table>
      <thead>
        <tr>
          <th>编号</th>
          <th>姓名</th>
          <th>职位</th>
          <th>创建时间</th>
          <th>操作</th>
        </tr>
      </thead>
      
        <c:forEach var="item" items="${adminList}" varStatus="status">
        <tr class="temp">
          <td>${item.adminId }</td>
          <td>${item.adminName }</td>
          <td>
            <c:forEach var="limits" items="${item.limitsList}" varStatus="status">
              ${limits.limit }
            </c:forEach>
          </td>
          <td>${item.createTime }</td>
          <td><a href="javascript:void(0);" onclick="contentSet('admin/getAdminById.do?id=${item.adminId}')" >详情</a></td>
          <td>
            <c:choose>
              <c:when test="${item.adminId != 1}">
                  <a href="javascript:void(0);" onclick="deleteAdmin('${item.adminId}')" >注销</a>
              </c:when>
              <c:when test="${item.adminId == 1}">
                  <a href="javascript:void(0);" style=" color:gray">注销</a>
              </c:when>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
     
    </table>
    
    
  <div class="fenye">
  <a class="FirsAndLast" href="javascript:void(0)" onclick="contentSet('admin/limitPage.do?pageNow=1')">首页</a>
  <c:choose>
    <c:when test="${page.pageNow - 1 > 0}">
      <a href="javascript:void(0);" onclick="contentSet('admin/limitPage.do?pageNow=${page.pageNow - 1}')" >上一页</a>
    </c:when>
    <c:when test="${page.pageNow - 1 <= 0}">
      <a href="javascript:void(0);" onclick="contentSet('admin/limitPage.do?pageNow=1')">上一页</a>
    </c:when>
  </c:choose>
    <font size="2">第 ${page.pageNow}/${page.totalPageCount}页</font>
  <c:choose>
    <c:when test="${page.pageNow + 1 < page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('admin/limitPage.do?pageNow=${page.pageNow + 1}')">下一页</a>
    </c:when>
    <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('admin/limitPage.do?pageNow=${page.totalPageCount}')">下一页</a>
    </c:when>
  </c:choose>
  <a class="FirsAndLast" href="javascript:void(0);" onclick="contentSet('admin/limitPage.do?pageNow=${page.totalPageCount}')">尾页</a>
  </div>
  </div>
</body>
</html>