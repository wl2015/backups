package com.WeChat.admin.Common.dao;

import java.util.List;

import com.WeChat.admin.Common.entity.ChooseSendPeople;
import com.WeChat.admin.Common.entity.DoneOrder;
import com.WeChat.admin.Common.entity.OrderMessage;

public interface CommonDao {
	public List<OrderMessage> getOrderMessageList(int pageCount);
	public List<ChooseSendPeople> getSendPeopleList();
	public void addPeopleId(int orderformId, int PeopleId);
	public List<DoneOrder> getTodayDoneOrderList(int pageCount);
	public List<DoneOrder> getAllDoneOrderlist(int pageCount);
	public List<DoneOrder> getAnydayDoneOrderList(int num, int pageCount);
	public List<DoneOrder> getDoneOrderListbyUserName(String userName, int pageCount);
	public int getOrderMessagePageNum();
	public int getTodayDoneOrderPageNum();
	public int getAllDoneOrderPageNum();
	public int getAnydayDoneOrderPageNum(int num);
	public int getDoneOrderListbyUserNamePageNum(String userName);
}