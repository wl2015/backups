package com.WeChat.mymanshao.mylist.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.WeChat.entity.OrderForm;
import com.WeChat.entity.User;
import com.WeChat.mymanshao.mylist.service.MyListService;
import com.WeChat.mymanshao.mylist.service.MyListServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MyListAction extends ActionSupport{

	private User loginuser;
	
	public User getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(User loginuser) {
		this.loginuser = loginuser;
	}

	
	public String MyList(){
		return "loginsuccess";
	}
	
	
	//查询正在处理的用户订单
	public String MyListDisplay(){
		loginuser =(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		MyListService list = new MyListServiceImpl();
		List<OrderForm> oderlist = list.doGetOderForm(loginuser);
		Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("orderform", oderlist);
		return "success";
	}
	
	//查询历史订单
	public String isNotdealList(){
		loginuser =(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		MyListService list = new MyListServiceImpl();
		List<OrderForm> oderlist = list.doGetNotOrderForm(loginuser);
		Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("isnot", oderlist);
		return "isnotdeal";
	}
	
}
