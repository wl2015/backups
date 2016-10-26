package com.WeChat.admin.profit;

import java.sql.Date;
import java.util.ArrayList;


import com.WeChat.entity.Income;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;

public class ProfitServiceImpl implements ProfitService{
	private ProfitDao profitDao;
	public void setProfitDao(ProfitDao profitDao) {
		this.profitDao = profitDao;
	}
	public ProfitServiceImpl(){
		this.setProfitDao(new ProfitDaoImpl());
	}
	//按统计方式（year,month,date）统计一段时间的销售情况
	public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, String downDate,String upDate,
 String dishname, String month1, String month2, String year1, String year2, String downyear, String upyear ){
		
		if(timeType.equals("date")){
			/*String down[]=downDate.split("/");
			String up[]=upDate.split("/");
			Date downDate1=new Date(Integer.parseInt(down[2])-1900,Integer.parseInt(down[1])-1, Integer.parseInt(down[0]));
			Date upDate1=new Date(Integer.parseInt(up[2])-1900,Integer.parseInt(up[1])-1, Integer.parseInt(up[0]));*/
			//System.out.println(downDate1+"..............downDate1..."+upDate1+".....upDate1.....service");
			if(dishname.equals("all")){
				return profitDao.getsellListofDate(downDate, upDate);
			}
			return profitDao.getsellListofDate(downDate, upDate, dishname);
		}
		else if(timeType.equals("year")){
			if(dishname.equals("all")){
				return profitDao.getListOfYear(Integer.parseInt(downyear), Integer.parseInt(upyear));
			}
			return profitDao.getListOfYear(Integer.parseInt(downyear), Integer.parseInt(upyear), dishname);
		}
		else if(timeType.equals("month")){
			if(dishname.equals("all")){
				return profitDao.getListOfMonth(Integer.parseInt(year1), Integer.parseInt(year2), Integer.parseInt(month1), Integer.parseInt(month2));
			}
			return profitDao.getListOfMonth(Integer.parseInt(year1), Integer.parseInt(year2), Integer.parseInt(month1), Integer.parseInt(month2), dishname);
		}
		else{
			return null;
		}			
		
	}
	
	//查询线上，线下，及总收入（不分菜品）
	public ArrayList<Income> getIncome(String TimeType, String Date1,String Date2, String Month1, String Month2, String Year1, String Year2,String Downyear, String Upyear){
		if(TimeType.equals("date")){
			/*String down[]=Date1.split("/");
			String up[]=Date2.split("/");
			Date downDate1=new Date(Integer.parseInt(down[2])-1900,Integer.parseInt(down[1])-1, Integer.parseInt(down[0]));
			Date upDate1=new Date(Integer.parseInt(up[2])-1900,Integer.parseInt(up[1])-1, Integer.parseInt(up[0]));*/
		
			return profitDao.getIncome(Date1, Date2);
		}
		else if(TimeType.equals("month")){
			Date downDate1=new Date(Integer.parseInt(Year1)-1900,Integer.parseInt(Month1)-1, 0);
			Date upDate1=new Date(Integer.parseInt(Year2)-1900,Integer.parseInt(Month2), 0);
			return profitDao.getIncome(downDate1, upDate1, TimeType);
		}
		else{
			Date downDate1=new Date(Integer.parseInt(Downyear)-1900,0, 1);
			Date upDate1=new Date(Integer.parseInt(Upyear)-1900+1,0, 0);
	
			return profitDao.getIncome(downDate1, upDate1, TimeType);
		}
				
	}
	//插入数据
	public int addProfit(Profit profit){
		Date nowdate=new Date(System.currentTimeMillis());
		
		profit.setDate(nowdate);
		profit.setMonth(nowdate.getMonth());
		profit.setYear(nowdate.getYear());
		
		return profitDao.addProfit(profit);
	}
	
}
