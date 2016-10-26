<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
<title>管理员主页面</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/adminIndex.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.js"></script>
  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>

  <script type="text/javascript" src="res/js/common.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/adminIndex.js?verson=2"></script>
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
  <script type="text/javascript" src="res/js/admin/calendar.js" ></script>
  <script type="text/javascript" src="res/js/admin/time.js" ></script>
  
  
  

</head>

<body>

<div class="header">
  <div class="headerInfo">
    <span id="welcome">欢迎来到有菜~~~</span>
    <div class="account">
      <span id="personalInfo">| 管理员：${LOGIN_SESSION_ADMIN.adminName } ▼</span>
      <!-- 账号详细信息 -->
      <div class="personalInfo">
        <ul>
          <li class="li1">账号：<span>${LOGIN_SESSION_ADMIN.adminAccount }</span></li>
          <li class="li2">手机：<span>${LOGIN_SESSION_ADMIN.adminPhone }</span></li>
          <li class="li3">邮箱：<span>${LOGIN_SESSION_ADMIN.adminMail }</span></li>
          <li class="li4">职位：
             <c:forEach var="item" items="${ADMIN_LIMITS}" varStatus="status">
              <p>${item.limit}</p> 
             </c:forEach>
          </li>
          <li class="li5"><a href="admin/modifyPassword.do" target="_blank">修改密码</a></li>
        </ul>
      </div>
    </div>
     <span id="exit"><a href="admin/loginOut.do">| 退出当前账号</a></span>
  </div>
  
  <div class="logo">
   
    <img src="res/img/logo.png"/>
  </div>
  
  <!-- 滚动提示栏 -->
  <div class="tips"><marquee  scrollamount="2" direction="Left" ><div>商家审核数量（<span id="verifyMerchantCount"></span>）</div><div>退款申请数量（<span  id="refundOrderCount"></span>）</div></marquee></div>
  <div class="boxNav">
    <ul>
      <li class="current"><a href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do');">新商家审核(${verifyMerchantCount})</a></li>
      <li class="hasChild"><a href="javascript:void(0);" onclick="contentSet('manageMer/show.do');">商家管理</a>
        <!-- 新加的 -->
        <div class="child">
          <ul>
            <li><a href="javascript:void(0);" onclick="contentSet('manageMer/show.do');">查询商家</a></li>
            <li><a href="javascript:void(0);" onclick="contentSet('manageMer/getMerchantMode.do');">模式选择</a></li>
          </ul>
        </div>
      </li>
      <li class="hasChild"><a href="javascript:void(0);" onclick="contentSet('dish/show.do');">菜品管理</a>
        <!-- 新加的 -->
        <div class="child">
          <ul>
            <li><a href="javascript:void(0);" onclick="contentSet('dish/show.do');">菜品管理</a></li>
            <li><a href="javascript:void(0);" onclick="contentSet('dish/toGroupList.do');">套餐管理</a></li>
            <li><a href="javascript:void(0);" onclick="contentSet('dish/TypeForDish.do');">菜品/套餐类别</a></li>
          </ul>
        </div>
      </li>
      <li><a href="javascript:void(0);" onclick="contentSet('comment/goodShow.do');">评论管理</a></li>
      <li><a href="javascript:void(0);" onclick="contentSet('admin/sales.do');">出货管理</a></li>
      <li class="hasChild"><a href="javascript:void(0);" onclick="contentSet('admin/finance.do');">财务管理</a>
        <!-- 新加的 -->
        <div class="child">
          <ul>
            <li><a href="javascript:void(0);" onclick="contentSet('admin/finance.do');">财务统计</a></li>
            <li><a href="javascript:void(0);" onclick="contentSet('admin/queryRefundOrderList.do');">用户退款申请(${refundOrderCount})</a></li>
            <li><a href="javascript:void(0);" onclick="contentSet('admin/createAdvanceList.do');">商家预付款单</a></li>
          </ul>
        </div>
      </li>
      <li><a href="javascript:void(0);" onclick="contentSet('admin/message.do');">站内信</a></li>
      <li><a href="javascript:void(0);" onclick="contentSet('admin/advance.do');">预付款管理</a></li>
      <li><a href="javascript:void(0);" onclick="contentSet('admin/limitPage.do');">权限管理</a></li>
    </ul>
  </div>
</div>

<div class="content">
  
  <!-- 每一个局部刷新的都是放到content中的 -->
  <div class="inner" style="text-align: center">
      欢迎来到有菜后台管理！
      <br>
      ${limitMsg }
  </div>
  
  
  
  
  
  
  
  
  

</div>
<div class="footer">
<div style="font-size: 20px" id="Clock"></div><br>
  版权申明.....版权申明.....版权申明.....版权申明.....版权申明.....
  <audio id="adminAudio" hidden="hidden"><!-- controls="controls" -->
    <source src="res/forYoucai.mp3" type="audio/mpeg">
    <source src="res/forYoucai.wav" type="audio/wav"> 
</audio>  
</div>
</body>
</html>
