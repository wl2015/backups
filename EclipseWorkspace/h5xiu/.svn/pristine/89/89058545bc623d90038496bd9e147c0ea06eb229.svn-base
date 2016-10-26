package com.h5.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h5.dao.DemoDao;
import com.h5.entity.User;
import com.h5.service.DemoService;

/**
 * Demo服务层实现类
 * @author Paul Iverson
 *
 */

@Transactional
@Service
public class DemoServiceImpl implements DemoService {
	
	@Inject
	public DemoDao demoDao;
	
	private static Logger log = Logger.getLogger(DemoServiceImpl.class);

	/**
	 * 获取所有用户列表
	 */
	public List<User> doGetUserList() {
		return demoDao.getUserList();
	}

	public Serializable doAdd(User user) {
		return demoDao.add(user);
	}

}
