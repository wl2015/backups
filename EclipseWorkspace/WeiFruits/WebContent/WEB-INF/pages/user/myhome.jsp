<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/user/home.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/user/userhome.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<title>Insert title here</title>
</head>
<body>
  <div class="panel panel-default" style="background: #FDE3A7; height:150px;text-align:center">
    <div class="panel-body">
      <div class="well-mg">
      <h4>公告栏</h4>
      ${noticeBean.noticeContent }
      </div>
    </div>
  </div>
  
  <div class="con">
    <div class="list-group" id="list">
      <a href="#" style="background:#F9690E">健康水果</a>
      <a href="#">新鲜牛奶</a>
    </div>
    <ul id="product_list" style="min-height:315px">
    <!-- 第一个商品 -->
    <c:forEach items="${fruitBeans }" var="fruit">
      <li data-product-id="20">
        <div class="icon">
          <img src="${fruit.fruitPic }" style="display:inline;width:80px;height:80px"/>
        </div>
        <div class="details">
          <div class="name">${fruit.fruitName}</div>
          <div class="soldout" style="top:0;right:2em">销量：<span>599</span></div>
          <div style="color:#26A65B">${fruit.fruitIntr }</div>
        </div>
        <ul class="rules" style="margin:10px 10px 0 10px">
          <li>
            <span id="price" style="color:#d62323">${fruit.fruitPrice }</span>
            <span id="priceunit">元</span>
            <span id="amount" style="color:#d62323">每</span>
            <span id="amountunit" style="color:#d62323">份</span>
            <div id="order_input">
              <div id="numberbox">
                <button class="glyphicon glyphicon-plus add" onclick="Fruits('${fruit.fruitId}','${fruit.fruitName}','${fruit.fruitPrice}','${fruit.fruitIntr}','${fruit.fruitDetail}','${fruit.fruitPic}','${fruit.sellCount}',this);"></button>
                <input class="number" type="text" id="fruit${fruit.fruitId }" value="${fruit.count }"/>
                <button class="glyphicon glyphicon-minus min" onclick="Fruits('${fruit.fruitId}','${fruit.fruitName}','${fruit.fruitPrice}','${fruit.fruitIntr}','${fruit.fruitDetail}','${fruit.fruitPic}','${fruit.sellCount}',this);"></button>
              </div>
            </div>
          </li>
        </ul>
      </li>
      </c:forEach>
    </ul>
      </div>
      <input style="display: none;" id="totalCount2" value="${totalCount }"/>
</body>
</html>