package com.WeChat.login.service;

import com.WeChat.entity.User;

public interface LoginService {
	/***
	 * 是否登录成功
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public User isLogined(User u);
}
