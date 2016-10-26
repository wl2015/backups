package com.WeChat.mymanshao.mydata.service;

import java.util.List;

import com.WeChat.entity.User;
import com.WeChat.mymanshao.mydata.dao.UserInfoDao;
import com.WeChat.mymanshao.mydata.dao.UserInfoDaoImp;

public class UserInfoServiceImp implements UserInfoService{

	private UserInfoDao userdao;
	
	public UserInfoServiceImp(){
		setUserDao(new UserInfoDaoImp());
	}


	private void setUserDao(UserInfoDao userdao) {
		// TODO Auto-generated method stub
		this.userdao = userdao;
	}


	/**
	 * 得到用户信息
	 */
	public List<User> DogetUserInfo(User u) {
		
		UserInfoDao userinfodao = new UserInfoDaoImp();
		return userinfodao.getUserInfo(u.getUser_id());
	}

	
	

	/**
	 * 
	 */
	public User getById(int userId) {
		return userdao.getById(userId);
		
	}

	/**
	 *保存或修改
	 */
	public int SaveorUpdate(User u) {
		if(u.getUser_id() == 0){
			return userdao.save(u);
		}
		else{
			return userdao.update(u);
		}
	}

	
	/**
	 * 得到密码
	 */
	public User getByIdPass(int userId) {
		// TODO Auto-generated method stub
		
		return userdao.getByIdPass(userId);
	}

	
	/**
	 * 修改或保存密码
	 */
	public int SaveorUpdatePass(User uu) {
		if(uu.getUser_id() == 0){
			return userdao.savepass(uu);
		}
		else{
			return userdao.updatepass(uu);
		}
	}

	
	/**
	 * 得到电话
	 */
	public User getByIdPhone(int userId) {
		// TODO Auto-generated method stub
		return userdao.getByIdPhone(userId);
	}

	/**
	 * 修改或保存电话
	 */
	public int SaveorUpdatePhone(User u) {
		// TODO Auto-generated method stub
		if(u.getUser_id() == 0){
			
			return userdao.savephone(u);
		}else{
			
			return userdao.updatephone(u);
		}
		
		
	}
	
	
}
