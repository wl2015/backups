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
<title>符合关键词查询的菜品</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/dishesManage.css">
  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/dishManage.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/jQuery/jquery.form.js"></script>
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 
</head>
<body>
  <div class="dishesManage">
    <div class="search">
      <span>搜索关键词："${keywords}"</span>
      <input type="text" name="key" id="key" placeholder="关键词:菜品名"/>
      <input id="serachDishByKeywords" type="button" value="搜索" />
    </div>
    <div class="newDish" onclick="contentSet('dish/add.do')">新增菜品</div>
    <div class="dishList">
      <ul id="dishList">
      <c:forEach var="item" items="${dishList}" varStatus="status">
          <li class="oneDish">
          <div class="dishLeft">
            <img src="${item.dishPic }"/>
          </div>
          <div class="contentRight">
            <ul class="contentList">
              <li><strong>菜品名：</strong><span>${item.dishName }</span></li>
              <li><strong>简介：</strong><span>${item.dishIntro }</span></li>
              <li><strong>成本价：</strong><span>${item.costPrice }</span></li>
              <li><strong>零售价：</strong><span>${item.retailPrice }</span></li>
              <li><strong>口味：</strong><span>${item.dishTaste }</span></li>
            </ul>
          </div>
          <div class="operate">
            <div class="editDish" onclick="editDish('${item.dishId}')">编辑</div>
            <div onclick="deleteDish('${item.dishId}')">删除</div>
          </div>
        </li>
      </c:forEach>    
      </ul>
    </div>
    
    
    <div class="fenye">
 
  <a href="javascript:void(0)" onclick="contentSet('dish/queryDishByKeywords.do?pageNow=1&keywords=${keywords}')">首页</a>
  <c:choose>
    <c:when test="${page.pageNow - 1 > 0}">
      <a href="javascript:void(0);" onclick="contentSet('dish/queryDishByKeywords.do?pageNow=${page.pageNow - 1}&keywords=${keywords}')" >上一页</a>
    </c:when>
    <c:when test="${page.pageNow - 1 <= 0}">
      <a href="javascript:void(0);" onclick="contentSet('dish/queryDishByKeywords.do?pageNow=1&keywords=${keywords}')">上一页</a>
    </c:when>
  </c:choose>
  <font size="2">第 ${page.pageNow}/ ${page.totalPageCount} 页</font>
  <c:choose>
    <c:when test="${page.pageNow + 1 < page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('dish/queryDishByKeywords.do?pageNow=${page.pageNow + 1}&keywords=${keywords}')">下一页</a>
    </c:when>
    <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('dish/queryDishByKeywords.do?pageNow=${page.totalPageCount}&keywords=${keywords}')">下一页</a>
    </c:when>
  </c:choose>
  <a href="javascript:void(0);" onclick="contentSet('dish/queryDishByKeywords.do?pageNow=${page.totalPageCount}&keywords=${keywords}')">尾页</a>
  <br>
   </div>
   
  </div>
  
  
  
  
  
  
    <!-- 弹出修改菜品的框框 -->
    <div class="editDishes" id="editDrag">
    
    </div>
</body>
</html>