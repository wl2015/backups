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
<base href="<%=basePath%>">
<title>符合关键词查询的商家管理</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/merchantManage.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/merchant.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 

</head>
<body>
 
  <div class="merchantManage">
     <div class="search">
      <span>关键词:"${keywords}"</span>
      <input type="text" name="keywords" id="keywords" placeholder="关键词:商家名/地址/负责人"/>
      <input id="serachMerchantByKeywords" type="button" value="搜索" />
    </div>
    <table id="merchantList">
      <thead>
        <tr>
          <th>商家名</th>
          <th>地址</th>
          <th>负责人</th>
          <th>星级</th>
          <th>联系方式</th>
          <th>操作</th>
        </tr>
      </thead>
      
      <c:forEach var="item" items="${merchantList}" varStatus="status">
        <tr class="temp">
          <td>${item.shopName }</td>
          <td>${item.merchantAddress }</td>
          <td>${item.bossName }</td>
          <td>${item.merchantStar }</td>
          <td>${item.linkPhone }</td>
          <td><a href="javascript:void(0);" onclick="contentSet('manageMer/getMerchantById.do?id=${item.merchantId}')" >详情</a></td>
          <td><a href="javascript:void(0);" onclick="deleteMerchant('${item.merchantId}')">删除</a></td>
        </tr>
      </c:forEach>
      
    </table>
    <div class="fenye">
  <a class="FirsAndLast" href="javascript:void(0)" onclick="contentSet('manageMer/queryMerchantsByKeyWords.do?pageNow=1&keywords=${keywords}')">首页</a>
  <c:choose>
    <c:when test="${page.pageNow - 1 > 0}">
      <a href="javascript:void(0);" onclick="contentSet('manageMer/queryMerchantsByKeyWords.do?pageNow=${page.pageNow - 1}&keywords=${keywords}')" >上一页</a>
    </c:when>
    <c:when test="${page.pageNow - 1 <= 0}">
      <a href="javascript:void(0);" onclick="contentSet('manageMer/queryMerchantsByKeyWords.do?pageNow=1&keywords=${keywords}')">上一页</a>
    </c:when>
  </c:choose>
    <font size="2">第 ${page.pageNow}/${page.totalPageCount}页</font>
  <c:choose>
    <c:when test="${page.pageNow + 1 < page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('manageMer/queryMerchantsByKeyWords.do?pageNow=${page.pageNow + 1}&keywords=${keywords}')">下一页</a>
    </c:when>
    <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
      <a href="javascript:void(0);" onclick="contentSet('manageMer/queryMerchantsByKeyWords.do?pageNow=${page.totalPageCount}&keywords=${keywords}')">下一页</a>
    </c:when>
  </c:choose>
  <a class="FirsAndLast" href="javascript:void(0);" onclick="contentSet('manageMer/queryMerchantsByKeyWords.do?pageNow=${page.totalPageCount}&keywords=${keywords}')">尾页</a>
    </div>
    
  </div>
</body>
</html>