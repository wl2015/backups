/**
 * 添加大学信息
 */
$(document).ready(function(){
  $('#campusName').attr("onblur", "AntiSqlValid(this)");
  $('#floor').attr("onblur", "AntiSqlValid(this)");
  $('#college_name').attr("onblur", "AntiSqlValid(this)");
  
});

//防SQL注入
function AntiSqlValid(field){
  var re = /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
  if(re.test(field.value)){
    alert("请您不要在参数中输入特殊字符和SQL关键字！");
    field.value = "";
    field.focus();
    return false;
  }
}
//行内编辑
/*function Edit(campusId){
  var row = $('#addCollege').datagrid('getSelected');
  console.log("row:"+row);
  if(row){
    var index = $('#addCollege').datagrid('getRowIndex', row);
  }else{
    index = 0;
  }
  console.log("index2:" + index);
  console.log("campusId:"+campusId);
  var name = document.getElementsByClassName('name')[index].innerHTML;
  console.log(name);
  $('#dd').dialog('open');
  $('#college_name').attr('value', name);
  $('#college_id').attr('value', campusId);
}*/

//添加宿舍楼
function SureBuilding(){
  var title = $('#floor').val();
  if(title != ""){
    if($('#tt').tabs('exists', title)){
      $('#tt').tabs('select', title);
    }else{
      $('#tt').tabs('add', {
        title: title,
        closable: true
      });
    }
  }else{
    alert("请填写对应大学的宿舍");
  }
}

//修改宿舍楼
/*function Revise(dormitoryId){
  var row = $('#add_college_building').datagrid('getSelected');
  if(row){
    var index = $('#add_college_building').datagrid('getRowIndex', row);
  }else{
    index = 0;
  }
  var collegeName = document.getElementsByClassName('name')[index].innerHTML;
  var buildingName = document.getElementsByClassName('building_name')[index].innerHTML;
  $('#db').dialog({
    title: '修改宿舍',
    left: 400,
    top: 20,
    width: 320,
    height: 200,
    closed: false,
    content: '<span id="college_name"></span><input type="text" style="width:50px;" value="" id="building_name"/>'
  });
  $('#college_name').html(collegeName);
  $('#building_name').attr('value', buildingName);
  document.getElementById("building_id").value=dormitoryId;
}*/

/**
 * 添加大学校区
 * */
function addCampus(){
  //$('#campusName').attr("onblur", "AntiSqlValid(this)");
  var campusName = document.getElementById("campusName").value;
  if((/^[A-Za-z]+$/).test(campusName)){
    alert("学校名称不能是字母！");
  }else{
    $.ajax({
      url:WEBROOT+'/admin/addNewCampus.do',
      type:'post',
      dataType:'json',
      data:{
        campusName:campusName,
      },
      success:function(res){
        if(res.result == AJAX_SUCCESS_CODE){
          window.location.reload();
        }
        else if(res.result == AJAX_FAIL_CODE){
          alert("保存失败请重试！！！");
        }
      }
    });
  }
}

/**
 * 删除校区
 */
function deleteCampus(campusId){
  $.ajax({
        url:WEBROOT+'/admin/deleteCampus.do',
        type:'post',
        dataType:'json',
        data:{
          campusId:campusId,
        },
        success:function(res){
          if(res.result == AJAX_SUCCESS_CODE){
            window.location.reload();
          }
          else if(res.result == AJAX_FAIL_CODE){
            alert("删除失败！！！");
          }
        }
  });
}
/**
 * 修改校区名称
 * */
function updateCampusName(){
  var campusId = document.getElementById("college_id").value;
  var campusName = document.getElementById("college_name").value;
  alert(campusId);
  $.ajax({
      url:WEBROOT+'/admin/updateCampusName.do',
      type:'post',
      dataType:'json',
      data:{
        campusId:campusId,
        campusName:campusName
      },
      success:function(res){
        if(res.result == AJAX_SUCCESS_CODE){
          //window.location.reload();
          parent.location.reload();     //父iframe刷新
        }
        else if(res.result == AJAX_FAIL_CODE){
          alert("删除失败！！！");
        }
      }
  });
}
/**
 * 删除宿舍
 * */
function deleteDormitory(dormitoryId){
  $.ajax({
        url:WEBROOT+'/admin/deleteDormitory.do',
        type:'post',
        dataType:'json',
        data:{
          dormitoryId:dormitoryId,
        },
        success:function(res){
          if(res.result == AJAX_SUCCESS_CODE){
            window.location.reload();
          }
          else if(res.result == AJAX_FAIL_CODE){
            alert("删除失败！！！");
          }
        }
  });
}
/**
 * 修改宿舍名称
 * */
function updateDormitoryName(){
  var dormitoryId = document.getElementById("building_id").value;
  var dormitoryName = document.getElementById("building_name").value;
  //alert(campusId);
  console.log(dormitoryId);
  $.ajax({
      url:WEBROOT+'/admin/updateDormitoryName.do',
      type:'post',
      dataType:'json',
      data:{
        dormitoryId:dormitoryId,
        dormitoryName:dormitoryName
      },
      success:function(res){
        if(res.result == AJAX_SUCCESS_CODE){
          window.location.reload();
          //parent.location.reload();
        }
        else if(res.result == AJAX_FAIL_CODE){
          alert("删除失败！！！");
        }
      }
  });
}

