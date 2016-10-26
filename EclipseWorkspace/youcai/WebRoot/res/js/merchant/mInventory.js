$(function(){	
	loadData();
	
	//批量修改库存
	 $(".Update-BTN").click(function(){
		 var inventoryArr = new Array();
		 $(".rowcount").each(function(i, elem){
			 //获取数据
			var inventoryId = parseInt($(elem).children().val());
			
			var surplusNum =  parseInt($(elem).next().html());
			var subNum =  parseInt($(elem).parent().find('.subnum').val());
			var addnum =  parseInt($(elem).parent().find('.addnum').val());
			var addLimit =  parseInt($(elem).parent().find('.addlimit').html());
			//封装对象
			if(subNum > 0 || addnum > 0){
				var inventory = new Object();
				inventory.iId = inventoryId;
				inventory.iNum = surplusNum - subNum + addnum;
				inventory.limitCount = addLimit - addnum;
				
				inventoryArr.push(inventory);
			}
			
		 });
		 
		  if(inventoryArr && inventoryArr.length > 0){
		   $.ajax ({
				url: WEBROOT + '/merchant/updateInventory.do',
				type:'post',
				data:JSON.stringify(inventoryArr),
					/*{
					sureinventoryArr:sureinventoryArr
				},*/
				dataType:'json',
				contentType:"application/json",
				/*dataFilter: {
					s:['p']
				},*/
				
				
				success:function(res) {
					//批量加载历史订单数据
					addData(res.rows);
					if(res.code == AJAX_FAIL_ALERT_CODE){
						alert(res.updatemsg);
					}					
				},
				error:function(){
					alert("网络异常，请稍后重试");
			   }
		   });
		  }
		  else{
			  alert('你还没有修改数据');
		  }
	 });
	 
	 
})

function subnumBlur(node){
	var surplus = parseInt($(node).parent().prev().html());
	var thisnum = parseInt($(node).val());	
	if(thisnum > surplus || thisnum < 0){
		$(node).val('0');
		alert('可减少的库存不能小于0，也不能大于现有余量');		
	}
}

function addnumBlur(node){
	var addLimit = parseInt($(node).parent().next().html());
	var thisnum = parseInt($(node).val());
	if(thisnum > addLimit || thisnum < 0){	
		$(node).val('0');
		alert('可添加的库存不能小于0，也不能大于可增加的量');
	}
}

/*初始化页面数据*/
function loadData(){
	$.ajax( {
		url : WEBROOT + '/merchant/MgetInventory.do',
		type : 'post',		
		/*dataFilter : {
			s : [ 'password2' ]
		},*/
		
		dataType : 'json',		
		success : function(res) {
			addData(res.rows);
		},
		error:function(){
			alert("网络异常，请稍后重试");
		}
		
	});
}

//加载数据
function addData(inventorylist){
	if(inventorylist && inventorylist.length > 0){		
		var tbodyhtml;
		$.each(inventorylist,function(i, elem){
			//追加2行数据,一行显示，一行默认隐藏
			tbodyhtml = tbodyhtml + '<tr>'+
			'	<td class="rowcount" >'+elem.dishName+'<input type="hidden" value=' + elem.iId + ' /></td>' +
			'	<td>'+elem.iNum+'</td>'+
			'	<td><input type="text" class="subnum" value="0" onBlur="subnumBlur(this);" onKeyup="replaceInvalidStr(this);" /></td>'+
			'	<td><input type="text" class="addnum" value="0" onBlur="addnumBlur(this);" onKeyup="replaceInvalidStr(this);" /></td>'+
			'	<td class="addlimit">'+elem.limitCount+'</td>'+
			'	<td>'+elem.iTime+'</td>'+
			'</tr>';					
		});
		$('#tt tbody').html(tbodyhtml);
	}
	else{
		alert("暂无数据");
	}
}

function replaceInvalidStr(node){
	$(node).val($(node).val().replace(/[^\d]/g,''));
}

