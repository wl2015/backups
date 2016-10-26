$(function(){
  var hM = getHeight($('.merchantMap'),0.4);
  $('.merchantMap').css("height",hM);
  
  var hPrev = getHeight($('.prev'),0.3);
  $('.prev').css({"height":hPrev,"line-height":hPrev});
  
  var hNext = getHeight($('.next'),0.3);
  $('.next').css({"height":hNext,"line-height":hNext});
  
  var hButton2 = getHeight($('.button2'),0.15);
  $('.button2').css({"height":hButton2,"line-height":hButton2});
});
//设置高度为宽度的比例
function getHeight(obj,num){
  return Math.floor((obj.width())*num)+'px';
}

$().ready(function(){
  getPushMerchantInfo(0);
});

//上一家，提取之前一家的商家信息
function getBackMerchant(){
  var state = parseInt($('#state').val());
  if(state > 0){
    getPushMerchantInfo(state-1);
  }
}

//下一家，提取下一家的商家信息
function nextMerchant(){
  var state = parseInt($('#state').val());
  getPushMerchantInfo(state+1);
}
//获取推送的商家信息
function getPushMerchantInfo(state){
  $.ajax({
    url:WEBROOT+'/user/pushMerchantInfo.do',
    type:'post',
    data:{
      state:state
    },
    success:function(res){
      if(res.resultCode == AJAX_CODE_SUCCESS){
          var map = new BMap.Map("allmap");    // 创建Map实例
          var point = new BMap.Point(res.pointLeft, res.pointRight); // 创建点坐标
          map.centerAndZoom(point, 14);  // 初始化地图,设置中心点坐标和地图级别
          map.enablePinchToZoom();//启用双指放缩
          map.disableDoubleClickZoom();//禁用双击放大 
          var pointMarker = new BMap.Point(res.merchant.merchantLng,res.merchant.merchantLat);
          var marker = new BMap.Marker(pointMarker);
          map.addOverlay(marker);//将标注添加到地图中
          marker.disableDragging(); //标注不能拖拽
          //左上角，添加默认缩放平移控件
          var top_left_navigation = new BMap.NavigationControl();
          //左上角，添加比例尺
          var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
          map.addControl(top_left_control);
          map.addControl(top_left_navigation);
          showMerchantInfo(res);
      }
      else if(res.resultCode == AJAX_CODE_FAIL){
         alert("取值失败");
      }
      
    }
  });
}

//显示商家信息
function showMerchantInfo(res){
  var htmlContent = 
    '<input type="text" style="display: none;" id="state" value="'+res.state+'">'
    + '<input type="text" style="display: none;" name="merchantId" value="'+res.merchant.merchantId+'">'
    + '<div class="leftPhoto">'
    +   '<img src="res/img/food.jpg"/>'
    + '</div>'
    + '<div class="rightInfo">'
    +   '<ul>'
    +     '<li>'+res.merchant.shopName+'</li>'
    +     '<li>星级:<span>'+res.merchant.merchantStar+'级</span></li>'
    +     '<li>联系方式:'+res.merchant.linkPhone+'</li>'
    +     '<li>地址:'+res.merchant.merchantAddress+'</li>'
    +   '</ul>'
    + '</div>'
    + '<!--店铺简介 -->'
    + '<div class="storeIntro">'
    +   '<span>商家简介:</span><p>'+res.merchant.merchantIntro+'</p>'
    + '</div>'
    + '<!--历史最多评价 -->'
    + '<div class="evaluate">'
    +   '<span>历史最多评价:</span>'
    +     '<p>'+res.merchant.merchantComment+'</p>'
    + '</div>';
  
  $('#merchantContent').html(htmlContent);
}