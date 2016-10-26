package com.WeChat.mymanshao.backtologin.action;

import com.WeChat.entity.User;
import com.WeChat.mymanshao.backtologin.service.LoginService;
import com.WeChat.mymanshao.backtologin.service.LoginServiceImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction123 extends ActionSupport{

	private User user;
	
	private LoginService loginservice = new LoginServiceImp();
	
	private String errorMsg;
	
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	//登录
	public String Login(){
	
		return "success";
		
	}
	
	
	//判断用户名和密码
	public String login(){
		user = loginservice.isLogin(getUser().getUser_name(),getUser().getPassword());
		
		if(user != null){
			ActionContext.getContext().getSession().put("loginUser", getUser());
			return "first";
		}else{
			this.errorMsg="用户名或密码错误，请重新登录！"; 
			//System.out.println(errorMsg);
			return "failed";
		}
	}
}
