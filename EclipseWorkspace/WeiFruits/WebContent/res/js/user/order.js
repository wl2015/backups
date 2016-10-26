/**
 * 
 */
$(document).ready(function(){
  $(".list-group a").click(function() {
    $(this).css("background","#F9690E");//给点击的a标签加背景
    $(this).siblings().css("background","#ECECEC");//被点击之外的a标签背景为白色
  });

  var details = document.getElementsByClassName('dd');
  var Details = function(num){
    return function(){
      ShowDetails(num);
    }
  }
  for(var i=0; i<details.length; i++){
    details[i].onclick = Details(i);
  }
});
  
//显示订单详情
  function ShowDetails(num){
    var de = document.getElementsByClassName('de');
    var details = document.getElementsByClassName('dd');
     if($(de[num]).is(':hidden')){
       $(de[num]).slideDown();
       $(details[num]).html("收起");
    }else{
      $(de[num]).slideUp();
      $(details[num]).html("详情");
    }
  }