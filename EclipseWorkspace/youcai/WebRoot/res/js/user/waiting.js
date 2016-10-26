$(function(){
  var circleH = getHeight($('.circle'),1);
  $('.circle').css({"height":circleH,"line-height":circleH});
  
  var buttonH = getHeight($('.button'),0.15);
  $('.button').css({"height":buttonH,"line-height":buttonH});
});

//设置高度为宽度的比例
function getHeight(obj,num){
  return Math.floor((obj.width())*num)+'px';
  
}