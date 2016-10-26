$(function(){

  /**
   * 管理员修改密码
   */
  //失去焦点后判断两次密码是否一致
  $("#beforePass").blur(valiPasswordOne);
  $("#afterPassTwo").blur(valiPasswordTwo);
  
  
  //点击修改密码
  $('#ModifyPass').click(function(){
    
    var beforePass = $('#beforePass').val();
    var afterPass = $("#afterPassOne").val();
    
    if (!isEmpty(beforePass)) {
      
      $.ajax({
        url : WEBROOT + "/admin/doModifyPass.do",
        type: "post",
        data:{
          beforePass:beforePass,
          afterPass:afterPass
        },
        dataType: "json",
        success: function(res) {
          if (res.code == "MODIFYPASSWORD_ERROR") {
            alert("原密码不正确！");
          }
          else {
            alert("密码修改成功！请重新登录");
             window.location = WEBROOT + '/admin/login.do';
          } 
        },
        error:function(){
            alert("服务器繁忙，请稍后再试");
         
        }
      });
      
    }
    else {
      alert("请输入原密码");
      return false;
    }
  });


});


  
/**
 * 管理员修改密码
 */



/**
 * 验证新密码是否符合格式（要加上是否与原密码相同）
 * @returns {Boolean}
 */
function valiPasswordOne() {
  
  var beforePass = $('#beforePass').val();
  var passOne = $("#afterPassOne").val();
  
  if (!isEmpty(beforePass)) {
    if (!CHECK.isPassword(passOne)) {
      $("#PassMsg").html("新密码长度应在6~12位");
      return false;
    } else {
        $("#PassMsg").html("通过");
        return true;
    }
  } else {
      alert("请正确填写原密码！");
      return false;
  }
  

}


/**
 * 验证两次密码是否一致
 * @returns {Boolean}
 */
function valiPasswordTwo() {
  var passOne = $("#afterPassOne").val();
  var passTwo = $("#afterPassTwo").val();
  
  if (!isEmpty(passOne)) {
    
    if (!CHECK.isPassword(passOne)) {
      $("#PassMsg").html("密码长度应在6~12位");
      return false;
    } else {
      
      if (passOne == passTwo) {
        $("#PassMsg").html("通过");
        return true;
      } else {
        $("#PassMsg").html("两次密码不一致");
        return false;
      }
      
    }
  } else {
    alert("新密码不能为空！");
    return false;
  }
  
}



/**
 * 判断为空
 */
var isEmpty = function(obj) {
  if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
    return true;
  }
  return false;
};
