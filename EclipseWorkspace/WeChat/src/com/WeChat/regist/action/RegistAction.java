package com.WeChat.regist.action;


import com.WeChat.entity.User;
import com.WeChat.regist.service.RegistService;
import com.WeChat.regist.service.RegistServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class RegistAction extends ActionSupport{
	//复合对象
	private User user;
	private String regisMsg;
	
	public String getRegisMsg() {
		return regisMsg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//userService 
	private RegistService registService = new RegistServiceImpl();
	
	public String isRegist(){
		System.out.println(user.getUser_name()+user.getPassword()+"....................................registaction");
		boolean result= registService.isRegist(user);
		if(result){
			regisMsg = "注册成功";
			return "registSuccess";
			
		}
		regisMsg = "注册失败，请重新注册";
		return "registfailed";		
	}
	
}
