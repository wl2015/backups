package com.h5.basic.exceptions;

/**
 * 登录超时的异常
 * */
public class SessionTimeoutException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param happenAtWhere 在什么地方发生的异常, 比如: 初次登录时(URL如果方便得到就可以写上), 注册成功跳转时(URL)
	 * */
	public SessionTimeoutException(String happenAtWhere) {
		
		super(new String("Session的相关错误[session超时], 该错误发生在: [ " + happenAtWhere +" ]") );
	}
}
