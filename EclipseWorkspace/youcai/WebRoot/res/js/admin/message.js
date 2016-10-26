$(function(){
  //站内信字数限制
  var maxLength = 400;
  $('#message').keyup(function(){
    var l = $(this).val().length;
    $('.limit strong').text(maxLength-l);
    if($('.limit strong').text() < 0){
      $('.limit strong').text('0');
      var val = $(this).val().substring(0,400);
      $(this).val(val);
    }
  });
  
  //滚动公告栏
  setInterval(function(){
    var newLi = $('#messageUl').children().first().clone(true);
    $('#messageUl').append(newLi);
    $('#messageUl').children().first().remove();
  },2000);
  
  
  
  
  //发送站内信
  $('#sendMsg').click(function(){
    
    var message = $('#message').val();
    
    if (!isEmpty(message)) {

      if (valiInput(message)) {

        $.ajax({
          url : WEBROOT + "/admin/writeMessage.do",
          type: "post",
          data:{
            message:message
          },
          dataType: "json", 
          success: function(res) {

            alert("发送站内信成功！");
            contentSet('admin/message.do');
            
          },

          error:function(){
            alert("系统繁忙，请稍后操作");
          }
        });
      }
      else {
        alert("站内信不能有非法字符！");
      }
    }
    else {
      alert("站内信不能为空！");
    }

  });
  
  
  
  
});



/**
 * 验证输入框是否有非法字符
 */ 
var valiInput = function(obj){
  
  var patrn=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;  
  
  if (patrn.test(obj)) {
    
    $("#input-body").find("#errorMsg").html("含有非法字符");
    return false;
  } else {
    $("#input-body").find("#errorMsg").html("通过");
    return true;  
  }
};
