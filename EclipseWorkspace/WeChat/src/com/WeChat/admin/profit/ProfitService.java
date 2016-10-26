package com.WeChat.admin.profit;

import java.sql.Date;
import java.util.ArrayList;


import com.WeChat.entity.Income;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;

public interface ProfitService {
	//按统计方式（year,month,date）统计一段时间的销售情况
	public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, String downDate,String upDate,
 String dishname, String month1, String month2, String year1, String year2, String downyear, String upyear );
	
	//查询线上，线下，及总收入（不分菜品）
	public ArrayList<Income> getIncome(String TimeType, String Date1,String Date2, String Month1, String Month2, String Year1, String Year2,String Downyear, String Upyear);
	//插入数据
	public int addProfit(Profit profit);
}
