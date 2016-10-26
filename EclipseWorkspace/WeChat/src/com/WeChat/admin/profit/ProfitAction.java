package com.WeChat.admin.profit;

import java.sql.Date;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.WeChat.admin.Dish.DishDaoImpl;
import com.WeChat.entity.Income;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;
import com.opensymphony.xwork2.ActionSupport;




public class ProfitAction extends ActionSupport{
	private String timeType;
	
	private String dishname;
	private String date1,date2,year1,year2,month1,month2;
	private String downyear,upyear;

	public String getDownyear() {
		return downyear;
	}
	public void setDownyear(String downyear) {
		this.downyear = downyear;
	}
	public String getUpyear() {
		return upyear;
	}
	public void setUpyear(String upyear) {
		this.upyear = upyear;
	}
	public ArrayList<ArrayList<SellStatus>> getFatherlist() {
		return fatherlist;
	}


	private int payType;
	private Profit profit;
	
	/*private ArrayList<SellStatus> dishPeriodSellList;//分周期，也分菜名
	private ArrayList<SellStatus> dishSellList;//不分周期（该段所有时间），只分菜名
	private ArrayList<SellStatus> PeriodSellList;//分周期，不分菜名
	private ArrayList<SellStatus> sellList;//不分周期，也不分菜名(该段时间内所有的销售统计)*/
	private ArrayList<ArrayList<SellStatus>> fatherlist;
	private LinkedHashMap<String, ArrayList<SellStatus>> fathermap=new LinkedHashMap<String, ArrayList<SellStatus>>();
	public LinkedHashMap<String, ArrayList<SellStatus>> getFathermap() {
		return fathermap;
	}
	private HashMap<String, String> periodMap;
	


	public HashMap<String, String> getPeriodMap() {
		return periodMap;
	}
	public void setPeriodMap() {
		this.periodMap = new HashMap<String, String>();
		
		this.periodMap.put("year", "年");
		this.periodMap.put("month", "月");
		this.periodMap.put("date", "日");
	}


	private ArrayList<Income> incomelist;
	public ArrayList<Income> getIncomelist() {
		return incomelist;
	}
	
	
	private HashMap<String,String> dishNameMap;
	
	public ProfitService service=new ProfitServiceImpl();
	//查询菜品名
	public String getDishName(){
		dishNameMap=new DishDaoImpl().getDishNameList();
		return "sellStatus";
	}
	//按统计方式查询销售情况
	public String getAllDishSellStatusByPeriodType(){
		
		dishNameMap=new DishDaoImpl().getDishNameList();
		
		//System.out.println(dishNameMap.size()+"..................................profitaction");
		
		
		
		System.out.println(getTimeType()+"........timetype....."+ getMonth1()+ getMonth2()+"...month"+ getYear1()+"..."+getYear2()+"..."+getDownyear()+"..."+getUpyear()+"。。。year"+getDate1()+ getDate2()+".........getMonth1(), getMonth2(), getYear1(), getYear2()..." + getDishname()+"....dishname......profitaction");
		
		fatherlist=service.getAllDishSellStatusByPeriodType(getTimeType(), getDate1(), getDate2(), getDishname(), getMonth1(), getMonth2(), getYear1(), getYear2(),getDownyear(), getUpyear());
		if(fatherlist!=null && fatherlist.size()>0 && getDishname().equals("all")){
			for(ArrayList<SellStatus> selllist:fatherlist){		
				fathermap.put(selllist.get(0).getPeriod(), selllist);
			}
		}
		//System.out.println(fatherlist.get(0).get(0).getPeriod()+"....."+fatherlist.get(0).size()+"....size"+fatherlist.get(0).get(0).getTotalaccount()+"....................................action");
		/*if(fatherlist != null && fatherlist.size()>0){
			for(ArrayList<SellStatus> list:fatherlist){
				if(list.size()== 1 && list.get(0).getDishName().equals("全部菜") && list.get(0).getPeriodType().equals("天数")){
					sellList=list;
				}
				else if(list.get(0).getDishName().equals("全部菜") && !(list.get(0).getPeriodType().equals("天数"))){
					PeriodSellList=list;
				}
				else if(!(list.get(0).getDishName().equals("全部菜")) && list.get(0).getPeriodType().equals("天数")){
					dishSellList=list;
				}
				else{
					dishPeriodSellList=list;
				}
			}
			//System.out.println(fatherlist.size()+"........selllist.............profitaction");
		}*/
		return "sellStatus";
	}
	
	//分周期查询线上，线下，及总收入（不分菜品）
	public String getIncomeOfPayType(){
		
		/*String down[]=getDate1().split("/");
		String up[]=getDate2().split("/");
		downDate=new Date(Integer.parseInt(down[2])-1900,Integer.parseInt(down[1])-1, Integer.parseInt(down[0]));
		upDate=new Date(Integer.parseInt(up[2])-1900,Integer.parseInt(up[1])-1, Integer.parseInt(up[0]));*/
		incomelist=service.getIncome(getTimeType(), getDate1(), getDate2(), getMonth1(), getMonth2(), getYear1(), getYear2(),getDownyear(), getUpyear());
		//System.out.println(incomelist.size()+incomelist.get(0).getPeriod()+"........incomelist.size........................................profitaction");
		return "incomeStatus";
	}
	
	public HashMap<String, String> getDishNameMap() {
		return dishNameMap;
	}
	public void setDishNameMap(HashMap<String, String> dishNameMap) {
		this.dishNameMap = dishNameMap;
	}
	//插入数据
	public String addProfit(){
		//dishNameMap=new DishDaoImpl().getDishNameList();
		return "";
	}
	
	public Profit getProfit() {
		return profit;
	}
	public void setProfit(Profit profit) {
		this.profit = profit;
	}
	
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	
	
	public String getDishname() {
		return dishname;
	}
	public void setDishname(String dishname) {
		this.dishname = dishname;
	}
	
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	public String getYear1() {
		return year1;
	}
	public void setYear1(String year1) {
		this.year1 = year1;
	}
	public String getYear2() {
		return year2;
	}
	public void setYear2(String year2) {
		this.year2 = year2;
	}
	public String getMonth1() {
		return month1;
	}
	public void setMonth1(String month1) {
		this.month1 = month1;
	}
	public String getMonth2() {
		return month2;
	}
	public void setMonth2(String month2) {
		this.month2 = month2;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
}
