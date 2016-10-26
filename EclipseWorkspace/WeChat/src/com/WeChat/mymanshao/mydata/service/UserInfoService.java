package com.WeChat.mymanshao.mydata.service;

import java.util.List;

import com.WeChat.entity.User;

public interface UserInfoService {

	//显示用户信息
	List<User> DogetUserInfo(User u);
	
	
	public User getById(int userId);
		
	//保存或修改
	int SaveorUpdate(User u);
	
	//查找密码
	public User getByIdPass(int userId);
	
	//保存或修改密码
	int SaveorUpdatePass(User u);
	
	//得到电话
	public User getByIdPhone(int userId);
	
	//保存或修改
	int SaveorUpdatePhone(User u);

}
