package com.WeChat.login.action;

import com.WeChat.entity.User;
import com.WeChat.entity.UserInfo;
import com.WeChat.login.service.LoginService;
import com.WeChat.login.service.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	
	//复合对象
	private User user;
	private String loginMsg;
	
	private UserInfo userInfo;

	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userInfo = userinfo;
	}

	public String getLoginMsg() {
		return loginMsg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//userService 
	private LoginService loginService = new LoginServiceImpl();
	
	public String isLogined(){
		System.out.println(user.getUser_name()+user.getPassword()+"...loginaction");
		user=loginService.isLogined(user);
		
		if(user != null){
			
			//把登录的当前用户放到ActionContext中（Map类型），就是把loginUser值放入session。得到完整写法为：
			//Map m = ActionContext.getContext().getSession();
			// m.put("loginUser", getUser());
			ActionContext.getContext().getSession().put("loginUser", getUser());
			
			return "success";
		}
		
		loginMsg="用户名或密码错误";
		return "failed";		
	}
	
}