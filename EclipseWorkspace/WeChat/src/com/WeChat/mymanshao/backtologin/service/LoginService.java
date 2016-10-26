package com.WeChat.mymanshao.backtologin.service;

import com.WeChat.entity.User;

public interface LoginService {

	
	public User isLogin(String user_name, String password);
}
