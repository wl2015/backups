/**
 * 管理员账户
 */


function SureAccount(){
  var oldpass = $('input[name="old_pass"]').val();
  var newpass = $('input[name="new_pass"]').val();
  var newpassre = $('input[name="new_pass_re"]').val();
  
  if(newpass != "" && newpassre != ""){
    if(oldpass == ""){
      $('.tip1').html("请输入旧密码");
      return false;
    }else{
      if(newpass.length < 6 || newpass.length > 10){
        $('.tip2').html("新密码必须在6位至10位之间");
        return false;
      }
      else if(!(/^[0-9a-zA-Z]*$/g).test(newpass)){
        $('.tip2').html("密码必须是数字和字母的组合");
        return false;
      }
      else if((/^[0-9a-zA-Z]*$/g).test(newpass) && newpass !== newpassre){
        $('.tip3').html("两次输入的密码不一致");
        return false;
      }else{
        document.getElementById("edit_info").submit();
      }
    }
  }else{
    alert("ok");
    return true;
  }
}

