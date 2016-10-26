package com.h5.service;

import java.util.List;

import com.h5.entity.Recharge;
import com.h5.entity.Upgrade;
import com.h5.entity.User;

public interface UserService {
	/**
	 * 修改头像
	 * @param id：登录时封装的用户id, logoUrl：头像地址
	 * @return true:修改头像成功，flase：修改失败
	 * */
	public boolean changeLogo(String id,String logoUrl);
	/**
	 * 获取用户信息（根据用户的登录信息获取用户详细信息）
	 * @param u：登录时封装的用户对象
	 * @return
	 * */	
	public User getUserInfo(User u);
	
	/**
	 * 修改用户昵称
	 * @param id：用户id号，nicename：新的昵称名
	 * @return
	 * */
	public boolean changeNiceName(String Id,String nicename);
	
	/**
	 * 修改密码
	 * @param id：用户id号，newPass：新的密码
	 * @return
	 * */
	public int changePass(String Id, String newPass);
	
	/**
	 * 通过id获取用户
	 * @param id：用户id号
	 * @return
	 * */
	public User getUserById(String id);
	
	/**
	 * 验证手机号在数据库中是否已存在
	 * @param phone：手机号
	 * @return
	 * */
	//验证手机号是否已存在
	public int usefulPhone(String phone);
	
	/**
	 * 普通用户注册
	 * @param u：注册的用户对象
	 * @return
	 * */
	//注册一个用户
	public boolean doregist(User u);
	
	/**
	 * 普通用户登录
	 * @param phoneNum：手机号，pass：加密后的密码
	 * @return
	 * */
	//用户登录
	public User doLogin(String phoneNum, String pass);
	
	/**
	 * 根据手机号重置密码
	 * @param phoneNum：手机号，newPass：加密后的新密码
	 * @return
	 * */
	//根据手机号重置密码
	public boolean reSetPass(String phoneNum, String newpass );
}
