

$(function(){
  var titleH = getHeight($('.title'),0.05);
  $('.title').css({"height":titleH,"line-height":titleH});
  //list5
  var list5H = getHeight($('.list5'),0.1);
  $('.list5').css({"height":list5H,"line-height":list5H});
  
  var divSureH = getHeight($('.divSure'),0.25);
  $('.divSure').css({"height":divSureH,"line-height":divSureH});
  var divRefundH = getHeight($('.divRefund'),0.25);
  $('.divRefund').css({"height":divRefundH,"line-height":divRefundH});
  var divHadRefundH = getHeight($('.divHadRefund'),0.25);
  $('.divHadRefund').css({"height":divHadRefundH,"line-height":divHadRefundH});
  
  //alert($('.divSure').width());
  
  $('.detailButton').click(function(){
    $(this).next().slideToggle();
  });
});

//设置高度为宽度的比例
function getHeight(obj,num){
  return Math.floor((obj.width())*num)+'px';
  
}
