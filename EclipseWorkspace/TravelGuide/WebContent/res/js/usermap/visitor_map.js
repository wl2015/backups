/**
 * 游客身份登录
 */


  
$(document).ready(function(){
  var MapGolocation = new BMap.Geolocation();
  var map = new BMap.Map("mapshow");  // 创建地图实例
  if(navigator.geolocation){
    console.log("您的浏览器支持geolocation");
    MapGolocation.getCurrentPosition(function(p){
      console.log(this.getStatus());
      if(this.getStatus() == BMAP_STATUS_SUCCESS){
        map.panTo(p.point);
        var locate = new BMap.Point(103.991807, 30.584949);       //经度：103.991807；纬度：30.584949
        map.centerAndZoom(locate, 15);
        ppoint = locate;
        setTimeout(function(){
          var convertor = new BMap.Convertor();
          var pointArr = [];
          pointArr.push(locate);
          convertor.translate(pointArr,1,5,TranslateCallBack);
        }, 1000);
/*        var local = new BMap.LocalSearch(map, { //智能搜索
          renderOptions: {map:map}
        });
        local.search("景点");*/
      }else{
        alert(this.getStatus());
      }
    },
    function(error){
      switch(error.code){
        case error.TIMEOUT:
        alert("连接超时");
        break;
        case error.PERMISSION_DENIED:
        alert("您拒绝了使用位置共享服务");
        break;
        case error.POSITION_UNAVAILABLE:
        alert("暂时无法提供此服务");
        break;
      }
    },{enableHighAccuracy: true});
  }else{
    alert("您的浏览器不支持geolocation");
  }

  function TranslateCallBack(p){
    if(p.status === 0){
      var mk = new BMap.Marker(p.points[0]);  //获得标记  
      map.addOverlay(mk); //经过换算的百度坐标
      //添加label
      var label = new BMap.Label("您的当前位置", {
        offset: new BMap.Size(-20, -5)
      });
      label.setStyle({
        color: "blue",
        width: "120px"
      });
      mk.setLabel(label);
      map.setCenter(p.points[0]);
    }
  }
  
  map.enableScrollWheelZoom(true);  //添加滚动缩放
  var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_LEFT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_LARGE,
    // 启用显示定位
    enableGeolocation: true
  });
  //map.addControl(navigationControl);  //添加导航控件
  
  //添加定位控件
/*  var geolocationControl = new BMap.GeolocationControl();
  map.addControl(geolocationControl);*/
  
  //点击对应的标签
  var td = document.querySelectorAll('table tr td span');
  var LabelInput = function(num){
    return function(){
      SelectLabel(num);
    }
  }
  
  for(var i=0; i<td.length; i++){
    td[i].onclick = LabelInput(i);
  }
  
  function SelectLabel(num){
    var labelvalue = document.querySelectorAll('table tr td')[num].innerHTML;
    document.querySelector('#labeltext').innerHTML = labelvalue;
  }
});



function SureLabel(){
  var labeltext = document.querySelector('#labeltext span').innerHTML;
  //alert(labeltext);
}

//清空选择的label
function ClearLabel(){
  document.querySelector('#labeltext').innerHTML = "";
}

