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
<title>FunMap</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path %>/res/css/accountmap.css" />
<link rel="stylesheet" href="<%=path%>/res/css/loaders.css"/>
<link rel="stylesheet" href="<%=path%>/res/css/SearchInfoWindow.css" />
<script src="http://api.map.baidu.com/api?v=2.0&ak=aYXuG6vmdGEHOhu2zQvdETFgxGR7wQBK"></script>
<script src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<script src="<%=path %>/res/js/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script src="<%=path %>/res/js/bootstrap.min.js"></script>
<script src="<%=path%>/res/js/usermap/account_map.js"></script>
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
  <div class="container" id="search">
    <div class="row">
      <div class="col-md-2">
        <input class="form-control" type="text" id="inputLocate"/>
      </div>
      <div class="col-md-1">
        <button class="btn btn-success" onclick="SearchLocate()">确认</button>
      </div>
      <div class="col-md-9" style="padding-top:8px">
        <span id="suggest">
          热搜标签：登山、古镇、花海、美食、民俗、采摘、美女、公园、自驾游
          <a data-target="#labels" data-toggle="modal" style="cursor: pointer;">更多</a>
        </span>
        <span id="username" style="margin-left:20px">用户名：${username }</span>
      </div>
    </div>
  </div>

  <div id="con" style="height:100%"></div>
  <div class="loader" style="display:none;">
    <div class="loader-inner ball-triangle-path">
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
  <div id="left_panel">
    <ul id="cardlist" style="display:none">
    </ul>
  </div>
    
    <!-- 更多标签 -->
  <div class="modal fade" id="labels" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" style="text-align: center;">
            更多标签
          </h4>
        </div>
        <div class="modal-body">
          休闲、情侣、历史、毕业、学生、大学生、独行、徒步、户外、摄影、学校、妹子<br/>
          爬山、看日出、看雪、露营、亲子游、家庭、免费、蜜月、度蜜月、艳遇、偶遇、宗教<br/>
          寺庙、背包客、避暑、小吃、滑雪、温泉、漂流、逛街、文艺、划船、踏青、骑马<br/>
          艺术、约会、展览、两人世界、骑行、博物馆、文物、拍照、摄像、喝茶、赏鱼、散步<br/>
          散心、聊天、放松、大学、学校、高校、大学城、氛围、放松、赏花、看花、古街
        </div>
        <div class="modal-footer" id="llogin">
          <button type="button" class="btn btn-primary" 
               data-dismiss="modal">关闭
          </button>
        </div>
      </div>
    </div>
  </div>
  
  
  <script>
  var map = new BMap.Map("con");
  var point = new BMap.Point(104.06, 30.67);
  map.centerAndZoom(point, 12);
  map.enableScrollWheelZoom(true);
  var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_LEFT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_LARGE,
    // 启用显示定位
    enableGeolocation: true
  });
  map.addControl(navigationControl);

/*   $(document).ready(function(){
    $('#suggest a').click(function(){
      $('#labels').modal('show');
    });
  }); */
  </script>
</body>
</html>