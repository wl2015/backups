$(document).ready(function(){
  // 百度地图API功能
  function G(id) {
    return document.getElementById(id);
  }

  var map = new BMap.Map("l-map");
  map.centerAndZoom("成都",12);                   // 初始化地图,设置城市和地图级别。

  var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
    {"input" : "suggestId"
    ,"location" : map
  });

  ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
  var str = "";
    var _value = e.fromitem.value;
    var value = "";
    if (e.fromitem.index > -1) {
      value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }    
    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
    
    value = "";
    if (e.toitem.index > -1) {
      _value = e.toitem.value;
      value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }    
    /*str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
    G("searchResultPanel").innerHTML = str;*/
  });

  var myValue;
  ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
  var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
    
    setPlace();
  });

  function setPlace(){
    map.clearOverlays();    //清除地图上所有覆盖物
    function myFun(){
      var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
      map.centerAndZoom(pp, 18);
      map.addOverlay(new BMap.Marker(pp));    //添加标注
      
      getAllAddressAndPutAllAddressToBackstage(pp);
    }
    var local = new BMap.LocalSearch(map, { //智能搜索
      onSearchComplete: myFun
    });
    local.search(myValue);
  }
  
  
  /**
   * 逆地址解析,并将坐标点和解析地址通过Ajax传送到后台
   */
  function getAllAddressAndPutAllAddressToBackstage(point){
    var geoc = new BMap.Geocoder();
    geoc.getLocation(point, function(rs){
      var addComp = rs.addressComponents;
      var address = addComp.province + addComp.city + addComp.district 
        + addComp.street + addComp.streetNumber;
      var headquarters = new BMap.Point(104.07238,30.664111);
      var distance = map.getDistance(point,headquarters).toFixed(2);//获取两点距离,保留小数点后两位
      //      alert(distance);
      //putAddressInfomationToBackstage(point.lng, point.lat, address, distance);
      alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
      alert(address);
      uploadMapInfo(point.lng,point.lat,address,addComp.district);
    });
  }
})


/**
 * 上传到服务器
 */
function uploadMapInfo(lng,lat,address,district){
  $.ajax({
    url:WEBROOT+'/test/getInfo.do',
    type:'post',
    dataType:'json',
    data:{
      lng:lng,
      lat:lat,
      address:address,
      district:district,
    },
    success:function(res){
      alert("success");
    }
  });
}