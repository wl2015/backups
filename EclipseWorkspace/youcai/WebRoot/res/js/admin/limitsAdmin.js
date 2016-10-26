$(function(){

    
  /**
   * 创建新的管理员
   */
  $('#createAdmin').click(function(){
    
    var flag = false;
    
    var adminName = $('#adminName').val();
    var adminAccount = $('#adminAccount').val();
    var adminPassword = $('#adminPassword').val();
    var adminPhone = $('#adminPhone').val();
    var adminMail = $('#adminMail').val();
    
    var limitSetList = new Array();
   
    //获取权限复选框选中的值并赋值给数组
    $("input[name='limitSet']:checked").each(function(index, element) {
      limitSetList.push($(this).val());
      flag = true;
    });
   /* for ( var i = 0; i < limitSetList.length; i++) {
      alert(limitSetList[i]);
    }*/
    
    if (flag) {
      if (!isEmpty(adminName) && !isEmpty(adminAccount) && !isEmpty(adminPassword) 
                              && !isEmpty(adminPhone) && !isEmpty(adminMail)) {
          if (valiInput(adminName) && valiInput(adminAccount) && valiInput(adminPassword)
                                   && valiInput(adminPhone)) {
            if (CHECK.isPassword(adminPassword)) {
              //ajax提交新admin信息注册
              $.ajax({
                url : WEBROOT + "/admin/doAddAdmin.do",
                type: "post",
                dataType: "json",
                data:{
                  adminName:adminName,
                  adminAccount:adminAccount,
                  adminPassword:adminPassword,
                  adminPhone:adminPhone,
                  adminMail:adminMail,
                  
                  limitSetList:JSON.stringify(limitSetList)//转换为json数组
                },
                success: function(res) {
                  if (res.code == "手机号格式不正确") {//手机号格式不正确
                    alert(res.code);
                  }else if (res.code == AJAX_ISEXISTPHONE_CODE) {//手机号已存在
                      alert(res.code);
                  } else if(res.code == AJAX_ISEXISTACCOUNT_CODE){//账号已存在
                      alert(res.code);
                  }else if(res.code == AJAX_CREATESUCCESS_CODE){//创建管理员成功
                    alert(res.code);
                    contentSet('admin/limitPage.do');
                  }
                },
                error:function(){
                  alert("系统繁忙，请稍后操作");
                }
              });    
            } else {
              alert("密码必须是6~12位");
              return false;
            }
          } else {
            alert("信息不能含有非法字符");
            return false;
          }
      } else {
          alert("请将信息填写完整！");
          return false;
      }
    } else {
      alert("请设置管理员权限！");
      return false;
    }
   

  });
  

  
  /**
   * 修改管理员信息
   */
  $('#updateAdmin').click(function(){
    
    var flag = false;
    
    var adminId = $('#adminId').val();
    var adminName = $('#adminName').val();
    var adminAccount = $('#adminAccount').val();
    var adminPassword = $('#adminPassword').val();
    var adminPhone = $('#adminPhone').val();
    var adminMail = $('#adminMail').val();
    
    var limitSetList = new Array();
   
    //获取权限复选框选中的值并赋值给数组
    $("input[name='limitSet']:checked").each(function(index, element) {
      limitSetList.push($(this).val());
      flag = true;
    });

   /* for ( var i = 0; i < limitSetList.length; i++) {
      alert(limitSetList[i]);
    }*/

    if (flag) {
      if (!isEmpty(adminName) && !isEmpty(adminAccount) && !isEmpty(adminPassword) 
          && !isEmpty(adminPhone) && !isEmpty(adminMail)) {
          
        if (valiInput(adminName) && valiInput(adminAccount) && valiInput(adminPassword)
            && valiInput(adminPhone)) {
          if (CHECK.isPassword(adminPassword)) {
          //ajax修改管理员信息
            $.ajax({
              url : WEBROOT + "/admin/updateAdmin.do",
              type: "post",
              dataType: "json",
              data:{
                adminId:adminId,
                adminName:adminName,
                adminAccount:adminAccount,
                adminPassword:adminPassword,
                adminPhone:adminPhone,
                adminMail:adminMail,
                
                limitSetList:JSON.stringify(limitSetList)//转换为json数组
              },
              success: function(res) {
                
                if (res.code == "手机号格式不正确") {//手机号格式不正确
                  alert(res.code);
                }else if (res.code == AJAX_ISEXISTPHONE_CODE) {//手机号已存在
                    alert(res.code);
                } else if(res.code == AJAX_ISEXISTACCOUNT_CODE){//账号已存在
                    alert(res.code);
                }else if(res.code == "修改管理员成功！"){//修改管理员成功
                  alert(res.code);
                  contentSet('admin/limitPage.do');
                }
              },
              error:function(){
                alert("系统繁忙，请稍后操作");
              }
            }); 
          }else {
            alert("密码必须是6~12位");
            return false;
          }
        }else {
          alert("信息不能含有非法字符");
          return false;
        }
      }else {
            alert("请将信息填写完整！");
            return false;
          }
    } else {
        alert("请设置管理员权限！");
        return false;
    }
  });
  
});

/**
 * 删除管理员
 * @param adminId
 */
function deleteAdmin(adminId){
  
  //alert(adminId);
  
  var r=confirm("确认删除吗？"); //删除提示框
  if (r==true)
  {
      $.ajax({
        url : WEBROOT + "/admin/deleteAdmin.do",
        type: "post",
        dataType: "json",
        data:{adminId:adminId},
        success: function(res) {
          alert("管理员删除成功!");
            contentSet('admin/limitPage.do');
        },
  
        error:function(){
          alert("系统繁忙，请稍后操作");
        }
      });
  }
}







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


/**
 * 判断为空
 */
var isEmpty = function(obj) {
  if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
    return true;
  }
  return false;
};