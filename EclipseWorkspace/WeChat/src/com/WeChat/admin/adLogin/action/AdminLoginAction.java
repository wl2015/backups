package com.WeChat.admin.adLogin.action;

import com.WeChat.admin.adLogin.service.AdminLoginService;
import com.WeChat.admin.adLogin.service.AdminLoginServiceImpl;
import com.WeChat.admin.backEntity.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminLoginAction extends ActionSupport{
	
	//private KucunServiceImpl kucunService = new KucunServiceImpl();
	
	private Admin admin;
	private String loginMsg;

	//adminService 
	private AdminLoginService loginService = new AdminLoginServiceImpl();
	
	
	
//	public KucunServiceImpl getKucunService() {
//		return kucunService;
//	}
//
//	public void setKucunService(KucunServiceImpl kucunService) {
//		this.kucunService = kucunService;
//	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}

	public AdminLoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(AdminLoginService loginService) {
		this.loginService = loginService;
	}



	public String isLogined(){
		
		System.out.println(admin.getAdmin_name()+admin.getPassword()+"...loginaction");
		
		admin = loginService.isLogined(admin);
		
		if(admin != null){
			
			
			//Map m = ActionContext.getContext().getSession();
			// m.put("loginUser", getUser());
			ActionContext.getContext().getSession().put("loginAdmin", getAdmin());
			
			loginMsg="登录成功！";
	
			return "loginsuccess";
		}
		
		loginMsg="账户或密码错误！";
		
		return "loginfailed";		
	}
	
}