$(function(){
  $('#modify').click(function(){
    if($('#psd').val().length<6){
      alert("密码太短，请输入六至二十位");
    }
    if($('#psd').val() != $('#repeatpsd').val()){
      alert("两次密码输入不相同");
    }
    else if($('#psd').val().length>=6 && $('#psd').val().length<20 
        &&$('#psd').val() == $('#repeatpsd').val()){
      document.change.submit()
    }
  });
});
