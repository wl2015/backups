var UPLOAD_URL_ROOT = "/youcai_uploads";
//var UPLOAD_URL_CUT_IMG = "/create_logo_image.jsp";
var UPLOAD_URL_IMG = "/upload_json.jsp";
$(function() {
  getData();
  //当商家进入merchantInfo界面时，页面每隔30s轮询一次是否有新消息
  //（1000毫秒 = 1s，60000毫秒 = 1分钟，所以20000 = 20s, 用10001以避免与抢单时轮询产生时间冲突）    
  if(CHECK.isEmpty($('#logo').attr('src'))){
    $('#logo').attr('src', WEBROOT+'/res/img/food.jpg');
  }  
  run();
  function run(){
    interval = setInterval(getData, "20001"); 
  }
  function getData(){
    var pushNum = $('#pushOrderNum').html();  
      $.ajax ({
        url: WEBROOT + '/merchant/merchantLunxunMessage.do',
        type:'post',
        dataType:'json',
        contentType:"application/json",
        success:function(res) {
          if(res.code ==  AJAX_SESSION_TIMEOUT_CODE ){
            alert("登录信息超时，请重新登录后再操作！");
            return;
          }
         /* $('#letterNum').html(res.messageNumber);
          $('#refundNum').html(res.refundMessageNumber);*/
          $('#pushOrderNum').html(res.pushOrderNumber);  
          if(parseInt(pushNum) < parseInt(res.pushOrderNumber)){
            var myAuto = document.getElementById('myaudio');
            myAuto.play();
            alert("你有新订单啦");
          }

        //系统消息图标及数字  
          var a1w = $('.img1').siblings('a').width();//a的宽度
          $('.img1').css("left",a1w);//图片left为a的宽度
          var img1w = $('.img1').width()/2;//图片一半宽度
          var n1w = ($('#letterNum').width())/2;//数字一半宽度
          var n1 = (a1w + img1w-n1w)+'px';//数字左边距  注意：width()方法获取的没有px，css()获取的有
          $('#letterNum').css("left",n1);
          //客户退单消息图标及数字
          var a2w = $('.img2').siblings('a').width();//a的宽度
          $('.img2').css("left",a2w);//图片left为a的宽度
          var img2w = $('.img2').width()/2;//图片一半宽度
          var n2w = ($('#refundNum').width())/2;//数字一半宽度
          var n2 = (a2w + img2w-n2w)+'px';//数字左边距
          $('#refundNum').css("left",n2);
          //客户退单消息图标及数字
          var a3w = $('.img3').siblings('a').width();//a的宽度
          $('.img3').css("left",a3w);//图片left为a的宽度
          var img3w = $('.img3').width()/2;//图片一半宽度
          var n3w = ($('#pushOrderNum').width())/2;//数字一半宽度
          var n3 = (a3w + img3w-n3w)+'px';//数字左边距
          $('#pushOrderNum').css("left",n3);
          
          //图片的top
          var liWidth = $('.list').find('li').height();//任何一个li 的高度
          var img1Height = $('.img1').height();//图片高度
          var img2Height = $('.img2').height();
          var img3Height = $('.img3').height();
          var n1Height = $('#letterNum').height();//数字高度
          var n2Height = $('#refundNum').height();
          var n3Height = $('#pushOrderNum').height();
          var img1Top = (liWidth-img1Height)/2+'px';//图片top
          var img2Top = (liWidth-img2Height)/2+'px';
          var img3Top = (liWidth-img3Height)/2+'px';
          var n1Top = (liWidth-n1Height)/2+'px';//数字top
          var n2Top = (liWidth-n2Height)/2+'px';
          var n3Top = (liWidth-n3Height)/2+'px';
          $('.img1').css("top",img1Top);
          $('.img2').css("top",img2Top);
          $('.img3').css("top",img3Top);
          $('#letterNum').css("top",n1Top);
          $('#refundNum').css("top",n2Top);
          $('#pushOrderNum').css("top",n3Top);
        },
        error:function(){
          alert("网络连接异常，请稍后重试");
        }
      });      
  }
  
  
  /*//系统消息图标及数字  
  var a1w = $('.img1').siblings('a').width();//a的宽度
  $('.img1').css("left",a1w);//图片left为a的宽度
  var img1w = $('.img1').width()/2;//图片一半宽度
  var n1w = ($('#letterNum').width())/2;//数字一半宽度
  var n1 = (a1w + img1w-n1w)+'px';//数字左边距  注意：width()方法获取的没有px，css()获取的有
  $('#letterNum').css("left",n1);
  //客户退单消息图标及数字
  var a2w = $('.img2').siblings('a').width();//a的宽度
  $('.img2').css("left",a2w);//图片left为a的宽度
  var img2w = $('.img2').width()/2;//图片一半宽度
  var n2w = ($('#refundNum').width())/2;//数字一半宽度
  var n2 = (a2w + img2w-n2w)+'px';//数字左边距
  $('#refundNum').css("left",n2);
  //客户退单消息图标及数字
  var a3w = $('.img3').siblings('a').width();//a的宽度
  $('.img3').css("left",a3w);//图片left为a的宽度
  var img3w = $('.img3').width()/2;//图片一半宽度
  var n3w = ($('#pushOrderNum').width())/2;//数字一半宽度
  var n3 = (a3w + img3w-n3w)+'px';//数字左边距
  $('#pushOrderNum').css("left",n3);
  
  //图片的top
  var liWidth = $('.list').find('li').height();//任何一个li 的高度
  var img1Height = $('.img1').height();//图片高度
  var img2Height = $('.img2').height();
  var img3Height = $('.img3').height();
  var n1Height = $('#letterNum').height();//数字高度
  var n2Height = $('#refundNum').height();
  var n3Height = $('#pushOrderNum').height();
  var img1Top = (liWidth-img1Height)/2+'px';//图片top
  var img2Top = (liWidth-img2Height)/2+'px';
  var img3Top = (liWidth-img3Height)/2+'px';
  var n1Top = (liWidth-n1Height)/2+'px';//数字top
  var n2Top = (liWidth-n2Height)/2+'px';
  var n3Top = (liWidth-n3Height)/2+'px';
  $('.img1').css("top",img1Top);
  $('.img2').css("top",img2Top);
  $('.img3').css("top",img3Top);
  $('#letterNum').css("top",n1Top);
  $('#refundNum').css("top",n2Top);
  $('#pushOrderNum').css("top",n3Top);*/
  
  //修改图标
  $('#logo').click(function(){
     $("#Uploader_img").click();
       uploadPicture();      
  });
  function uploadPicture(){      
      $("#Uploader_img").change(function() {    
          $("#fileForm").ajaxSubmit({
              dataType:  'json',
              url : UPLOAD_URL_ROOT + UPLOAD_URL_IMG,
              type: 'post',
              success:function(res){
                  if(res.error == 0) {                    
                     doChangeInconInDataBase(res.url);
                     alert('上传成功');
                  }
                  else{
                     alert(res);
                  }               
              },
              error:function(){
                 alert("网络连接失败，请稍后重试1");
              }
          });
      });
      
  }

  function doChangeInconInDataBase(str){
    $.ajax({
      url: WEBROOT + '/merchant/changeLogo.do',
      data:{
        logoUrl:str
      },
      type: 'post',
      dataType: 'json',
      success: function(res) {
          if(res.code == AJAX_SUCCESS_ALERT_CODE){
            $("#logo").attr("src",str); 
          } else if(res.code ==  AJAX_SESSION_TIMEOUT_CODE ){
            alert("登录信息超时，请重新登录后再操作！");
          }else{
            alert("系统处理异常！错误信息：" + res.message);
          }
      },
      error:function() {
        alert("数据连接失败，请稍后重试");
      }
    });
     //$(".showall").remove();
  }
  //修改图标结束
  

});

//ajax异步加载
function centerSet(url){
  $.ajax({
    url:url,
    type:'get',
    dataType:'html',
    success:function(data){            
      if(data.code ==  AJAX_SESSION_TIMEOUT_CODE ){
        alert("登录信息超时，请重新登录后再操作！");        
      }
      else{
        $('.inner').html(data);
      }
      
    }
  
  });
  
}