package com.h5.service;

import java.io.Serializable;
import java.util.List;

import com.h5.entity.User;

/**
 * Demo服务层
 * @author Paul Iverson
 *
 */
public interface DemoService {
	
	/**
	 * 获取所有用户列表
	 */
	public List<User> doGetUserList();
	
	public Serializable doAdd(User user);
}
