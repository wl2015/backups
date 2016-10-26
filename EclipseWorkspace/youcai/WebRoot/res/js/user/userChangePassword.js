$(function(){
  $('#modify').click(function(){
    if($('#psd').val().length>=6){
      if($('#psd').val()==$('#repeatpsd').val()){
            document.change.submit();
      }
      else{
        alert("两次密码输入不一致");
      }
    }
    else{
      alert("密码是六至二十位");
    }
    
  });
});