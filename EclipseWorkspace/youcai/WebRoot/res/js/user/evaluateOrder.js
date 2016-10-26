$(function(){
  var receiveH = getHeight($('.receiveConfirm'),0.2);
  $('.receiveConfirm').css({"height":receiveH,"line-height":receiveH});
  $('#good1').show();
  $('#good2').show();
  $('#good3').show();
  
  $('#goods').click(function(){
    hideAllInput();
    $('#good1').show();
    $('#good2').show();
    $('#good3').show();
    $('#page').html(1);
    $('#badChange').hide();
    $('#goodChange').show();
  });
  $('#bads').click(function(){
    hideAllInput();
    $('#bad1').show();
    $('#bad2').show();
    $('#bad3').show();
    $('#page').html(1);
    $('#badChange').show();
    $('#goodChange').hide();
  });
  
  $('#goodChange').click(function(){
    var page = parseInt($('#page').html());
    var num = parseInt($('#goodCommentsNum').html());
    if(page*3 >= num){
      hideAllInput();
      $('#good1').show();
      $('#good2').show();
      $('#good3').show();
      $('#page').html(1);
    }
    else{
      hideAllInput();
      $('#good'+(page*3+1)).show();
      $('#good'+(page*3+2)).show();
      $('#good'+(page*3+3)).show();
      $('#page').html(page+1);
    }
  });
  $('#badChange').click(function(){
      var page = parseInt($('#page').html());
      var num = parseInt($('#badCommentsNum').html());
      if(page*3 >= num){
        hideAllInput();
        $('#bad1').show();
        $('#bad2').show();
        $('#bad3').show();
        $('#page').html(1);
      }
      else{
        hideAllInput();
        $('#bad'+(page*3+1)).show();
        $('#bad'+(page*3+2)).show();
        $('#bad'+(page*3+3)).show();
        $('#page').html(page+1);
      }
    });
});

//设置高度为宽度的比例
function getHeight(obj,num){
  return Math.floor((obj.width())*num)+'px';
}
//隐藏所有input评论
function hideAllInput(){
  var goodNum = parseInt($('#goodCommentsNum').html());
  var badNum = parseInt($('#badCommentsNum').html());
  for(var i=1;i<=goodNum;i++){
    $('#good'+i).hide();
  }
  for(var j=1;j<=badNum;j++){
      $('#bad'+j).hide();
  }
}
