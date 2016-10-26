<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册第二步</title>

<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js" ></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.form.js" ></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/merchantRegist2.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/common.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/inputcheck.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=U5iFnij0pevt6y63HRqcpG38"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/mRegist2.css"/>
</head>
<body>
  <div class="content">
    <div class="left">
      <div class="title">
        <span id="span1">1.获取验证码</span>
        <span>--></span>
        <span id="span2">2.填写注册信息</span>
      </div>
      <div class="info">
      <form id="registForm" method="post">
        <div class="box">
        <span><label for="name">邮箱</label></span><input type="text" id="email" name="email"/>
        </div>
        <div class="box">
        <span><label for="name">联系人</label></span><input type="text" id="linkman" name="linkman"/>
        </div>
        <div class="box">
        <span><label for="name">店铺名称</label></span><input type="text" id="shopname" name="shopname"/>
        </div>
        <div class="box">
        <span><label for="name">身份证号</label></span><input type="text" id="IDcard" name="IDcard" onKeyUp="this.value = this.value.replace(/[^0-9a-zA-Z]/g,'');"/>
        </div>
        <div class="box">
        <span><label for="name">联系号码</label></span><input type="text" id="linkphone" name="linkphone" onKeyUp="this.value = this.value.replace(/[^0-9]/g,'');"/>
        </div>
        <div class="box">
        <span><label for="name">银行卡号</label></span><input type="text" id="bankcode" name="bankcode" onKeyUp="this.value = this.value.replace(/[^0-9]/g,'');"/>
        </div>
        <div class="box">
        <span><label for="name">密码</label></span><input type="password" id="password" name="password" />
        </div>
        <div class="box">
        <span><label for="name">确认密码</label></span><input type="password" id="repeatPassword"  name="repeatPassword" />
        </div>
        <div class="box">
        <span><label for="name">选择地址</label></span><input type="text"  name="place" />
        <!-- 搜索结果面板 -->
            <!-- <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:200px;height:auto;display:none"></div>  -->
        <span id="span3"><input type="button" id="showMap" value="在地图中选择我的位置"/></span>
        </div>
        <!-- 保存选择的地图坐标 -->
        <input type="hidden"  id="markerlng" name="markerlng">
            <input type="hidden"  id="markerlat" name="markerlat">
          </form>
        <div class="box box1">
          <input type="button" id="regist-BTN" value="注册"/>
          <a href="<%=basePath %>merchant/toMerchantLogin.do">返回登录</a>
        </div>
      </div>
    </div>
    <div class="right">
      <img src="<%=basePath%>res/img/logo.png"/>
    </div>
  </div>
  
  
  <!-- 显示地图的div -->
  <div id="baidumap">
    <p>请在地图中移动图标，选择你所在的位置</p>
    <span style="position:absolute;left:40%; top:5px">搜索地址</span><input type="text" id="city"/>
      <input type="button" id="jumpMap" value="搜索"/>
    <!-- 搜索结果面板 -->
     <div id="searchResultPanel"></div> 
    <span id="surePos">确定</span>
    <span id="closeMap">退出</span>
    
    <!-- 放地图 -->
    <div id="container"></div>
    
  </div>
</body>
</html>