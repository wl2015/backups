$(function(){
  
  getData();
  //当管理员进入merchantInfo界面时，页面每隔30s轮询一次是否有新消息
  //（1000毫秒 = 1s，60000毫秒 = 1分钟，所以30000 = 30s, 用3000001以避免与抢单时轮询产生时间冲突）    
    
  run();
  function run(){
    interval = setInterval(getData, "30001"); 
  }
  function getData(){
    var beforeMerchantNum = $('#verifyMerchantCount').html();  //得到原先商家审核数量
    var beforeRefundNum = $('#refundOrderCount').html();  //得到原先退款申请数量
      $.ajax ({
        url: WEBROOT + '/admin/adminLunxunMessage.do',
        type:'post',
        dataType:'json',
        contentType:"application/json",
        success:function(res) {
          //如果登录超时
          if(res.code ==  AJAX_SESSION_TIMEOUT_CODE ){
            alert("登录信息超时，请重新登录后再操作！");
            return;
          }
          $('#verifyMerchantCount').html(res.verifyMerchantCount);
          $('#refundOrderCount').html(res.refundOrderCount);
          
          //播放语音提示
          if(parseInt(beforeMerchantNum) < parseInt(res.verifyMerchantCount)){
           
            var myAuto = document.getElementById('adminAudio');
            myAuto.play();
            alert("您有新的商家审核申请，请您及时处理！");
          }
          else if(parseInt(beforeRefundNum) < parseInt(res.refundOrderCount)){
            
            var myAuto = document.getElementById('adminAudio');
            myAuto.play();
            alert("您有新的用户退款申请，请你及时处理！");
          }
        },
        error:function(){
          alert("网络连接异常，请稍后重试");
        }
      });     
  }
  
  
  
  
  
  
  
  
  $('.boxNav ul li a').click(function(){
    $(this).parent('li').addClass('current').siblings('li').removeClass('current');
  });
  
   
//设置账号详细信息的显示
  $('#personalInfo').mouseover(function(){
    $(this).css("background",'black');
    $('.personalInfo').show().mouseover(function(){
      $('#personalInfo').css("background",'black');
      $(this).show();
    }).mouseout(function(){
      $('#personalInfo').css("background",'#333333');
      $(this).hide();
    });
  }).mouseout(function(){
    $(this).css("background",'#333333');
    $('.personalInfo').hide();
  });
  
  
//显示子菜单
  $('.hasChild').hover(function(){
    $(this).find('.child').show();
  },function(){
	  $(this).find('.child').hide();
  });
  
  
});


function contentSet(url){
  //alert("页面跳转+++");
  
  //！！！这一坨应该往你加载数据那里放，然后加载数据成功后移除加载图
  $('.content').addClass('contentstyle');
  $('.content').prepend('<img class="loading" src="res/img/adminLoad.gif"/>');
  var contentWidth = $('.content').width();
  var imgWidth = $('.loading').width();
  $('.loading').css({
    "marginLeft":(contentWidth-imgWidth)/2+'px',
    "marginTop":"50px"
  });
  
  
  $.ajax({
    url:url,
    type:'post',
    dataType:'html',
    success:function(data){
    
      //移除加载图
      $('.loading').remove();
      $('.content').removeClass('contentstyle');
      
      $('.content').html(data);
    }
  });
}

function contentSetByGet(url){
  //alert("页面跳转+++");
  
  $.ajax({
    url:url,
    type:'get',
    dataType:'html',
    success:function(data){
      $('.content').html(data);
    }
  });
}
