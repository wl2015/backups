function getAddress(){
  var select = document.getElementById("address");
  var index = select.selectedIndex;
  var value = select.options[index].value;
  var sightId = value.split("*")[0];
  var left = value.split("*")[1];
  var right = value.split("*")[2];
  var map = new BMap.Map("allmap");    // 创建Map实例
  map.centerAndZoom("成都",12);
  var geoc = new BMap.Geocoder();
  var pt = new BMap.Point(left,right);
  geoc.getLocation(pt, function(rs){
    var addComp = rs.addressComponents;
    var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
    document.getElementById("content").innerHTML=address;
    updateSight(sightId,address,addComp.district);
  });
}

function updateSight(sightId,address,district){
  $.ajax({
        url:WEBROOT+'/test/upDateSight.do',
        type:'post',
        dataType:'json',
        data:{
          sightId:sightId,
          address:address,
          district:district,
        },
        success:function(res){
        }
    });
}