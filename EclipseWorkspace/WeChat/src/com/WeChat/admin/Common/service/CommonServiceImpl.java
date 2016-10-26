package com.WeChat.admin.Common.service;

import java.util.List;

import com.WeChat.admin.Common.dao.CommonDao;
import com.WeChat.admin.Common.dao.CommonDaoImpl;
import com.WeChat.admin.Common.entity.ChooseSendPeople;
import com.WeChat.admin.Common.entity.DoneOrder;
import com.WeChat.admin.Common.entity.OrderMessage;

public class CommonServiceImpl implements CommonService{
	private CommonDao commondao;
	public CommonServiceImpl(){
		setCommondao(new CommonDaoImpl());
	}
	public void setCommondao(CommonDao commondao) {
		this.commondao = commondao;
	}
	public List<OrderMessage> getOrderMessageList(int pageCount){
		return this.commondao.getOrderMessageList(pageCount);
	}
	public List<ChooseSendPeople> getSendPeopleList(){
		return this.commondao.getSendPeopleList();
	}
	public void addPeopleId(int orderformId, int PeopleId){
		this.commondao.addPeopleId(orderformId, PeopleId);
	}
	public List<DoneOrder> getTodayDoneOrderList(int pageCount){
		return this.commondao.getTodayDoneOrderList(pageCount);
	}
	public List<DoneOrder> getAllDoneOrderlist(int pageCount){
		return this.commondao.getAllDoneOrderlist(pageCount);
	}
	public List<DoneOrder> getAnydayDoneOrderList(int num, int pageCount){
		return this.commondao.getAnydayDoneOrderList(num, pageCount);
	}
	public List<DoneOrder> getDoneOrderListbyUserName(String userName, int pageCount){
		return this.commondao.getDoneOrderListbyUserName(userName, pageCount);
	}
	public int getOrderMessagePageNum(){
		return this.commondao.getOrderMessagePageNum();
	}
	public int getTodayDoneOrderPageNum(){
		return this.commondao.getTodayDoneOrderPageNum();
	}
	public int getAllDoneOrderPageNum(){
		return this.commondao.getAllDoneOrderPageNum();
	}
	public int getAnydayDoneOrderPageNum(int num){
		return this.commondao.getAnydayDoneOrderPageNum(num);
	}
	public int getDoneOrderListbyUserNamePageNum(String userName){
		return this.commondao.getDoneOrderListbyUserNamePageNum(userName);
	}
}
