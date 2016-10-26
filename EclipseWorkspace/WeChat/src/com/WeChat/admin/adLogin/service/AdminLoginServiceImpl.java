package com.WeChat.admin.adLogin.service;

import com.WeChat.admin.adLogin.dao.AdminDao;
import com.WeChat.admin.adLogin.dao.AdminDaoImpl;
import com.WeChat.admin.backEntity.Admin;



public class AdminLoginServiceImpl implements AdminLoginService{
	
	private AdminDao admindao;
	
	
	public AdminDao getAdmindao() {
		return admindao;
	}
	public void setAdmindao(AdminDao admindao) {
		this.admindao = admindao;
	}
	
	public AdminLoginServiceImpl(){
		this.setAdmindao(new AdminDaoImpl());
	}
	
	
	
	//@Override
	
	public Admin isLogined(Admin a) {
		
		return admindao.isLogined(a);
	}
}
