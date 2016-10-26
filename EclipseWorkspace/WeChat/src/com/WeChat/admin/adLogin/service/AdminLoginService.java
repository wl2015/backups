package com.WeChat.admin.adLogin.service;


import com.WeChat.admin.backEntity.Admin;

public interface AdminLoginService {
	/***
	 * 判断管理员登录
	 * @return
	 */
	public Admin isLogined(Admin a);
}
