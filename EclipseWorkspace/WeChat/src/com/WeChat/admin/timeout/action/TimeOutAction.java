package com.WeChat.admin.timeout.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.WeChat.entity.OrderForm;
import com.WeChat.admin.timeout.dao.TimeOutDaoImp;
import com.WeChat.admin.timeout.service.TimeOutService;
import com.WeChat.admin.timeout.service.TimeOutServiceImp;

public class TimeOutAction extends ActionSupport{
	
	private TimeOutService outservice = new TimeOutServiceImp();

	private int pageSize=5;//（*自己设置）
	private int pageNum;
	private int totalpage;
	private int totalnum;
	
	private List<OrderForm> orderlist;
	private OrderForm orderinfo;
	private String orderformId;
	
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
	
	public List<OrderForm> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<OrderForm> orderlist) {
		this.orderlist = orderlist;
	}
	
	
	public OrderForm getOrderinfo() {
		return orderinfo;
	}
	public void setOrderinfo(OrderForm orderinfo) {
		this.orderinfo = orderinfo;
	}
	
	
	public String getOrderformId() {
		return orderformId;
	}
	public void setOrderformId(String orderformId) {
		this.orderformId = orderformId;
	}
	/*
	 * 根据时间查询已过期订单
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			pageNum=1;			
		}
		else{
			pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		}
		totalnum=new TimeOutDaoImp().getTotalnum();//（*该方法是获取数据总数自己写要到对应的dao，daoimpl，Service，serviceimpl里添加）
		totalpage=(totalnum-1)/pageSize +1;
		orderlist = outservice.doTimeOutForm(getPageNum());
		
		return "timeoutsuccess";
	}
	
	//根据订单ID查询订单详细信息
	public String getTimeOutInfoById(){
		setOrderinfo(outservice.getInfoById(orderformId));
		return "getinfosuccess";
	}
	
	//根据订单ID删除订单信息
	public String DeleteTimeOutById(){
		outservice.DodeleteByorderId(Integer.parseInt(this.orderformId));
		return "deletesuccess";
	}
}
