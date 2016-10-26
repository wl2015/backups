<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微果</title>
<link rel="stylesheet" href="<%=path %>/res/css/user/home.css"/>
<link rel="icon" href="<%=path %>/res/images/favicon.ico" type="image/x-icon"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<style>

  body {
    font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
    padding: 0;
    margin: 0;
  }
</style>
</head>
<body>
    <iframe scrolling="auto" frameborder="0" style="width:100%;" src="<%=path %>/user/toMyHome"
    marginheight="0" marginwidth="0" id="iframepage" onLoad="iFrameHeight()" name="myframe">
    </iframe>

  
  <footer>
    <ul class="btn-list" style="padding:0;margin:0">
      <li id="market" onclick="SetFrame('toMyHome');"><a>商店</a></li>
      <li id="order" onclick="SetFrame('toOrder');"><a>订单</a></li>
      <li id="shopping" onclick="SetFrame('toShopping')">
        <a>购物车
          <span class="number_notice" style="display:none" id="totalCount">0</span>
        </a>
      </li>
      <li id="account" onclick="SetFrame('toAccount')"><a>账户</a></li>
    </ul>
  </footer>
  
  
  <script>
    $(document).ready(function(){
    //判断jQuery是否已经加载完成
      if(typeof jQuery == 'undefined'){
        console.log("jQuery hasn't loaded");
      }else{
        console.log("jQuery has loaded");
      }
  
      $('.number').attr({"readonly":true});
    });
    
    function SetFrame(flag){
      var ifm = document.getElementById('iframepage');
      $('.panel').remove();
      ifm.src = flag;
    }
    
  //iframe高度自适应
  function iFrameHeight(){
    var ifm= document.getElementById("iframepage");
    if(ifm != null){
      var bHeight = ifm.contentWindow.document.body.scrollHeight;
      var dHeight = ifm.contentWindow.document.documentElement.scrollHeight;
      var height = Math.max(bHeight, dHeight);
      ifm.height = height;
    }
  }
  </script>
</body>
</html>