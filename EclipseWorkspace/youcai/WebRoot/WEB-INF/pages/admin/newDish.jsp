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
<link rel="stylesheet" type="text/css" href="res/css/admin/newDish.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/dishManage.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/jQuery/jquery.form.js"></script>
  <script type="text/javascript" src="res/js/constants.min.js"></script>  

<title>新增页面</title>
</head>
<body>
  <div class="newDish">
    <div class="addDishes" id="addDrag">
      <ul id="ul">
        <li><strong>菜品名</strong><input type="text"  id="newName" name="newName" maxlength="15"/></li>
        <li><strong>菜品简介</strong><input type="text"  id="newIntro" name="newIntro" maxlength="20"/></li>
        <li class="textA"><strong>菜品详情</strong><textArea id="newDetail" name="newDetail"></textArea></li>
        <li class="imgShow">
          <strong>图片</strong>
          <span id="pic">
              <div id="preview">  
                <img alt="图片预览" id="imghead" width=100 height=100 border=0 src="">  
              </div>  
            <br>
          </span>
          <form action="" method="post" id="uploader" enctype="multipart/form-data">
            <input onchange="previewImage(this)" id="uploadField" type="file" name="file"/>
            <input type="hidden" name="dir" value="media"/>
          </form> 
        </li>
        <li><strong>成本价</strong><input type="text" id="newCostPrice" name="newCostPrice"/></li>
        <li class="twoInline">
          <strong>原价</strong><input type="text" id="originalPrice" name="originalPrice"/>
          <strong class="two">零售价</strong><input type="text" id="newRetailPrice" name="newRetailPrice"/>
        </li>
        <li class="twoInline">
          <strong>口味</strong><input type="text" id="newTaste" name="newTaste"/>
          <strong class="two">类别</strong>
          <select id="typeId">
            <optgroup label="菜品">  
                  <c:forEach var="item" items="${typeList}" varStatus="status">
                      <option value="${item.typeId}">${item.typeName}</option>  
                  </c:forEach> 
            </optgroup>  
          </select>
        </li>
      </ul>
      <div class="twoButton">
        <div id="cancelAdd" onclick="contentSet('dish/show.do');">取消新增</div>
        <div id="sureAdd">确定新增</div>
      </div>
    </div>
  </div>
</body>
</html>