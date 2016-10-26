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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>访客身份</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path%>/res/css/visitormap.css"/>
<link rel="stylesheet" href="<%=path%>/res/css/loaders.css"/>
<script src="<%=path %>/res/js/jquery.min.js"></script>
<script src="<%=path%>/res/js/constants.min.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=aYXuG6vmdGEHOhu2zQvdETFgxGR7wQBK"></script>
<script src="<%=path %>/res/js/usermap/visitor.js"></script>
<style>
  html{
    height: 100%;
  }
  body{
    height: 100%;
    padding: 0;
    margin: 0;
    overflow-y: hidden;
    font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
  }

</style>
</head>
<body>
  <div class="container" id="label">
    <div class="row">
      <div class="col-xs-6">
        <div id="labeltext">

        </div>
      </div>
      <div class="col-xs-6">
        <button class="btn btn-sm btn-warning" onclick="SureLabel();">确定</button>
        <button class="btn btn-sm btn-danger" onclick="ClearLabel()">清空</button>
      </div>
    </div>
    <table class="table" style="margin-top: 40px">
      <caption>请选择以下标签（一次只能选择一种标签）</caption>
      <tbody>
        <tr>
          <td><span class="label label-success">户外</span></td>
          <td><span class="label label-success">休闲</span></td>
          <td><span class="label label-success">自驾游</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">历史</span></td>
          <td><span class="label label-success">毕业</span></td>
          <td><span class="label label-success">情侣</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">喝茶</span></td>
          <td><span class="label label-success">徒步</span></td>
          <td><span class="label label-success">摄像</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">妹子</span></td>
          <td><span class="label label-success">花海</span></td>
          <td><span class="label label-success">古镇</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">寺庙</span></td>
          <td><span class="label label-success">避暑</span></td>
          <td><span class="label label-success">博物馆</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">美食</span></td>
          <td><span class="label label-success">大学</span></td>
          <td><span class="label label-success">骑行</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">温泉</span></td>
          <td><span class="label label-success">看日出</span></td>
          <td><span class="label label-success">聊天</span></td>
        </tr>
        <tr>
          <td><span class="label label-success">宗教</span></td>
          <td><span class="label label-success">采摘</span></td>
          <td><span class="label label-success">露营</span></td>
        </tr>
      </tbody>
    </table>
    <div style="overflow-y:auto;width:300px;height:180px;" id="address">
      <table>
        <tbody id="get">
        </tbody>
      </table>
    </div>
  </div>
  
  <div id="mapshow"style="height:100%">
    <div class="loader" style="display:none;">
      <div class="loader-inner ball-triangle-path">
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>
  </div>
  
  <script>
    var map = new BMap.Map("mapshow");
    var point = new BMap.Point(104.06, 30.67);
    map.centerAndZoom(point, 12);
    
    function Select(address, Lng, Lat){
      map.clearOverlays();    //清除地图上所有覆盖物
      var point = new BMap.Point(Lng,Lat);
      map.centerAndZoom(point,12);
      var marker = new BMap.Marker(point);
      map.addOverlay(marker);  //添加标注
      map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
      addClickHandler(address, marker);  //添加信息窗口
    }
    
    function addClickHandler(content, marker){
      //添加信息窗口
      var opts = {
        width:100,
        height: 80,
        title: "详细地址："
      }
      marker.addEventListener("click", function(e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content,opts);
        map.openInfoWindow(infoWindow,point);
      });
    }
  </script>
</body>
</html>