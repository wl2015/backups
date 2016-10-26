package com.WeChat.admin.profit;


import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


import com.WeChat.db.BaseDao;
import com.WeChat.entity.Income;
import com.WeChat.entity.MyMonth;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;
import com.WeChat.entity.CaculateDay;




public class ProfitDaoImpl extends BaseDao implements ProfitDao {
	//按天查询线上，线下，及总收入（不分菜品）
	public ArrayList<Income> getIncome(String downDate,String upDate){
		ArrayList<Income> incomeList=new ArrayList<Income>();
		//按天查询线上收入
		String sql1="select sum(price*account) as 'onlinecome',date from sellcondition where date between '"+downDate+"' and '"+upDate+"' and paytype=1 group by date order by date desc";
		//按天查询线下收入
		String sql2="select sum(price*account) as 'offlinecome',date from sellcondition where date between '"+downDate+"' and '"+upDate+"' and paytype=0 group by date order by date desc";
		//按天查询总收入
		String sql3="select sum(price*account) as 'allincome',date from sellcondition where date between '"+downDate+"' and '"+upDate+"' group by date order by date desc";
		try{
			ResultSet rs=super.query(sql3, null);
			
			while(rs.next()){
				Income income=new Income();
				income.setAllIncome(rs.getInt(1));
				income.setPeriodType("date");
				income.setPeriod(rs.getDate(2)+"");
				incomeList.add(income);
			}
			rs=super.query(sql1, null);
			int i=0;
			while(rs.next()){
				String date1=rs.getDate(2).toString();
				while(!(incomeList.get(i).getPeriod().equals(date1))){
					incomeList.get(i).setOnlineIncome(0);
					i++;
				}
				incomeList.get(i).setOnlineIncome(rs.getInt(1));
				i++;
			}
			rs=super.query(sql2, null);	
			i=0;
			while(rs.next()){
				String date1=rs.getDate(2).toString();
				while(!(incomeList.get(i).getPeriod().equals(date1))){
					incomeList.get(i).setOffLineIncome(0);
					i++;
				}
				incomeList.get(i).setOffLineIncome(rs.getInt(1));
				i++;
			}
			
		}catch(Exception e){e.getStackTrace(); return null;}
		return incomeList;
	}
	//按周期（year,month）查询线上，线下，及总收入（不分菜品）
	public ArrayList<Income> getIncome(Date downDate,Date upDate, String periodType){
		ArrayList<Income> incomeList=new ArrayList<Income>();
		if(periodType.equals("year")){
			//按周期查询线上收入
			String sql1="select sum(price*account) as 'onlinecome',year from sellcondition where date between '"+downDate+"' and '"+upDate+"' and paytype=1 group by year order by year desc";
			//按周期查询线下收入
			String sql2="select sum(price*account) as 'offlinecome',year from sellcondition where date between '"+downDate+"' and '"+upDate+"' and paytype=0 group by year order by year desc";
			//按周期查询总收入
			String sql3="select sum(price*account) as 'allincome',year from sellcondition where date between '"+downDate+"' and '"+upDate+"' group by year order by year desc";
			
			try{
				ResultSet rs=super.query(sql3, null);
				
				while(rs.next()){
					Income income=new Income();
					income.setAllIncome(rs.getInt(1));
					income.setPeriodType("年");					
					income.setPeriod(rs.getInt(2)+"年");									
					incomeList.add(income);
				}
				rs=super.query(sql1, null);
				int i=0;
				while(rs.next()){
					String time=rs.getInt(2)+"年";
					while(!(incomeList.get(i).getPeriod().equals(time))){
						incomeList.get(i).setOnlineIncome(0);
						i++;
					}
					incomeList.get(i).setOnlineIncome(rs.getInt(1));
					i++;
				}
				rs=super.query(sql2, null);	
				i=0;
				while(rs.next()){
					String time=rs.getInt(2)+"年";
					while(!(incomeList.get(i).getPeriod().equals(time))){
						incomeList.get(i).setOffLineIncome(0);
						i++;
					}
					incomeList.get(i).setOffLineIncome(rs.getInt(1));
					i++;
				}
				
			}catch(Exception e){e.getStackTrace(); return null;}
		}
		else{
			//按月查询线上收入
			String sql1="select sum(price*account) as 'onlinecome',year, month from sellcondition where date between '"+downDate+"' and '"+upDate+"' and paytype=1 group by  year, month order by  year, month desc";
			//按月查询线下收入
			String sql2="select sum(price*account) as 'offlinecome',year, month from sellcondition where date between '"+downDate+"' and '"+upDate+"' and paytype=0 group by  year, month order by  year, month desc";
			//按月查询总收入
			String sql3="select sum(price*account) as 'allincome',year, month from sellcondition where date between '"+downDate+"' and '"+upDate+"' group by year, month order by  year, month desc";
			
			try{
				ResultSet rs=super.query(sql3, null);
				
				while(rs.next()){
					Income income=new Income();
					income.setAllIncome(rs.getInt(1));
					income.setPeriodType("月");					
					income.setPeriod(rs.getInt(2)+"年"+rs.getInt(3)+"月");									
					incomeList.add(income);
				}
				rs=super.query(sql1, null);
				int i=0;
				while(rs.next()){
					String time=rs.getInt(2)+"年"+rs.getInt(3)+"月";
					while(!(incomeList.get(i).getPeriod().equals(time))){
						incomeList.get(i).setOnlineIncome(0);
						i++;
					}
					incomeList.get(i).setOnlineIncome(rs.getInt(1));
					i++;
				}
				rs=super.query(sql2, null);	
				i=0;
				while(rs.next()){
					String time=rs.getInt(2)+"年"+rs.getInt(3)+"月";
					while(!(incomeList.get(i).getPeriod().equals(time))){
						incomeList.get(i).setOffLineIncome(0);
						i++;
					}
					incomeList.get(i).setOffLineIncome(rs.getInt(1));
					i++;
				}
				
			}catch(Exception e){e.getStackTrace(); return null;}
		}
		return incomeList;
	}
	
	private CaculateDay caculateday=new CaculateDay();
	/* public static void main(String args[]){
		 ArrayList<Date> datelist=null;
			//查询该段时间有哪些天
			String sql="select date from sellconditionwhere date between '"+downDate+"' and '"+upDate+"'"; 
			ResultSet rs1=super.query(sql, null);
		 if(rs1.next()){
				datelist=(ArrayList<Date>)rs1.getArray(1);
			}
		 System.out.print(datelist.get(0));
	 }*/
	
	//查询所有每天销售情况(菜要分类)及 每天的总体营业额，总体成本等（日）
	public ArrayList<ArrayList<SellStatus>> getsellListofDate(String downDate,String upDate){
		System.out.println(downDate+"................"+upDate+".............................dao");
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		
		
		ArrayList<SellStatus> childsellList3=new ArrayList<SellStatus>();
		ArrayList<Date> datelist=new ArrayList<Date>();
			//查询该段时间有哪些天
			String sql="select distinct date from sellcondition where date between '"+downDate+"' and '"+upDate+"' order by date desc"; 
			
			
			
			//该期间内的总体营业额，总体成本等
			/*select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account)
			as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '2013-01-01' and '2013-06-01'*/
			//各种菜每天的销售情况
			String sql3="select sum(cost*account) as 'totalcost',sum(price*account) "+
			"as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '"+downDate+"' and '"+upDate+"'";
			System.out.println(sql3);
			ResultSet rs1=super.query(sql, null);
			
			
			
			try{
				
					while(rs1.next()){
						datelist.add(rs1.getDate("date"));
					}
				//System.out.println(datelist.get(0)+".....daoimpl...."+datelist.get(1));
				
				ResultSet rs;
				//各种菜每天的销售情况
				for(Date date:datelist){
					String sql1="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit',dish_name,cost,price from sellcondition where date='"+date+"' group by dish_name";
					ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();
					rs=super.query(sql1, null);
					SellStatus sumSellstatus=new SellStatus();
					sumSellstatus.setDishName("合计");
					sumSellstatus.setPeriodType("date");
					sumSellstatus.setPeriod(date+"");
					float totalcost=0, totalincome=0, totalprofit=0;
					while(rs.next()){						
							SellStatus sellstatus=new SellStatus();
							sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
							sellstatus.setTotalcost(rs.getFloat("totalcost"));
							sellstatus.setTotalIncome(rs.getFloat("totalincome"));
							sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
							sellstatus.setDishName(rs.getString("dish_name"));
							sellstatus.setPeriodType("date");
							sellstatus.setPeriod(date+"");
							sellstatus.setCost(rs.getFloat("cost"));
							sellstatus.setPrice(rs.getFloat("price"));
							childsellList1.add(sellstatus);
							totalcost=totalcost+rs.getFloat("totalcost");
							totalincome=totalincome+rs.getFloat("totalincome");
							totalprofit=totalprofit+rs.getFloat("totalprofit");
					}
					sumSellstatus.setTotalcost(totalcost);
					sumSellstatus.setTotalIncome(totalincome);
					sumSellstatus.setTotalprofit(totalprofit);
					sumSellstatus.setTotalaccount("-");
					childsellList1.add(sumSellstatus);
					fathersellList.add(childsellList1);					
				}
				
				
				rs=super.query(sql3, null);
				if(rs.next()){
					SellStatus sellstatus=new SellStatus();
					
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setPeriodType("合计天数");
					sellstatus.setPeriod(downDate+"至"+upDate);
					sellstatus.setDishName("合计");
					sellstatus.setTotalaccount("-");
					childsellList3.add(sellstatus);
				}
				fathersellList.add(childsellList3);
				//System.out.println(fathersellList.size()+"...........................................profitdaoimply");
				
				}catch(Exception e){e.printStackTrace(); return null;}
				//System.out.println(fathersellList.size()+fathersellList.get(0).get(0).getDishName()+".......daoimpl");
				return fathersellList;
		}
		
	//查询某一种菜在该期间日销售情况及该段时间的总体情况等（日）
	public ArrayList<ArrayList<SellStatus>> getsellListofDate(String downDate,String upDate, String dishname){
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();		

			//根据菜品名统计各天的销售情况
		String	sql="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit',date,cost,price "+
			"from sellcondition where date between '"+downDate+"' and '"+upDate+"' and dish_name='"+dishname+"' group by date order by date desc";
			//根据菜品名统计该时间段内的销售总额
			//select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) 
			//as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '2013-01-01' and '2013-06-01' and paytype=1
			
		String	sql2="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) "+
					"as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where dish_name='"+dishname+"' and date between '"+downDate+"' and '"+upDate+"'";
			
			try{
				ResultSet rs=super.query(sql, null);
				while(rs.next()){
					SellStatus sellstatus=new SellStatus();
					sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setDishName(dishname);
					sellstatus.setPeriodType("date");
					sellstatus.setPeriod(rs.getDate("date")+"");
					sellstatus.setCost(rs.getFloat("cost"));
					sellstatus.setPrice(rs.getFloat("price"));
					childsellList1.add(sellstatus);
				}
				
				rs=super.query(sql2, null);
				if(rs.next()){
					SellStatus sellstatus=new SellStatus();
					sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setPeriodType("合计天数");
					sellstatus.setPeriod(downDate+"至"+upDate);
					sellstatus.setDishName("合计");
					childsellList1.add(sellstatus);
				}
				fathersellList.add(childsellList1);
				
				//System.out.println(fathersellList.size()+"...........................................profitdaoimply");
				
				}catch(Exception e){e.printStackTrace(); return null;}
				return fathersellList;
	}
	
	//查询所有每月销售情况(菜要分类)及 每月的总体营业额，总体成本等(月)
	public ArrayList<ArrayList<SellStatus>> getListOfMonth(int year1,int year2, int month1, int month2){
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		ArrayList<SellStatus> childsellList3=new ArrayList<SellStatus>();
		ArrayList<MyMonth> monthlist=new ArrayList<MyMonth>();
		//Date downDate1=new Date(Integer.parseInt(down[2])-1900,Integer.parseInt(down[1])-1, Integer.parseInt(down[0]));
		//Date upDate1=new Date(Integer.parseInt(up[2])-1900,Integer.parseInt(up[1])-1, Integer.parseInt(up[0]));
		Date downDate=new Date(year1-1900,month1-1, 1);
		Date upDate=new Date(year2-1900,month2, 0);
		System.out.println(downDate+".........down...."+upDate+".........up...................update...............................daoimpl");
		//查询该段时间有哪些月
		String sql="select distinct year, month from sellcondition where date between '"+downDate+"' and '"+upDate+"' order by year, month desc"; 
		//各种菜每月的销售情况
		String sql1="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit'"+
		",dish_name,cost,price from sellcondition where month=? and year=? group by dish_name";
		
		//该期间内的总体营业额，总体成本等
		/*select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account)
		as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '2013-01-01' and '2013-06-01'*/
		String sql3="select sum(cost*account) as 'totalcost',sum(price*account) "+
		"as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '"+downDate+"' and '"+upDate+"'";
		
		
		
		ResultSet rs1=super.query(sql, null);
		try{
			
			while(rs1.next()){
				MyMonth month=new MyMonth();
				month.setYear(rs1.getInt(1));
				month.setMonth(rs1.getInt(2));
				monthlist.add(month);				
			}
			
			ResultSet rs;
			//各种菜每月的销售情况
			for(MyMonth month:monthlist){
				ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();
				rs=super.query(sql1, new Object[]{month.getMonth(), month.getYear()});
				SellStatus sumSellstatus=new SellStatus();
				sumSellstatus.setDishName("总计");
				sumSellstatus.setPeriodType("month");
				sumSellstatus.setPeriod(""+month.getYear()+"年"+month.getMonth()+"月");
				float totalcost=0, totalincome=0, totalprofit=0;
				while(rs.next()){						
						SellStatus sellstatus=new SellStatus();
						sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
						sellstatus.setTotalcost(rs.getFloat("totalcost"));
						sellstatus.setTotalIncome(rs.getFloat("totalincome"));
						sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
						sellstatus.setDishName(rs.getString("dish_name"));
						sellstatus.setPeriodType("month");
						sellstatus.setPeriod(""+month.getYear()+"年"+month.getMonth()+"月");
						sellstatus.setCost(rs.getFloat("cost"));
						sellstatus.setPrice(rs.getFloat("price"));
						childsellList1.add(sellstatus);
						totalcost=totalcost+rs.getFloat("totalcost");
						totalincome=totalincome+rs.getFloat("totalincome");
						totalprofit=totalprofit+rs.getFloat("totalprofit");
						
				}
				sumSellstatus.setTotalcost(totalcost);
				sumSellstatus.setTotalIncome(totalincome);
				sumSellstatus.setTotalprofit(totalprofit);
				sumSellstatus.setTotalaccount("-");
				childsellList1.add(sumSellstatus);
				fathersellList.add(childsellList1);					
			}
			
			
			rs=super.query(sql3, null);
			if(rs.next()){
				SellStatus sellstatus=new SellStatus();
				
				sellstatus.setTotalcost(rs.getFloat("totalcost"));
				sellstatus.setTotalIncome(rs.getFloat("totalincome"));
				sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
				sellstatus.setPeriodType("合计销售月数");
				sellstatus.setPeriod(year1+"年"+month1+"月 至 "+year2+"年"+month2+"月");
				sellstatus.setDishName("合计");
				sellstatus.setTotalaccount("-");
				childsellList3.add(sellstatus);
			}
			
			fathersellList.add(childsellList3);
			System.out.println(fathersellList.get(0).get(0).getPeriod()+"..........................daoimpl");
			return fathersellList;
			//System.out.println(fathersellList.size()+"...........................................profitdaoimply");
			
			}catch(Exception e){e.printStackTrace(); return null;}
			
	}
	//查询某一种菜在该期间月销售情况及该段时间的总体情况等(月)
	public ArrayList<ArrayList<SellStatus>> getListOfMonth(int year1,int year2, int month1, int month2, String dishName){
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();
		Date downDate=new Date(year1-1900,month1-1, 1);
		Date upDate=new Date(year2-1900,month2, 0);
		//System.out.println(downDate+".........down...."+upDate+".........up...................update...............................daoimpl");				
		
			//根据菜品名统计各月的销售情况
		String 	sql="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit',month,year,cost,price "+
			"from sellcondition where date between '"+downDate+"' and '"+upDate+"' and dish_name='"+dishName+"' group by year, month order by year, month desc";
			//根据菜品名统计该时间段内的销售总额
			//select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) 
			//as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '2013-01-01' and '2013-06-01' and paytype=1
			
		String 	sql2="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) "+
					"as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where dish_name='"+dishName+"' and date between '"+downDate+"' and '"+upDate+"'";
			
			try{
				ResultSet rs=super.query(sql, null);
				while(rs.next()){
					SellStatus sellstatus=new SellStatus();
					sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setDishName(dishName);
					sellstatus.setPeriodType("month");
					sellstatus.setPeriod(""+rs.getInt("year")+"年"+rs.getInt("month")+"月");
					sellstatus.setCost(rs.getFloat("cost"));
					sellstatus.setPrice(rs.getFloat("price"));
					childsellList1.add(sellstatus);
				}
				
				rs=super.query(sql2, null);
				if(rs.next()){
					SellStatus sellstatus=new SellStatus();
					sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setPeriodType("合计销售月数");
					sellstatus.setPeriod(year1+"年"+month1+"月 至 "+year2+"年"+month2+"月");
					sellstatus.setDishName("合计");
					childsellList1.add(sellstatus);
				}
				fathersellList.add(childsellList1);
				
				//System.out.println(fathersellList.size()+"...........................................profitdaoimply");
				
				}catch(Exception e){e.printStackTrace(); return null;}
				return fathersellList;
	}
	
	//按统计方式'年'统计一段时间内各种菜的销售情况（年）	
	public ArrayList<ArrayList<SellStatus>> getListOfYear(int year1,int year2){	
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		
		
		ArrayList<SellStatus> childsellList2=new ArrayList<SellStatus>();
					 
			//各种菜每年的销售情况
			String sql1="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit'"+
			",dish_name,cost,price from sellcondition where year=? group by dish_name";
			
			//该期间内的总体营业额，总体成本等
			/*select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account)
			as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where date between '2013-01-01' and '2013-06-01'*/
			String sql3="select sum(cost*account) as 'totalcost',sum(price*account) "+
			"as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where year between "+year1+" and "+year2;			
			
				
			try{
				ResultSet rs;
				//各种菜每年的销售情况
				for(int year=year1; year<=year2; year++){
					ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();
					rs=super.query(sql1, new Object[]{year});
					SellStatus sumSellstatus=new SellStatus();
					sumSellstatus.setDishName("合计");
					sumSellstatus.setPeriodType("year");
					sumSellstatus.setPeriod(year+"年");
					float totalcost=0, totalincome=0, totalprofit=0;
					while(rs.next()){						
							SellStatus sellstatus=new SellStatus();
							sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
							sellstatus.setTotalcost(rs.getFloat("totalcost"));
							sellstatus.setTotalIncome(rs.getFloat("totalincome"));
							sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
							sellstatus.setDishName(rs.getString("dish_name"));
							sellstatus.setPeriodType("year");
							sellstatus.setPeriod(year+"年");
							sellstatus.setCost(rs.getFloat("cost"));
							sellstatus.setPrice(rs.getFloat("price"));
							childsellList1.add(sellstatus);
							totalcost=totalcost+rs.getFloat("totalcost");
							totalincome=totalincome+rs.getFloat("totalincome");
							totalprofit=totalprofit+rs.getFloat("totalprofit");
					}
					sumSellstatus.setTotalcost(totalcost);
					sumSellstatus.setTotalIncome(totalincome);
					sumSellstatus.setTotalprofit(totalprofit);
					sumSellstatus.setTotalaccount("-");
					childsellList1.add(sumSellstatus);
					fathersellList.add(childsellList1);					
				}
				
				
				rs=super.query(sql3, null);
				if(rs.next()){
					SellStatus sellstatus=new SellStatus();
					
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setPeriodType("合计年数");
					sellstatus.setPeriod(year1+"年至"+year2+"年");
					sellstatus.setDishName("合计");
					sellstatus.setTotalaccount("-");
					childsellList2.add(sellstatus);
				}
				fathersellList.add(childsellList2);
				//System.out.println(fathersellList.size()+"...........................................profitdaoimply");
				
				}catch(Exception e){e.printStackTrace(); return null;}
				return fathersellList;
		}
		
	//按统计方式'年'统计一段时间内某一种菜的销售情况（年）
	public ArrayList<ArrayList<SellStatus>> getListOfYear(int year1,int year2, String dishName){
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();		

			//根据菜品名统计各年的销售情况
		String	sql="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit',year,cost,price "+
			"from sellcondition where year between "+year1+" and "+year2+" and dish_name='"+dishName+"' group by year order by year desc";
			//根据菜品名统计该时间段内的销售总额
		String	sql2="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) "+
					"as 'totalincome',sum(account*(price-cost)) as 'totalprofit' from sellcondition where dish_name='"+dishName+"' and year between "+year1+" and "+year2;
			
			try{
				ResultSet rs=super.query(sql, null);
				while(rs.next()){
					SellStatus sellstatus=new SellStatus();
					sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setDishName(dishName);
					sellstatus.setPeriodType("year");
					sellstatus.setPeriod(rs.getInt("year")+"年");
					sellstatus.setCost(rs.getFloat("cost"));
					sellstatus.setPrice(rs.getFloat("price"));
					childsellList1.add(sellstatus);
				}
				
				rs=super.query(sql2, null);
				if(rs.next()){
					SellStatus sellstatus=new SellStatus();
					sellstatus.setTotalaccount(rs.getInt("totalaccount")+"");
					sellstatus.setTotalcost(rs.getFloat("totalcost"));
					sellstatus.setTotalIncome(rs.getFloat("totalincome"));
					sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
					sellstatus.setPeriodType("合计年数");
					sellstatus.setPeriod(year1+"年至"+year2+"年");
					sellstatus.setDishName("合计");
					childsellList1.add(sellstatus);
				}
				fathersellList.add(childsellList1);
				
				//System.out.println(fathersellList.size()+"...........................................profitdaoimply");
				
				}catch(Exception e){e.printStackTrace(); return null;}
				return fathersellList;
	}
	
	//按统计方式（year,month）统计一段时间某一种菜(选了某一种菜)的销售情况
	/*public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, Date downDate,Date upDate, String dishname){
		
		ArrayList<ArrayList<SellStatus>> fathersellList=new ArrayList<ArrayList<SellStatus>>();
		ArrayList<SellStatus> childsellList1=new ArrayList<SellStatus>();
		ArrayList<SellStatus> childsellList2=new ArrayList<SellStatus>();
		String sql="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit', "
			+timeType+" from sellcondition where date between '"+downDate+"' and '"+upDate+"' and dish_name='"+dishname+"'  group by "+timeType+" order by "+timeType+" desc";
		//该菜在该时间段内(所有周期之和)的销售总额
		String sql2="select sum(account) as 'totalaccount',sum(cost*account) as 'totalcost',sum(price*account) as 'totalincome',sum(account*(price-cost)) as 'totalprofit'"+
			" from sellcondition where date between '"+downDate+"' and '"+upDate+"' and dish_name='"+dishname+"'";
		
		//System.out.println(sql+"..........daoimpl");
		//System.out.println(sql2+"..........daoimpl");
		
		try {
			ResultSet rs=super.query(sql, null);
			while(rs.next()){
				SellStatus sellstatus=new SellStatus();
				sellstatus.setTotalaccount(rs.getInt("totalaccount"));
				sellstatus.setTotalcost(rs.getFloat("totalcost"));
				sellstatus.setTotalIncome(rs.getFloat("totalincome"));
				sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
				sellstatus.setDishName(dishname);
				sellstatus.setPeriodType(timeType);				
				sellstatus.setPeriod(rs.getInt(timeType)+"");
				childsellList1.add(sellstatus);				
			}
			fathersellList.add(childsellList1);
			rs=super.query(sql2, null);
			if(rs.next()){
				SellStatus sellstatus=new SellStatus();
				sellstatus.setTotalaccount(rs.getInt("totalaccount"));
				sellstatus.setTotalcost(rs.getFloat("totalcost"));
				sellstatus.setTotalIncome(rs.getFloat("totalincome"));
				sellstatus.setTotalprofit(rs.getFloat("totalprofit"));
				sellstatus.setDishName(dishname);
				sellstatus.setPeriodType("天数");
				sellstatus.setPeriod(caculateday.cal(upDate)-caculateday.cal(downDate)+"");
				childsellList2.add(sellstatus);
			}
			fathersellList.add(childsellList2);
		} catch (SQLException e) {e.printStackTrace(); return null; }
		return fathersellList;
	}*/
	
	//插入数据
	public int addProfit(Profit profit){//select id from sellcondition where dish_name='干锅兔' and date='2013-05-19'
		String sql1="select id from sellcondition where dish_name='"+profit.getDishName()+"' and date='"+profit.getDate()+"'";
		
		//update sellcondition set account=account+3 where id='14'
		//(修改数据)
		String sql2="update sellcondition set account=account+"+profit.getAccount()+" where id='?'";
		//(插入数据)
		String sql="insert into sellcondition values(0,?,?,?,?,?,?,?,?)";
		
		
		int id=0;
		int rows;
		try {
		ResultSet rs=super.query(sql1, null);
		
			if(rs.next()){
				id=rs.getInt(1);
			}
		
		if(id==0){
			rows=super.update(sql, new Object[]{
					profit.getDishName(),
					profit.getCost(),
					profit.getPrice(),
					profit.getAccount(),
					profit.getMonth(),
					profit.getDate(),
					profit.getYear(),
					profit.getPaytype()
			});
			return rows;
		}
		else{
			rows=super.update(sql2, new Object[]{id});
			return rows;
		}
		} catch (SQLException e) {e.printStackTrace(); return 0;}
	}
	/*public ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(
			String timeType, Date downDate, Date upDate) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
}
