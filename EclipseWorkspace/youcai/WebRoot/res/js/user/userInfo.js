$(function(){
  $('#nameModify').click(function(){
    nameModify();
  });
  $('#emailModify').click(function(){
    emailModify();
  });
  
  $('#nameInput').keyup(function(){
    var name = $('#nameInput').val();
    $('#nameInput').val(name.replace(/[^\u4E00-\u9FA5]/g,''));
  });
});

//姓名修改
function nameModify(){
  $('#nameInput').val($('#nameA').html());
  $('#nameA').hide();
  $('#nameModify').hide();
  $('#nameInput').show();
  $('#nameSave').show();
  $('#nameSave').click(function(){
    nameSave();
  });
}

//姓名保存
function nameSave(){
  var name = $('#nameInput').val();
  updateName(name);
  $('#nameInput').hide();
  $('#nameSave').hide();
  $('#nameA').show();
  $('#nameModify').show();
}

// 将修改后的姓名传输到后台
function updateName(name){
  $.ajax({
      url:WEBROOT+'/user/updateUserName.do',
      type:'post',
      dataType:'json',
      data:{
        name:name
      },
      success:function(res){
        if(res.resultCode==AJAX_CODE_SUCCESS){
          $('#nameA').html(name);
        }
        else if(res.resultCode==AJAX_CODE_FAIL){
          alert("修改失败，请重试");
        }
      }
    });
}

//邮箱修改
function emailModify(){
  $('#emailInput').val($('#emailA').html());
  $('#emailModify').hide();
  $('#emailA').hide();
  $('#emailInput').show();
  $('#emailSave').show();
  $('#emailSave').click(function(){
    emailSave();
  });
}

//邮箱保存
function emailSave(){
  var mail = $('#emailInput').val();
  
  var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  if (filter.test(mail)){
    updateMail(mail);
    $('#emailModify').show();
    $('#emailA').show();
    $('#emailInput').hide();
    $('#emailSave').hide();
  }
  else {
    alert('您的电子邮件格式不正确');
    return false;
  }

}

//将修改后的邮箱传输到后台
function updateMail(mail){
  $.ajax({
      url:WEBROOT+'/user/updateUserMail.do',
      type:'post',
      dataType:'json',
      data:{
        mail:mail
      },
      success:function(res){
        if(res.resultCode == AJAX_CODE_SUCCESS){
          $('#emailA').html(mail);
        }
        else if(res.resultCode == AJAX_CODE_FAIL){
          alert("修改失败，请重试");
        }
      }
    });
}
