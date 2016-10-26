package com.WeChat.regist.service;



import com.WeChat.entity.User;
import com.WeChat.regist.dao.UserDao;
import com.WeChat.regist.dao.UserDaoImpl;

public class RegistServiceImpl implements RegistService{
	private UserDao userdao;
	public void setuserDao(UserDao userdao) {
		this.userdao = userdao;
	}
	public RegistServiceImpl(){
		this.setuserDao(new UserDaoImpl());
	}
	//是否注册成功,成功返回true，否则返回false
	public boolean isRegist(User u){
		
		return userdao.isRegist(u);
	}

}
