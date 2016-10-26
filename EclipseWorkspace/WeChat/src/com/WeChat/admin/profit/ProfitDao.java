package com.WeChat.admin.profit;

import java.sql.Date;
import java.util.ArrayList;


import com.WeChat.entity.Income;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;

public interface ProfitDao {
	//按天查询线上，线下，及总收入（不分菜品）
	public ArrayList<Income> getIncome(String downDate,String upDate);
	//按周期（year,month,week,weekday,monthday）查询线上，线下，及总收入（不分菜品）
	public ArrayList<Income> getIncome(Date downDate,Date upDate, String periodType);
	//查询所有每天销售情况(菜要分类)及 每天的总体营业额，总体成本等
	public ArrayList<ArrayList<SellStatus>> getsellListofDate(String downDate,String upDate);
	//查询某一种菜在该期间销售情况及该段时间的总体情况等
	public ArrayList<ArrayList<SellStatus>> getsellListofDate(String downDate,String upDate, String dishname);
	//按统计方式（year,month,week,weekday,monthday）统计一段时间某一种菜(选了某一种菜)的销售情况
	//查询所有每月销售情况(菜要分类)及 每月的总体营业额，总体成本等(月)
	public ArrayList<ArrayList<SellStatus>> getListOfMonth(int year1,int year2, int month1, int month2);
	//查询某一种菜在该期间月销售情况及该段时间的总体情况等(月)
	public ArrayList<ArrayList<SellStatus>> getListOfMonth(int year1,int year2, int month1, int month2, String dishName);
	//按统计方式'年'统计一段时间内各种菜的销售情况（年）	
	public ArrayList<ArrayList<SellStatus>> getListOfYear(int year1,int year2);
	//按统计方式'年'统计一段时间内某一种菜的销售情况（年）
	public ArrayList<ArrayList<SellStatus>> getListOfYear(int year1,int year2, String dishName);
	
	/*public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, java.sql.Date downDate,Date upDate, String dishname);
	//按统计方式（year,month,week,weekday,monthday）统计一段时间内各种菜的销售情况
	public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, Date downDate,Date upDate );
	*/
	//插入数据
	public int addProfit(Profit profit);
}
