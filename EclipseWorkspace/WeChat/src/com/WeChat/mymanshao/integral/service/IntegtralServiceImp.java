package com.WeChat.mymanshao.integral.service;

import java.util.List;

import com.WeChat.entity.Integral;
import com.WeChat.entity.User;
import com.WeChat.mymanshao.integral.dao.IntegralDao;
import com.WeChat.mymanshao.integral.dao.IntegralDaoImp;

public class IntegtralServiceImp implements IntegralService{

	/*
	 * 积分信息
	 */
	public List<Integral> DogetIntegralInfo(User u){
		IntegralDao myintegral = new IntegralDaoImp();
		return myintegral.getIntegralInfo(u.getUser_id());
		
	}
}
