package com.WeChat.mymanshao.backtologin.service;

import com.WeChat.entity.User;
import com.WeChat.mymanshao.backtologin.dao.LoginDao;
import com.WeChat.mymanshao.backtologin.dao.LoginDaoImp;

public class LoginServiceImp implements LoginService{

	
	private LoginDao logindao;
	

	public void setLogindao(LoginDao logindao) {
		this.logindao = logindao;
	}

	
	public LoginServiceImp(){
		setLogindao(new LoginDaoImp());
	}

	//得到用户名和密码
	public User isLogin(String user_name, String password) {
		// TODO Auto-generated method stub
		
		User u = new User();
		u.setUser_name(user_name);
		u.setPassword(password);
		return logindao.isLogin(u);
	}

}
