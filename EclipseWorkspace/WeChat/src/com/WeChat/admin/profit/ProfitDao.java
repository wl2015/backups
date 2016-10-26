package com.WeChat.admin.profit;

import java.sql.Date;
import java.util.ArrayList;


import com.WeChat.entity.Income;
import com.WeChat.entity.Profit;
import com.WeChat.entity.SellStatus;

public interface ProfitDao {
	//�����ѯ���ϣ����£��������루���ֲ�Ʒ��
	public ArrayList<Income> getIncome(String downDate,String upDate);
	//�����ڣ�year,month,week,weekday,monthday����ѯ���ϣ����£��������루���ֲ�Ʒ��
	public ArrayList<Income> getIncome(Date downDate,Date upDate, String periodType);
	//��ѯ����ÿ���������(��Ҫ����)�� ÿ�������Ӫҵ�����ɱ���
	public ArrayList<ArrayList<SellStatus>> getsellListofDate(String downDate,String upDate);
	//��ѯĳһ�ֲ��ڸ��ڼ�����������ö�ʱ������������
	public ArrayList<ArrayList<SellStatus>> getsellListofDate(String downDate,String upDate, String dishname);
	//��ͳ�Ʒ�ʽ��year,month,week,weekday,monthday��ͳ��һ��ʱ��ĳһ�ֲ�(ѡ��ĳһ�ֲ�)���������
	//��ѯ����ÿ���������(��Ҫ����)�� ÿ�µ�����Ӫҵ�����ɱ���(��)
	public ArrayList<ArrayList<SellStatus>> getListOfMonth(int year1,int year2, int month1, int month2);
	//��ѯĳһ�ֲ��ڸ��ڼ�������������ö�ʱ������������(��)
	public ArrayList<ArrayList<SellStatus>> getListOfMonth(int year1,int year2, int month1, int month2, String dishName);
	//��ͳ�Ʒ�ʽ'��'ͳ��һ��ʱ���ڸ��ֲ˵�����������꣩	
	public ArrayList<ArrayList<SellStatus>> getListOfYear(int year1,int year2);
	//��ͳ�Ʒ�ʽ'��'ͳ��һ��ʱ����ĳһ�ֲ˵�����������꣩
	public ArrayList<ArrayList<SellStatus>> getListOfYear(int year1,int year2, String dishName);
	
	/*public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, java.sql.Date downDate,Date upDate, String dishname);
	//��ͳ�Ʒ�ʽ��year,month,week,weekday,monthday��ͳ��һ��ʱ���ڸ��ֲ˵��������
	public  ArrayList<ArrayList<SellStatus>> getAllDishSellStatusByPeriodType(String timeType, Date downDate,Date upDate );
	*/
	//��������
	public int addProfit(Profit profit);
}
