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
<title>出货管理</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/salesManage.css">


<script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="res/js/admin/finance.js?verson=2"></script>
<script type="text/javascript" src="res/js/constants.min.js" ></script>


</head>
<body>
<form action="admin/addSales.do" method="post" id="salesForm">
  <div class="deliverManage">
    <div class="deliverLeft">
    <div class="dishName">
      <span>菜品名</span>
      <select name="dishId">  
                        <optgroup label="私房菜">  
                        <c:forEach var="item" items="${dishList}" varStatus="status">
                            <option value="${item.dishId}">${item.dishName}</option>  
                        </c:forEach> 
                             
                         </optgroup>  
                         <optgroup label="甜品">  
                              <option value="4">彩虹棒</option>  
                              <option value="5">冰纷卷</option>  
                          </optgroup>  
                      </select> 
                      <input type="hidden" name="dishName1" value="">
    </div>
    <div class="numId">
      <span>商家名称</span>
      <span id="merchantId"  name="merchantId">点击商家选择</span>
      <input type="hidden" id="merId" name="merId">
    </div>
    <div class="search">

      <input type="text" name="searchKey" id="searchKey"/><input id="serachMerchant" type="button" value="搜索" />
    </div>
    <!-- 显示搜索的内容 -->
    <div class="showResult">
      <table id="showMerList">
        <thead>
          <tr>
            <th>商家编号</th>
            <th class="merchantName">商家名称</th>
            <th>注册时间</th>
          </tr>
        </thead>
      </table>
    </div>
    <div class="num">
      <span>出货数量</span>
       <input type="number" name="salesNum" id="salesNum" size="10"/>
    </div>
    <div class="date">
      <span>出货日期</span>
      <input name="salesTime" type="text" style="padding-left:5px;" id="txtDate" onclick="SetDate(this)" readonly="readonly" />  
    </div>
    <div id="addSales">确认出货</div>
  </div>
  <div class="deliverRight">
      <div class="title"><span>出货号</span><span>商家名</span><span>菜品名</span><span>出货数量</span><span>出货时间</span></div>
      <ul id="ulScroll">
        <c:forEach items="${saleList }" var="item" varStatus="status">
        <li style="background-color:#EBEBEB; margin-bottom:3px;"><span>${item.salesId }</span><span>${item.shopName }</span><span>${item.dishName }</span><span>${item.salesNum }</span><span>${item.createTime }</span></li>
        </c:forEach>
      </ul>
    </div>
  </div>
    
</form> 

</body>
</html>