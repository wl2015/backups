$(function(){
  
  /**
   * 管理员找回密码
   */
  $('.vertifyPic').attr("src", WEBROOT+"/res/img/num"+Math.floor(Math.random()*(10-1)+1)+".png");
  $('#vertifyCode').maxLength(1);
  
  
  $('.vertifyPic').click(function(){
    $(this).attr("src", WEBROOT+"/res/img/num"+Math.floor(Math.random()*(10-1)+1)+".png");
  });
  $('#vertifyCode').blur(function(){
    var txtvertify = $('#vertifyCode').val();
    var picUrl = $('.vertifyPic').attr('src');
    if(picUrl.indexOf(txtvertify) < 1){
      alert("你的校验码输入错误");
    }   
  });
  
  
  
  //发送手机验证码
  $(".vertify-BTN").click(function clickCheckCode() {
    
    var $buttonNode = $(this);
    var buttonVal = $buttonNode.val();
    var phoneNode = $("#vertify-phone");//获得手机号
    var phoneNum = phoneNode.val();
    var txtvertify = $('#vertifyCode').val();//获得图片码输入
    var picUrl = $('.vertifyPic').attr('src');//获得图片里面数字
    //alert('phoneNum:'+phoneNum);
    if(picUrl.indexOf(txtvertify) < 1){
      alert("你的校验码输入错误");
      return;
    }
    if (!CHECK.isPhoneNumber(phoneNum)) {
      alert("请输入正确的手机号");
      return;
    }
    $buttonNode.unbind('click');
    $buttonNode.css('background-color', '#808080');
    $buttonNode.val("正在发送...");
    $buttonNode.parent().css("width", "87px");
    phoneNode.attr('readonly', 'readonly');
    $.ajax( {
      url : WEBROOT + '/admin/findPassOne.do',//getsmsCodeToResetpassword
      type : 'post',
      data : {
        p : phoneNum
      },
      dataType : 'json',
      dataFilter : {
        s : [ 'p' ]
      },
      success : function(res) {
        if (res.code == AJAX_SUCESS_IDENTIFYING_CODE) {
          // 获取成功,执行延迟加载程序
          successCallback();
          alert("验证成功!");
        } else if (res.code == AJAX_FAIL_IDENTIFYING_CODE) {
          whenErrorCallBack(res.msg);
          //alert("failed");
        } else {
          whenErrorCallBack(res.msg);
        }
      },
      error : function(res) {
        //alert("连接错误，请重新发送验证码");
        whenErrorCallBack("连接错误，请重新发送验证码");
      }
    });
  
  
 // 发送验证码成功时要调用的函数
    var successCallback = function() {
      var waitTime = 60;
      var timerId = setInterval(timeRun, 1000);
      function timeRun() {
        if (waitTime == 0) {
          clearInterval(timerId);
          phoneNode.removeAttr('readonly');
          $buttonNode.val(buttonVal);
          $buttonNode.parent().css("width", "83px");
          $buttonNode.click(clickCheckCode);
          $buttonNode.css('background-color', '#42C2B3');
        } else {
          waitTime--;
          $buttonNode.val('短信已发送(' + waitTime + ')');
        }
      }
    };
    // 发送验证码失败时要调用的函数
    var whenErrorCallBack = function(msg) {
      alert(msg);
      phoneNode.removeAttr('readonly');
      $buttonNode.val(buttonVal);
      $buttonNode.parent().css("width", "130px");
      $buttonNode.click(clickCheckCode);
      $buttonNode.css('background-color', '#42C2B3');
    }
  });
  
  
//跳到下一步（做相应的验证）
  $('#next-BTN').click(function() {
    var phone = $('#vertify-phone').val();//验证手机
    var sms = $('#vertify-input').val();//验证短信验证码
    //alert('phone:'+phone+'...........sms:'+sms);
    if (CHECK.isPhoneNumber(phone) && CHECK.isNumber(sms)) {
      $.ajax( {
        url : WEBROOT + '/admin/checkeSmsCode.do',
        type : 'post',
        data : {
          phoneNum : phone,
          smsCode : sms
        },
        dataFilter : {
          s : [ 'phoneNum', 'smsCode' ]
        },
        dataType : 'json',
        success : function(res) {
          if (res.code == AJAX_SUCCESS_ALERT_CODE) {
            window.location = WEBROOT + '/admin/findPassTwo.do';  
            //alert("success");
          } else {
            //$('#vertify-phone').val("");
            $('#vertify-input').val("");
            alert(res.msg);
          }
        },
        error : function() {
          alert("数据提交失败，请稍后重试");
        }
      });
    }
    else{
      alert("你的验证码或手机格号码式不对");
    }
  });
  
  
  //失去焦点后判断两次密码是否一致
  $("#repeatPassword").blur(repeatPassword);
  
  
  //点击重置密码
  $('#finishedButton').click(function(){
    
    var password = $('#password').val();
    var repeatPassword = $("#repeatPassword").val();
    
    if (!isEmpty(password)) {
      
      $.ajax({
        url : WEBROOT + "/admin/doRepeatPass.do",
        type: "post",
        data:{
          password:password,
          repeatPassword:repeatPassword
        },
        dataType: "json",
        success: function(res) {
          if (res.code == AJAX_SUCCESS_SKIP_CODE) {
            alert("密码修改成功");
            window.location = WEBROOT + '/admin/login.do';
            
          } else {
            $('#password').val("");
            $('#repeatPassword').val("");
            alert("密码重置失败，请重新修改");
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
 * 管理员重置密码
 */

/**
 * 验证两次密码是否一致
 * @returns {Boolean}
 */
function repeatPassword() {
  var password = $("#password").val();
  var repeatPassword = $("#repeatPassword").val();
  
  if (!isEmpty(password)) {
    
    if (!CHECK.isPassword(password)) {
      $("#PassMsg").html("密码长度应在6~12位");
      return false;
    } else {
      
      if (password == repeatPassword) {
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
