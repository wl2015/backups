$(document).ready(function(){
  
  //点击切换编辑和保存
  $('td a').click(function(){
    $(this).hide().siblings('a').show();//contentEditable="true"
  });
  //点击编辑，开启编辑模式
  $('.editComment').click(function(){
    $(this).parent().siblings('.commentContent2').attr('contentEditable','true').focus();
  });

});




//新增评论
$('#doAddComment').click(function(){
  
  var commentIntro = $('#commentIntro').val();
  var commentType = $('select[name=commentType]').val();
  
  if (!isEmpty(commentIntro)) {
    
    if (valiInput(commentIntro)) {
      
      $.ajax({
        url : WEBROOT + "/comment/doAddComment.do",
        type: "post",
        data:{
          commentIntro:commentIntro,
          commentType:commentType
        },
        dataType: "json", 
        success: function(res) {

          alert("新增评论成功！");
          contentSet('comment/goodShow.do');
          
        },

        error:function(){
          alert("系统繁忙，请稍后操作");
        }
      });
    }
    else {
      alert("评论内容不能有非法字符！");
    }
  }
  else {
    alert("评论内容不能为空！");
  }

});

/**
 * 修改好评按钮
 */
//点击保存，不可编辑
function saveGoodComment(id){
  $(this).parent().siblings('.commentContent2').attr('contentEditable','false');
  
  var intro = $("#"+id+"").html();
  //alert(id+"........"+intro+"..........");
  if (!isEmpty(intro)) {
    /**
     * 判断输入是否有非法字符
     */
    if (valiInput(intro)) {
      
      updataGoodComment(id,intro);
    }
    else {

      alert("含有非法字符");
    }
  }
  else {
    alert("请将信息填写完整！");
  }
}

/**
 * 修改差评按钮
 */
//点击保存，不可编辑
function saveBadComment(id){
  $(this).parent().siblings('.commentContent2').attr('contentEditable','false');
  
  var intro = $("#"+id+"").html();
  //alert(id+"........"+intro+"..........");
  if (!isEmpty(intro)) {
    /**
     * 判断输入是否有非法字符
     */
    if (valiInput(intro)) {
      
      updataBadComment(id,intro);
    }
    else {

      alert("含有非法字符");
    }
  }
  else {
    alert("请将信息填写完整！");
  }
  
}

function updataGoodComment(id,intro){
  
  alert("ajax修改评论");

  $.ajax({
    url : WEBROOT + "/comment/updateComment.do",
    type: "post",
    dataType: "json",
    data: {
      id:id,
      intro:intro,
    },
    success: function(res) {
      alert("修改评论成功！");

      if(res.data == "0"){
        contentSet("comment/goodShow.do");//window.location = WEB_ROOT + '/comment/show.do';
      }
      else{
        alert("跳转页面失败"); 
      }          
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
  
}

function updataBadComment(id,intro){
  
  alert("ajax修改评论");

  $.ajax({
    url : WEBROOT + "/comment/updateComment.do",
    type: "post",
    dataType: "json",
    data: {
      id:id,
      intro:intro,
    },
    success: function(res) {
      alert("修改评论成功！");

      if(res.data == "0"){
        contentSet("comment/badShow.do");//window.location = WEB_ROOT + '/comment/show.do';
      }
      else{
        alert("跳转页面失败"); 
      }          
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
  
}

/**
 * ajax删除好评
 */
function deleteGoodComment(id){
 
  var r=confirm("确认删除吗？"); //删除提示框
  if (r==true)
  {
      $.ajax({
        url : WEBROOT + "/comment/deleteComment.do",
        type: "post",
        dataType: "json",
        data:{id:id},
        success: function(res) {
          alert("评论删除成功!");
          if(res.data == "0"){
            contentSet('comment/goodShow.do');
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
  else {
    contentSet('comment/goodShow.do');
  }
}


/**
 * ajax删除差评
 */
function deleteBadComment(id){
 
  var r=confirm("确认删除吗？"); //删除提示框
  if (r==true)
  {
      $.ajax({
        url : WEBROOT + "/comment/deleteComment.do",
        type: "post",
        dataType: "json",
        data:{id:id},
        success: function(res) {
          alert("评论删除成功!");
          if(res.data == "0"){
            contentSet('comment/badShow.do');
          }
          else{
            alert("跳转页面失败"); 
          }    
        },
  
        error:function(){
          alert("系统繁忙，请稍后操作");
          contentSet('comment/badShow.do');
        }
      });
  }
  else {
      contentSet('comment/badShow.do');
  }
}


/**
 * 验证输入框是否有非法字符
 */ 
var valiInput = function(obj){
  
  var patrn=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;  
  
  if (patrn.test(obj)) {
    return false;
  } else {

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

