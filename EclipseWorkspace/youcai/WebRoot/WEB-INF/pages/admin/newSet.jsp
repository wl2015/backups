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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增套餐</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>res/css/admin/newSet.css">
<script type="text/javascript" src="<%=basePath %>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>res/js/jQuery/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath %>res/js/common.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>res/js/inputcheck.js" ></script>
<script type="text/javascript" src="<%=basePath %>res/js/admin/newSet.js" ></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
</head>
<body>
  <div class="newSet">
    <div class="sureNewSet">确定新增</div>
    <!-- 新增时弹出的框，要求确认所选套餐和输入新增套餐的名字 -->
    <div class="forSetName">
      <div class="sure">
        <span class="mySelect">你选择的是：</span><br/>
        <div class="group">
        <span class="dishName">棒棒鸡</span><span class="dishNum">(<strong>2</strong>)份</span><span class="add">+</span>
      <span class="dishName">棒棒鸡</span><span class="dishNum">(<strong>2</strong>)份</span>
      </div>
      </div>
      <div class="setSetName">请输入套餐名：<input type="text" id="groupName"/></div>
      <div class="price">原价：<span id="oldPrice">222</span>元    <span class="salePrice"> 售价：<span id="salePrice">222</span><!-- <input type="text" id="retailPrice"/> --></span>元</div>
      <div class="belong">所属类别<select id="groupType">
        <c:forEach var="item" items="${typelist}" varStatus="status">
          <option data=${item.typeId }>${item.typeName }</option>
        </c:forEach>
      <!-- <option>A类</option><option>B类</option> --></select>
      </div>
      <form id="fileForm">
      <div class="upload">上传图片<input type="file" id="groupPic" name="Upload_img"/></div>
      </form>
      <div class="setIntro" style="float:left;">套餐简介：</div><textarea  id="groupIntro" cols="20" rows="5" ></textarea>
      <br/>
      <div class="twoButton">
        <div class="cancelSet">取消新增</div>
        <div class="sureSet">确定新增</div>
      </div>
    </div>
    
    <ul class="newSetUl">
    <c:forEach var="item" items="${dishList}" varStatus="status">
     <li class="oneDish">
        <img src="<%=basePath %>res/img/food.jpg"/> <%-- ${item.dishPic } --%>
        <div><span>${item.dishName }</span><input type="text" value="0" 
        data='{"id":${item.dishId },"dishName":"${item.dishName }","originalPrice":${item.originalPrice },"retailPrice":${item.retailPrice },"dishPic":"${item.dishPic }","dishTaste":"${item.dishTaste }"}' />份</div>
      </li>        
  </c:forEach> 
      <!-- <li class="oneDish">
        <img src="res/img/food.jpg"/>
        <div><span>棒棒鸡</span><input type="text" value="0"/>份</div>
      </li> -->
      
    </ul>
  </div>
</body>
</html>