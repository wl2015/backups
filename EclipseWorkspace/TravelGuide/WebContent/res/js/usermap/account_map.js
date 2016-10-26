/**
 * 用户身份访问
 */

//获得景点对象并创建一个数组
function GetSight(obj){
  var sight_array = new Array;
  $('#cardlist').html("");
  for(var i=0; i<obj.length; i++){
    var name = obj[i].querySelector('#sightname span').innerHTML;
    var address = obj[i].querySelector('#address span').innerHTML;
    var lng = obj[i].querySelector('#lng span').innerHTML;
    var lat = obj[i].querySelector('#lat span').innerHTML;
    (function(){
      var j = i + 1;
      $('#cardlist').css({'display':''});
      $('#cardlist').append("<li id='card'>"+j+"、"+name+"</li>");
    })(i);
    var sight = {
      address: address,
      sightLng: lng,
      sightLat: lat
    };
    sight_array.push(sight);
  }
  return sight_array;
}

function SearchLocate(){
  var sight= document.getElementById('inputLocate').value;
  $('#con').addClass('blur');
  $('.loader').css({'display':''});
  $.ajax({
    url: WEBROOT+'/user/getSightsByTypeName.do',
    type: 'post',
    data:{
      typeName: sight
    },
    success: function(res, status, xhr){
      var sight_obj = $(res).find('#sight');
      var getSightObj = GetSight(sight_obj);
      var len = getSightObj.length;
      if(len != 0){
        map.clearOverlays();    //清除地图上所有覆盖物
        for(var i=0; i<getSightObj.length; i++){
          var address = getSightObj[i].address;
          var lat = getSightObj[i].sightLat;
          var lng = getSightObj[i].sightLng;
          var point = new BMap.Point(lng, lat);
          var marker = new BMap.Marker(point);
          var label = new BMap.Label(i+1, {offset:new BMap.Size(-8,-10)});
          marker.setLabel(label);
          map.addOverlay(marker);
          map.centerAndZoom(point, 10);
          map.enableScrollWheelZoom(true);
          addClickHandler(address, marker);  //添加信息窗口
        }
      }else{
       alert("没有对应的结果，请重新输入！"); 
      }
    },
    error: function(xhr, status, info){
      if(status == 408){
        alert("请求超时！");
      }else{
        alert("传值失败！");
        console.log("error:"+info);
      }
    },
    complete: function(){
      $('.loader').css({'display':'none'});
      $('#con').removeClass('blur');
    }
  });
}

function addClickHandler(address, marker){
  var opt = {
    title: "详细地址",
    width: 209,
    height: 105,
    enableAutoPan: true,
    panel: "panel",
    searchTypes: [
      BMAPLIB_TAB_TO_HERE
    ]
  };
  marker.addEventListener("click", function(e){
    var p = e.target;
    var searchInfoWindow = new BMapLib.SearchInfoWindow(map, address, opt);
    searchInfoWindow.open(new BMap.Point(p.getPosition().lng, p.getPosition().lat));
  });
  //添加信息窗口
  /*var opts = {
    width:100,
    height: 80,
    title: "详细地址："
  }
  marker.addEventListener("click", function(e){
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    var infoWindow = new BMap.InfoWindow(content,opts);
    map.openInfoWindow(infoWindow,point);
  });*/
}


