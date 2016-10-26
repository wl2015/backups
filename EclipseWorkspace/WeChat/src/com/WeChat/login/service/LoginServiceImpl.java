package com.WeChat.login.service;

import com.WeChat.entity.User;
import com.WeChat.login.dao.UserDao;
import com.WeChat.login.dao.UserDaoImpl;


public class LoginServiceImpl implements LoginService{
	private UserDao userdao;
	public void setuserDao(UserDao userdao) {
		this.userdao = userdao;
	}
	public LoginServiceImpl(){
		this.setuserDao(new UserDaoImpl());
	}
	//是否登录成功@Override
	public User isLogined(User u) {		
		
		return userdao.isLogined(u);
	}
}
