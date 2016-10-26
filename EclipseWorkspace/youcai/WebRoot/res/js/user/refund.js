$(function(){
  $('#Content').keyup(function(){
    $('#isAllowToCommit').html("yes");
    AntiSqlValid(this);
    var content = $('#Content').val();
    if(content.length>50){
      var newContent = "";
      for(var i=0; i<50; i++){
        newContent = newContent + content.charAt(i);
      }
      $('#Content').val(newContent);
      $('#leftCount').html(0);
    }
    else{
      $('#leftCount').html(50-content.length);
    }
  });
  
  $('.submitRefund').click(function(){
    if($('#Content').val()==""){
     alert("内容不能为空");
    }
    else if($('#isAllowToCommit').html()=="no"){
    alert("请您不要在参数中输入特殊字符和SQL关键字！");
    }
    else{
      document.refund.submit();
    }
  });
});
/***
 * 防sql注入
 * */
function AntiSqlValid(oField )
{
   re= /’|"|=|;|>|<|%/i;
    if ( re.test(oField.value) )
   {
    alert("请您不要在参数中输入特殊字符和SQL关键字！");
    $('#isAllowToCommit').html("no");
   }
}