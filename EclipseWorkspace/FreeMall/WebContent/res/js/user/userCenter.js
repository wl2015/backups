function edit(){
  var edit = document.getElementById("edit");
  var save = document.getElementById("save");
  var cancel = document.getElementById("cancel");
  edit.style.display="none";
  save.style.display="";
  cancel.style.display="";
  
  var name = document.getElementById("name");
  var editName = document.getElementById("editName");
  var phoneNumber = document.getElementById("phoneNumber");
  var editPhoneNumber = document.getElementById("editPhoneNumber");
  var oldPSW = document.getElementById("oldPSW");
  var newPSW = document.getElementById("newPSW");
  var copyPSW = document.getElementById("copyPSW");
  name.style.display="none";
  editName.style.display="";
  phoneNumber.style.display="none";
  editPhoneNumber.style.display="";
  oldPSW.style.display="";
  newPSW.style.display="";
  copyPSW.style.display="";
}
function cancel(){
var edit = document.getElementById("edit");
  var save = document.getElementById("save");
  var cancel = document.getElementById("cancel");
  edit.style.display="";
  save.style.display="none";
  cancel.style.display="none";
  
  var name = document.getElementById("name");
  var editName = document.getElementById("editName");
  var phoneNumber = document.getElementById("phoneNumber");
  var editPhoneNumber = document.getElementById("editPhoneNumber");
  var oldPSW = document.getElementById("oldPSW");
  var newPSW = document.getElementById("newPSW");
  var copyPSW = document.getElementById("copyPSW");
  name.style.display="";
  editName.style.display="none";
  phoneNumber.style.display="";
  editPhoneNumber.style.display="none";
  oldPSW.style.display="none";
  newPSW.style.display="none";
  copyPSW.style.display="none";
}

function update(userId,userName){
  var name = document.getElementById("nameValue").value;
  var phoneNumber = document.getElementById("phoneNumberValue").value;
  var oldPSW = document.getElementById("oldPSWValue").value;
  var newPSW = document.getElementById("newPSWValue").value;
  var copyPSW = document.getElementById("copyPSWValue").value;
  if(name.length < 1){
    alert("姓名不能为空");
  }
  else if(name.length > 10){
    alert("姓名太长");
  }
  else if(!phoneNumber.match(PHONE_TEST)){
    alert("请输入正确的手机号码");
  }
  else if(oldPSW.length < 1){
    alert("旧密码不能为空");
  }
  else if(oldPSW.length < 6){
    alert("旧密码输入格式不正确");
  }
  else if(newPSW.length < 6 || newPSW.length > 20){
    alert("密码必须为6至20位");
  }
  else if(newPSW != copyPSW){
    alert("两次密码必须相同");
  }
  else{
    updateUserInfo(userId,userName,name,phoneNumber,oldPSW,newPSW);
  }
}

function updateUserInfo(userId,userName,name,phoneNumber,oldPSW,newPSW){
  $.ajax({
      url:WEBROOT+'/user/updateUserInfo.do',
      type:'post',
      dataType:'json',
      data:{
        userId:userId,
        userName:userName,
        name:name,
        phoneNumber:phoneNumber,
        oldPSW:oldPSW,
        newPSW:newPSW
      },
      success:function(res){
        if(res.result == AJAX_SUCCESS_CODE){
          window.location.reload();
        }
        else if(res.result == AJAX_FAIL_CODE){
          alert("保存失败,可能是密码输入错误");
        }
      }
    });
}