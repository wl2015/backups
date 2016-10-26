package com.WeChat.admin.adLogin.dao;

import com.WeChat.admin.backEntity.Admin;





public interface AdminDao {
	
	// 验证管理员登录	
	public Admin isLogined(Admin a);
}
