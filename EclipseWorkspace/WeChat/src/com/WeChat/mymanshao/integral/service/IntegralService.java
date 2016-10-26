package com.WeChat.mymanshao.integral.service;

import java.util.List;

import com.WeChat.entity.Integral;
import com.WeChat.entity.User;

public interface IntegralService {

	List<Integral> DogetIntegralInfo(User loginuser);
	
}
