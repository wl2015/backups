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
<title>新商家审核</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/adminIndex.css">
<link rel="stylesheet" type="text/css" href="res/css/admin/merchantManage.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/merchant.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
  <div class="merchantManage">

    <table  id="verifyMerchantList">
      <caption>新商家审核</caption>
      <thead>
        <tr>
          <th>商家名</th>
          <th>地址</th>
          <th>负责人</th>
          <th>联系方式</th>
          <th>申请时间</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <c:forEach var="item" items="${verifyMerchantList}" varStatus="status">
        <tr class="temp">
          <td>${item.shopName }</td>
          <td>${item.merchantAddress }</td>
          <td>${item.bossName }</td>
          <td>${item.linkPhone }</td>
          <td>${item.registerTime }</td>
          <td>
            <c:if test="${item.merchantStatus == 0}">
                 <font style="color:red">未审核</font>
            </c:if>
            <c:if test="${item.merchantStatus == 2}">
                                          已驳回
            </c:if>
          </td>
          <td>
            <c:if test="${item.merchantStatus == 0}">
               <a href="javascript:void(0);" onclick="contentSet('manageMer/getVerifyMerchantById.do?id=${item.merchantId}')" >审核</a>
            </c:if>
            <c:if test="${item.merchantStatus == 2}">
               <a href="javascript:void(0);" onclick="contentSet('manageMer/getVerifyMerchantById.do?id=${item.merchantId}')" >详情</a>
            </c:if>
          
          </td>
        </tr>
      </c:forEach>

    </table>
    
    <c:choose>
    <c:when test="${page.totalPageCount == 0}">
        <div class="fenye">
          <font size="2">暂时没有新商家注册</font>
        </div>  
    </c:when>
    <c:when test="${page.totalPageCount > 0}">
        <div class="fenye">
        <a class="FirsAndLast" href="javascript:void(0)" onclick="contentSet('manageMer/showVerify.do?pageNow=1')">首页</a>
        <c:choose>
      
          <c:when test="${page.pageNow - 1 > 0}">
            <a href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do?pageNow=${page.pageNow - 1}')" >上一页</a>
          </c:when>
          <c:when test="${page.pageNow - 1 <= 0}">
            <a href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do?pageNow=1')">上一页</a>
          </c:when>
      
        </c:choose>
        <c:choose>
          <c:when test="${page.totalPageCount == 0}">
            <font size="2">暂时没有新商家注册</font>
          </c:when>
          <c:when test="${page.totalPageCount > 0}">
            <font size="2">第 ${page.pageNow}/${page.totalPageCount}页</font>
          </c:when>
        </c:choose>
        <c:choose>
          <c:when test="${page.pageNow + 1 < page.totalPageCount}">
            <a href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do?pageNow=${page.pageNow + 1}')">下一页</a>
          </c:when>
          <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
            <a href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do?pageNow=${page.totalPageCount}')">下一页</a>
          </c:when>
        </c:choose>
        <a class="FirsAndLast" href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do?pageNow=${page.totalPageCount}')">尾页</a>
        </div>
     </c:when>
     </c:choose>
  </div>

</body>
</html>