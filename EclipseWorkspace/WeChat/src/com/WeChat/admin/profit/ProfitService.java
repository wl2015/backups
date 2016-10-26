package com.WeChat.admin.profit;

import java.sql.Date;
import java.util.ArrayList;


import com.WeChat.entity.Income;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;

public interface ProfitService {
	//��ͳ�Ʒ�ʽ��year,month,date��ͳ��һ��ʱ����������
	public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, String downDate,String upDate,
 String dishname, String month1, String month2, String year1, String year2, String downyear, String upyear );
	
	//��ѯ���ϣ����£��������루���ֲ�Ʒ��
	public ArrayList<Income> getIncome(String TimeType, String Date1,String Date2, String Month1, String Month2, String Year1, String Year2,String Downyear, String Upyear);
	//��������
	public int addProfit(Profit profit);
}
