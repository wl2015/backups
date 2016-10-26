
$(function(){
  var toPayH = getHeight($('.toPay'),0.35);
  $('.toPay').css({"height":toPayH,"line-height":toPayH});
});
//设置高度为宽度的比例
function getHeight(obj,num){
  return Math.floor((obj.width())*num)+'px';
  
}