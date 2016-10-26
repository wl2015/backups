$(function(){
  var hM = getHeight($('.merchantMap'),0.4);
  $('.merchantMap').css("height",hM);
  
  var hPrev = getHeight($('.prev'),0.2);
  $('.prev').css({"height":hPrev,"line-height":hPrev});
  
  var hNext = getHeight($('.next'),0.2);
  $('.next').css({"height":hNext,"line-height":hNext});
  
  var hButton2 = getHeight($('.button2'),0.09);
  $('.button2').css({"height":hButton2,"line-height":hButton2});
});
//设置高度为宽度的比例
function getHeight(obj,num){
  return Math.floor((obj.width())*num)+'px';
}
//
function addMap(pointLeft,pointRight,merchantOneLeft,merchantOneRight,merchantTwoLeft,merchantTwoRight){
  var map = new BMap.Map("allmap");    // 创建Map实例
  var point = new BMap.Point(pointLeft, pointRight); // 创建点坐标
  map.centerAndZoom(point, 14);  // 初始化地图,设置中心点坐标和地图级别
  map.enablePinchToZoom();//启用双指放缩
  map.disableDragging();//禁止拖拽
  map.disableDoubleClickZoom();//禁用双击放大 
  var pointOneMarker = new BMap.Point(merchantOneLeft,merchantOneRight);
  var pointTwoMarker = new BMap.Point(merchantTwoLeft,merchantTwoRight);
  addMarker(pointOneMarker);
  addMarker(pointTwoMarker)
  
  /**
   * 添加标注
   * */
  function addMarker(point){
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);//将标注添加到地图中
    //marker.enableDragging();//标注可拖拽
    marker.disableDragging(); //标注不能拖拽
  }
}
