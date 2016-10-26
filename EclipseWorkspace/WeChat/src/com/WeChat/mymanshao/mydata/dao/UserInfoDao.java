package com.WeChat.mymanshao.mydata.dao;

import java.util.List;

import com.WeChat.entity.User;

public interface UserInfoDao {
	
	//显示用户信息
	public List<User> getUserInfo(int userId);
	
	
	//
	public User getById(int userId);
	
	
	//修改
	public int update(User u);

	//保存
	public int save(User u);
	
	

	//查找密码
	public User getByIdPass(int user_id);
	
	//保存修改后的密码
	public int savepass(User u);

	//修改密码
	public int updatepass(User u);
	
	
	//得到电话
	public User getByIdPhone(int user_id);
	
	//保存电话
	public int savephone(User u);
	
	//修改电话
	public int updatephone(User u);
}
