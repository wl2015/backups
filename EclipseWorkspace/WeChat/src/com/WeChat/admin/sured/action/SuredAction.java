package com.WeChat.admin.sured.action;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.WeChat.entity.OrderForm;
import com.WeChat.admin.sured.dao.SuredDaoImp;
import com.WeChat.admin.sured.service.SuredService;
import com.WeChat.admin.sured.service.SuredServiceImp;

public class SuredAction extends ActionSupport{

	private SuredService suredservice = new SuredServiceImp();
	private List<OrderForm> orderlist;
	private String orderformId;
	private OrderForm orderinfo;
	
	private HashMap<Integer, String> pageList = new HashMap<Integer, String>();
	
	public HashMap<Integer, String> getPageList() {
		return pageList;
	}

	private int pageSize=5;//（*自己设置）
	private int pageNum;
	private int totalpage;
	private int totalnum;
	
	public int getPageSize() {
		return pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public List<OrderForm> getOrderform() {
		return orderlist;
	}
	
	public String getOrderformId() {
		return orderformId;
	}

	public void setOrderformId(String orderformId) {
		this.orderformId = orderformId;
	}

	public OrderForm getOrderinfo() {
		return orderinfo;
	}

	public void setOrderinfo(OrderForm orderinfo) {
		this.orderinfo = orderinfo;
	}

	//显示已确认的订单
	public String getTakedOrderForm(){
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			pageNum=1;			
		}
		else{
			pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		
		}
		totalnum=new SuredDaoImp().getTotalnum();//（*该方法是获取数据总数自己写要到对应的dao，daoimpl，Service，serviceimpl里天加）
		totalpage=(totalnum-1)/pageSize +1;
		orderlist = suredservice.OrderFormTaked(getPageNum());

		for(int i=1; i<=totalpage; i++){
			pageList.put(i, "第"+i+"页");
		}
		
		return "takedsuccess";
	}
	
	//根据ID查询并显示已确认订单的详细信息
	public String getSuredInfoById(){
		setOrderinfo(suredservice.getInfoById(orderformId));
		return "suredinfo";
	}
}
