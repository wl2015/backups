package com.WeChat.mymanshao.integral.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.WeChat.entity.Integral;
import com.WeChat.entity.User;
import com.WeChat.mymanshao.integral.service.IntegralService;
import com.WeChat.mymanshao.integral.service.IntegtralServiceImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IntegralAction extends ActionSupport{

	private Integral integ;
	private User loginuser;
	public Integral getInteg() {
		return integ;
	}
	public void setInteg(Integral integ) {
		this.integ = integ;
	}
	public User getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(User loginuser) {
		this.loginuser = loginuser;
	}
	
	//查找用户积分信息
	public String MyIntegral(){
		loginuser = (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		IntegralService myintegral = new IntegtralServiceImp();
		List<Integral> myinteg = myintegral.DogetIntegralInfo(loginuser);
		Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("myintegral", myinteg);
		
		return "integralsuccess";
		
	}
}
