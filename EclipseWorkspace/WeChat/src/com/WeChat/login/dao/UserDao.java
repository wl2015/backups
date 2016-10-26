package com.WeChat.login.dao;

import com.WeChat.entity.User;

public interface UserDao {
	// * 是否登录成功	
	public User isLogined(User u);
}
