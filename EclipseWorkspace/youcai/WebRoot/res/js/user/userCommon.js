$(function(){
  $('#phoneNum').keyup(function() {  
//    var number = $('#phoneNum').val();
//    var newNumber="";
//    for(var i=0;i<number.length;i++){
//      var num = number.charAt(i);
//      if(num==0 || num==1 || num==2 || num==3 || num==4 || num==5 || num==6 || num==7 || num==8 || num==9){
//        newNumber = newNumber + num;
//      }
//    }
//    $('#phoneNum').val(newNumber);
    var number = $('#phoneNum').val();
    $('#phoneNum').val(number.replace(/[^\d]/g,''));
  })
  
  $('#psd').keyup(function(){
    var password = $('#psd').val();
    $('#psd').val(password.replace(/[^\w+$\.]/g,''));
  });
  
  $('#repeatpsd').keyup(function(){
    var password = $('#repeatpsd').val();
    $('#repeatpsd').val(password.replace(/[^\w+$\.]/g,''));
  });
  
  $('#oldpsd').keyup(function(){
      var password = $('#oldpsd').val();
      $('#oldpsd').val(password.replace(/[^\w+$\.]/g,''));
  });
})