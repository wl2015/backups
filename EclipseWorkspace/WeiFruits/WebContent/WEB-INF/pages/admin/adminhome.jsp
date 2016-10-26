<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理主页</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/order_alert.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/res/js/admin/adminCommon.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/constants.min.js"></script>
<style>
  #north h2{
    position: relative;
    top: 12px;
    left: 45%;
    text-align: center;
    color: #0000CC;
    font-size: 20px;
    display: inline-block;
  }
  #tree li{
    padding-top: 20px;
  }
  #north .order-alert{
    position: relative;
    top: 30px;
    left: 80%;
    color: #D91E18;
    font-size: 13px;
  }

  
  
</style>
</head>
<body class="easyui-layout">
  <div id="north" data-options="region:'north', border:false" style="height:60px;background:#B3DFDA">
    <h2>微果后台管理</h2>
    <!-- <span class="order-alert">订单提示</span> -->
  </div>
  
  <div id="west" data-options="region:'west', title: '目录', split:false" style="width:200px;padding:10px">
    <ul class="easyui-tree" id="tree">
      <li>
        <span><a id="goods" href="javascript:void(0);" onclick="addTabs('toGoodsManage')">商品管理</a></span>
      </li>
      <li>
        <span><a id="orders" href="javascript:void(0);" onclick="addTabs('toOrderManage')">订单管理</a></span>
      </li>
      <li>
        <span><a id="area" href="javascript:void(0);" onclick="addTabs('toAreaManage')">校区管理</a></span>
      </li>
      <li data-options="state: 'closed'">
        <span>公告栏信息发布</span>
        <ul>
          <li>
            <span><a id="newnotes" href="javascript:void(0);" onclick="addTabs('toNewnotes')">新建公告</a></span>
          </li>
          <li>
            <span><a id="oldnotes" href="javascript:void(0);" onclick="addTabs('toOldnotes')">历史公告栏</a></span>
          </li>
        </ul>
      </li>
      <li>
        <a id="account" href="javascript:void(0);" onclick="addTabs('toAccountManage')">账户管理</a>
      </li>
    </ul>
  </div>
  
  <div id="south" data-options="region:'south', border: false" 
      style="height:50px;background:#A9FACD;padding:10px">
  </div>
  
  <div data-options="region:'center',title:'Center'" id="center">
 
  </div>
  
  <script>

    function addTabs(action){
      var url = window.location.href;
      var webroot = "/" +url.split("/")[3];
      var finalUrl = webroot + '/admin/' + action;
      var content = '<iframe scrolling="auto" frameborder="0" src="'+finalUrl+'" style="width:100%;height:100%"></iframe>';
      $('#center').html("");
      $('#center').html(content);
    }

  

  </script>
</body>
</html>