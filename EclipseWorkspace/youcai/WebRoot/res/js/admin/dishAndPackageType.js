$(function(){
    
  /**
   * 添加类别
   */
    $('#addTypeBtn').click(function(){
      
      var typeName = $('input[name=typeName]').val();
      var target = $('select[name=target]').val();
      
      //alert("添加类别"+typeName +"..."+target);
      
      if(!isEmpty(typeName) && !isEmpty(target)){//如果类别名称不为空
          /**
           * 判断输入是否有非法字符
           */
          if (valiInput(typeName)) {
            //ajax提交新增类别
            $.ajax({
              url : WEBROOT + "/dish/addTypeForDish.do",
              type: "post",
              dataType: "json",
              data: {
                typeName:typeName,
                target:target
              },
              success: function(res) {
                alert("添加类别成功！");
                if (res.target == 1) {
                  contentSet("dish/TypeForDish.do");//window.location = WEBROOT + '/dish/show.do';
                }else if (res.target == 2) {
                  contentSet("dish/TypeForPackage.do");//window.location = WEBROOT + '/dish/show.do';
                }
              },
              error:function(){
                alert("系统繁忙，请稍后操作");
              }
            });
          }
          else {
            alert("含有非法字符");
            return false;
          }

      }else {
        alert("请将信息填写完整！");
        return false;
      }

    });
  
});


/**
 * ajax删除菜品/套餐类别
 */
function deleteType(typeId,target){
 
/*  alert("typeId="+typeId);
  alert("target="+target);*/
  var r=confirm("确认删除吗？"); //删除提示框
  if (r==true)
  {
      $.ajax({
        url : WEBROOT + "/dish/deleteType.do",
        type: "post",
        dataType: "json",
        data:{
          typeId:typeId,
          target:target
        },
        success: function(res) {
          alert("类别删除成功!");
          if(res.target == 1){
            contentSet('dish/TypeForDish.do');
          }
          else if (res.target == 2) {
            contentSet('dish/TypeForPackage.do');
          }
          else{
            alert("跳转页面失败"); 
          }    
        },
  
        error:function(){
          alert("系统繁忙，请稍后操作");
          contentSet('comment/goodShow.do');
        }
      });
  }
  
}





/**
 * 验证输入框是否有非法字符
 */ 
var valiInput = function(obj){
  
  var patrn=/[`!@#$%^*()_+<>?:"{},.\/;'[\]]/im;  
  
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