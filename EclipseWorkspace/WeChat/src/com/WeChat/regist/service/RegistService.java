package com.WeChat.regist.service;


import com.WeChat.entity.User;

public interface RegistService {
	//是否注册成功,成功返回true，否则返回false
	public boolean isRegist(User u);
}
