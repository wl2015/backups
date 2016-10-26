<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品套餐管理----套餐分类</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/setFenlei.css">
    <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
    <script type="text/javascript" src="res/js/admin/dishAndPackageType.js?verson=2"></script>  
    <script type="text/javascript" src="res/js/common.min.js"></script>     
    <script type="text/javascript" src="res/js/constants.min.js"></script>
</head>
<body>
  <div class="dishOrSetManage">
      <ul class="dishOrSetManageUl">
      <li><span class="sp1">类别名称：</span><input type="text" name="typeName" maxlength="15"/></li>
      <li><span class="sp1">所属类型：</span>
        <select name="target">
          <option value="1">菜品</option>
          <option value="2">套餐</option>
        </select>
        <div class="button" id="addTypeBtn">确认添加</div>
      </li>
      <li>
          <a href="javascript:void(0)" onclick="contentSet('dish/TypeForDish.do')">菜品分类</a>
          <a href="javascript:void(0)" onclick="contentSet('dish/TypeForPackage.do')">套餐分类</a>
      </li>
    </ul>
    <table>
      <thead>
        <tr>
          <th>类别名</th>
          <th>所属类型</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${typeList}" varStatus="status">
        <tr>
          <td>${item.typeName}</td>
          <td>${item.target == 1 ? '菜品' : '套餐'}</td>
          <td><a href="javascript:void(0)" onclick="deleteType('${item.typeId}','${item.target}')">删除</a></td>
        </tr>
       </c:forEach>
      </tbody>
    </table>
    
 <div class="fenye" style=" text-align: center">
 
  <a href="javascript:void(0)" onclick="contentSet('dish/TypeForPackage.do?pageNow=1')">首页</a>
  <c:choose>
    <c:when test="${page.pageNow - 1 > 0}">
      <a href="javascript:void(0);" onclick="contentSet('dish/TypeForPackage.do?pageNow=${page.pageNow - 1}')" >上一页</a>
    </c:when>
    <c:when test="${page.pageNow - 1 <= 0}">
      <a href="javascript:void(0);" onclick="contentSet('dish/TypeForPackage.do?pageNow=1')">上一页</a>
    </c:when>
  </c:choose>
  <font size="2">第 ${page.pageNow}/ ${page.totalPageCount} 页</font>
  <c:choose>
    <c:when test="${page.pageNow + 1 < page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('dish/TypeForPackage.do?pageNow=${page.pageNow + 1}')">下一页</a>
    </c:when>
    <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('dish/TypeForPackage.do?pageNow=${page.totalPageCount}')">下一页</a>
    </c:when>
  </c:choose>
  <a href="javascript:void(0);" onclick="contentSet('dish/TypeForPackage.do?pageNow=${page.totalPageCount}')">尾页</a>
  <br>
  </div>
  </div>
</body>
</html>