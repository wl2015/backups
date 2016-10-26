package com.WeChat.admin.Common.action;


import java.util.ArrayList;
import java.util.List;

import com.WeChat.admin.Common.entity.ChooseSendPeople;
import com.WeChat.admin.Common.entity.DoneOrder;
import com.WeChat.admin.Common.entity.OrderMessage;
import com.WeChat.admin.Common.entity.PageMessage;
import com.WeChat.admin.Common.service.CommonService;
import com.WeChat.admin.Common.service.CommonServiceImpl;

import com.WeChat.db.TimeOperate;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport{
	private int orderformId;
	private int peopleId;
	private List<OrderMessage> ordermessagelist;
	private List<ChooseSendPeople> choosesendpeoplelist;
	private List<DoneOrder> doneorderlist;
	private String querycondition;
	private int pageConut;
	private List<PageMessage> pagemessagelist;
	private int pageMax;
	private int deorderstate;

	public int getDeorderstate() {
		return deorderstate;
	}

	public void setDeorderstate(int deorderstate) {
		this.deorderstate = deorderstate;
	}

	public int getPageMax() {
		return pageMax;
	}

	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}

	public List<PageMessage> getPagemessagelist() {
		return pagemessagelist;
	}

	public void setPagemessagelist(List<PageMessage> pagemessagelist) {
		this.pagemessagelist = pagemessagelist;
	}

	public int getPageConut() {
		return pageConut;
	}

	public void setPageConut(int pageConut) {
		this.pageConut = pageConut;
	}

	public List<DoneOrder> getDoneorderlist() {
		return doneorderlist;
	}

	public String getQuerycondition() {
		return querycondition;
	}

	public void setQuerycondition(String querycondition) {
		this.querycondition = querycondition;
	}

	public void setDoneorderlist(List<DoneOrder> doneorderlist) {
		this.doneorderlist = doneorderlist;
	}

	public int getOrderformId() {
		return orderformId;
	}

	public void setOrderformId(int orderformId) {
		this.orderformId = orderformId;
	}

	public int getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(int peopleId) {
		this.peopleId = peopleId;
	}
	
	public List<ChooseSendPeople> getChoosesendpeoplelist() {
		return choosesendpeoplelist;
	}

	public void setChoosesendpeoplelist(List<ChooseSendPeople> choosesendpeoplelist) {
		this.choosesendpeoplelist = choosesendpeoplelist;
	}

	public List<OrderMessage> getOrdermessagelist() {
		return ordermessagelist;
	}
	public void setOrdermessagelist(List<OrderMessage> ordermessagelist) {
		this.ordermessagelist = ordermessagelist;
	}
	/**
	 * 获得已确认订单列表，进入打印订单和选择配送员界面
	 * */
	public String getEnsureOrderList(){
		CommonService commonservice = new CommonServiceImpl();
		pagemessagelist = new ArrayList<PageMessage>();
		int pageNum = commonservice.getOrderMessagePageNum();
		pageMax =pageNum;
		for(int i = 1;i<=pageNum; i++){
			PageMessage page = new PageMessage();
			page.setCount(i);
			String ss ="第"+i+"页";
			page.setCountmessage(ss);
			pagemessagelist.add(page);
		}
		if(getPageConut()==0){
			ordermessagelist = commonservice.getOrderMessageList(0);
			pageConut = 1;
		}else{
			ordermessagelist = commonservice.getOrderMessageList(getPageConut()-1);
			pageConut=getPageConut();
		}
		choosesendpeoplelist = commonservice.getSendPeopleList();
		return "getEnsureOrderListSuccess";
	}
	/**
	 * 选择配送员
	 * */
	public String chooseSendPeople(){
		CommonService commonservice = new CommonServiceImpl();
		commonservice.addPeopleId(getOrderformId(), getPeopleId());
		return "chooseSendPeopleSuccess";
	}
	/**
	 * 已完成订单列表
	 * */
	public String getDoneOrderList(){
		CommonService commonservice = new CommonServiceImpl();
		//当天的订单列表,deorderstate=0
		if(getDeorderstate()==0){	
			pagemessagelist = new ArrayList<PageMessage>();
			int pageNum = commonservice.getTodayDoneOrderPageNum();
			pageMax =pageNum;
			for(int i = 1;i<=pageNum; i++){
				PageMessage page = new PageMessage();
				page.setCount(i);
				String ss ="第"+i+"页";
				page.setCountmessage(ss);
				pagemessagelist.add(page);
			}
			if(getPageConut()==0){
				doneorderlist = commonservice.getTodayDoneOrderList(0);
				pageConut = 1;
			}else{
				doneorderlist = commonservice.getTodayDoneOrderList(getPageConut()-1);
				pageConut=getPageConut();
			}
			deorderstate=0;
		}
		//全部的订单列表,deorderstate=1
		else if(getDeorderstate()==1){
			pagemessagelist = new ArrayList<PageMessage>();
			int pageNum = commonservice.getAllDoneOrderPageNum();
			pageMax =pageNum;
			for(int i = 1;i<=pageNum; i++){
				PageMessage page = new PageMessage();
				page.setCount(i);
				String ss ="第"+i+"页";
				page.setCountmessage(ss);
				pagemessagelist.add(page);
			}
			if(getPageConut()==0){
				doneorderlist = commonservice.getAllDoneOrderlist(0);
				pageConut = 1;
			}else{
				doneorderlist = commonservice.getAllDoneOrderlist(getPageConut()-1);
				pageConut=getPageConut();
			}
			deorderstate=1;
		}
		//按日期查找订单列表,deorderstate=2
		else if(getDeorderstate()==2){
			TimeOperate t = new TimeOperate();
			int num = t.getTimeDifferenceToToday(getQuerycondition());
			pagemessagelist = new ArrayList<PageMessage>();
			int pageNum = commonservice.getAnydayDoneOrderPageNum(num);
			pageMax =pageNum;
			for(int i = 1;i<=pageNum; i++){
				PageMessage page = new PageMessage();
				page.setCount(i);
				String ss ="第"+i+"页";
				page.setCountmessage(ss);
				pagemessagelist.add(page);
			}
			if(getPageConut()==0){
				doneorderlist = commonservice.getAnydayDoneOrderList(num,0);
				pageConut = 1;
			}else{
				doneorderlist = commonservice.getAnydayDoneOrderList(num,getPageConut()-1);
				pageConut=getPageConut();
			}
			deorderstate = 2;
			querycondition = getQuerycondition();
			if(num==0){
				deorderstate = 0;
			}
		}
		//按用户名查找订单列表,deorderstate=3
		else if(getDeorderstate()==3){
			pagemessagelist = new ArrayList<PageMessage>();
			int pageNum = commonservice.getDoneOrderListbyUserNamePageNum(getQuerycondition());
			pageMax =pageNum;
			for(int i = 1;i<=pageNum; i++){
				PageMessage page = new PageMessage();
				page.setCount(i);
				String ss ="第"+i+"页";
				page.setCountmessage(ss);
				pagemessagelist.add(page);
			}
			if(getPageConut()==0){
				doneorderlist = commonservice.getDoneOrderListbyUserName(getQuerycondition(),0);
				pageConut = 1;
			}else{
				doneorderlist = commonservice.getDoneOrderListbyUserName(getQuerycondition(),getPageConut()-1);
				pageConut=getPageConut();
			}
			deorderstate = 3;
			querycondition = getQuerycondition();
		}
		
		return "getDoneOrderListSuccess";
	}

}
