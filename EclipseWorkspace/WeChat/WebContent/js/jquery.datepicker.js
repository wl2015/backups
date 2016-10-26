/*
 * jQuery datepicker v1.0
 * @Author:  Travis
 * @Date:    January 17th, 2012
 * @Website: http://www.travisup.com/
 */
(function($) {

	$.fn.extend({
        datepicker:datepicker
    });

	function datepicker() {
		var input = $(this);
		var curDate = new Date();
		var year = curDate.getFullYear();
		var	month = curDate.getMonth();
		var day = curDate.getDate();
		var monthLength = [31,28,31,30,31,30,31,31,30,31,30,31];
		var reYear = /^\d{4}$/;
		var reMonth = /^\d{1,2}$/;
		
		var frame = '<style>'+
		'.datepicker .datepicker table, .datepicker tr, .datepicker th, .datepicker td{padding:0; margin:0; border:1px solid #ccc;}'+
		'.datepicker{font-size:12px; width:176px; box-shadow:1px 2px 2px rgba(0,0,0,0.2); font-family:Arial, Verdana, Tahoma; position:absolute; display:none;}'+
		'.datepicker table{border-collapse:collapse; border-spacing:0; background:#f5f5f5;}'+
		'.datepicker table th, .datepicker table td{height:24px; width:24px; line-height:24px; text-align:center;}'+
		'.cal-top{background:#eee; border:1px solid #ccc; border-bottom:none; line-height:26px; height:26px; overflow:hidden; _zoom:1;}'+
		'.cal-prev, .cal-next{float:left; height:26px; width:24px; font-weight:bold; text-align:center; cursor:pointer; font-family:serif;}'+
		'.cal-ym{float:left; width:126px; height:26px; text-align:center;}'+
		'.cal-ym input{border:1px solid #eee; background:#eee; padding:0; margin:0; outline:0; height:16px; margin-top:4px; text-align:center; cursor:pointer;}'+
		'.cal-ym input:focus{border-color:#aaa; background:#fff;}'+
		'.cal-y{width:44px;}'+
		'.cal-m{width:28px;}'+
		'.cal-day, .cal-clear, .cal-today{cursor:pointer;}'+
		'.cal-day:hover{background:#666; color:#fff;}'+
		'.cal-cur{background:#333; color:#fff;}'+
		'</style>'+
		'<div class="datepicker">'+
		'<div class="cal-top">'+
		'<span class="cal-prev">&lt;</span>'+
		'<span class="cal-ym"><input type="text" class="cal-y" /><input type="text" class="cal-m" /></span>'+
		'<span class="cal-next">&gt;</span>'+
		'</div>'+
		'<table class="cal-main">'+
		'<thead class="cal-week"><tr><th>日</th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr></thead>'+
		'<tbody class="cal-days"></tbody>'+
		'<tfoot class="cal-meta"><tr><td colspan="3">日期选择器</td><td colspan="2" class="cal-clear">清空</td><td colspan="2" class="cal-today">今天</td></tr></tfoot>'+
		'</table>'+
		'</div>';
		
			
		/*初始化日历*/	
		$("body").append(frame);	
		$(".cal-y").val(year+'年');
		$(".cal-m").val((month+1+'月'));
		$(".cal-days").html(drawDays());
		bindClick();
		
		/*点击显示插件*/
		$(this).bind("focus", function() {
			$(this).click();	
		});
		$(this).bind("click", function(e) {
			inputPos = $(this).offset();
			var pickerX = inputPos.left;
			var pickerY = inputPos.top + $(this).outerHeight();
			$(".datepicker").css({"left":pickerX, "top":pickerY}).show();
			e.stopPropagation();
			$(document).bind('click', pickerHide);
			$(".datepicker").bind('click', stopHide);
		});
		
		/*点击上一月*/
		$(".cal-prev").bind("click", function() {
			if(month == 0) {
				month = 11;
				year--;
			} else {
				month--;
			}
			$(".cal-y").val(year+'年');
			$(".cal-m").val((month+1+'月'));
			$(".cal-days").html(drawDays());
			bindClick();
		});
		
		/*点击下一月*/
		$(".cal-next").bind("click", function() {
			if(month == 11) {
				month = 0;
				year++;
			} else {
				month++;
			}		
			$(".cal-y").val(year+'年');
			$(".cal-m").val((month+1+'月'));
			$(".cal-days").html(drawDays());
			bindClick();
		});
		
		/*年份修改*/
		$(".cal-y").bind("click", function() {
			$(this).val(year).select();
		});
		$(".cal-y").bind("blur", function() {
			if(reYear.test($(this).val()) && $(this).val() != year) {
				if($(this).val() < 1900) {
					$(this).val(1900);
				} else if($(this).val() >= 2099) {
					$(this).val(2099);
				}
				year = $(this).val();
				$(this).val(year+'年');
				$(".cal-days").html(drawDays());
				bindClick();
			} else {
				$(this).val(year+'年');
			}	
		});
		
		/*月份修改*/
		$(".cal-m").bind("click", function() {
			$(this).val(month+1).select();
		});
		$(".cal-m").bind("blur", function() {
			if(reMonth.test($(this).val()) && $(this).val() != (month+1)) {
				if($(this).val() < 1) {
					$(this).val(1);
				} else if($(this).val() >= 12) {
					$(this).val(12);
				}
				month = $(this).val() - 1;
				$(this).val((month+1)+'月');
				$(".cal-days").html(drawDays());
				bindClick();
			} else {
				$(this).val((month+1)+'月');
			}	
		});
		
		/*隐藏插件函数*/
		function pickerHide() {
			$(".datepicker").hide();
			$(document).unbind('click', pickerHide);
			$(".datepicker").unbind('click', stopHide);
		}
		
		/*阻止绑定函数*/
		function stopHide(e) {
			e.stopPropagation();
		}
		
		/*显示当前日期*/
		$(".cal-today").bind("click", function() {
			year = curDate.getFullYear();
			month = curDate.getMonth();
			day = curDate.getDate();
			$(".cal-y").val(year+'年');
			$(".cal-m").val((month+1+'月'));
			$(".cal-days").html(drawDays());
			bindClick();
			input.val(showDate());
			pickerHide();
		});
		
		/*清除数据*/
		$(".cal-clear").bind("click", function() {
			input.val('');
			pickerHide();
		});
		
		/*显示日期函数*/
		function showDate() {
			var strDate = year;
			if(month < 9) {
				strDate += '-0'+(month+1);
			} else {
				strDate += '-'+(month+1);
			}
			if(day < 10) {
				strDate += '-0'+(day);
			} else {
				strDate += '-'+(day);
			}
			return strDate;
		}
		
		/*绑定日期点击函数*/
		function bindClick() {
			$(".cal-day").unbind("click").bind("click", function() {
				day = $(this).text();
				$(".cal-day").removeClass("cal-cur");
				$(this).addClass("cal-cur");
				input.val(showDate());
				pickerHide();
			});
		}
		
		/*绘制日期函数*/
		function drawDays() {
			var sign = 1;
			var days = '';
			var week = new Date(year, month, 1).getDay();
			
			if(year % 400 === 0 || year % 4 === 0 && year % 100 !== 0) {
				monthLength[1] = 29;
			} else {
				monthLength[1] = 28;
			}
			
			if(day > monthLength[month]) {
				day = monthLength[month];
			}
			
			days += '<tr>';
			for(var i = 0; i <= 6; i++) {
				if(i < week) {
					days += '<td></td>';
				} else {
					if(sign == day) {
						days += '<td class="cal-day cal-cur">'+(sign++)+'</td>';
					} else {
						days += '<td class="cal-day">'+(sign++)+'</td>';
					}
				}		
			}
			days += '</tr>';
			
			while(sign <= monthLength[month]) {
				days += '<tr>';
				for(var i = 0; i < 7; i++) {
					if(sign <= monthLength[month]) {
						if(sign == day) {
							days += '<td class="cal-day cal-cur">'+(sign++)+'</td>';
						} else {
							days += '<td class="cal-day">'+(sign++)+'</td>';
						}
					} else {
						days += '<td></td>';
					}
				}
				days += '</tr>';
			}	
			return days;
		}
	}
	
})(jQuery);