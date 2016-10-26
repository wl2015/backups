package com.h5.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.h5.dao.BaseDao;
import com.h5.dao.DemoDao;
import com.h5.entity.User;

/**
 * 
 * @author Paul Iverson
 *
 */
@Repository
public class DemoDaoImpl extends BaseDao<User> implements DemoDao {
	
	private static final Logger log = Logger.getLogger(DemoDaoImpl.class);

	/**
	 * 获取所有用户列表
	 */
	public List<User> getUserList() {
		return getAllList();
	}

	@Override
	protected Class<User> getEntity() {
		return User.class;
	}

	public Serializable add(User user) {
		/*super.add(user);
		log.debug("\n\n\n----插入成功！");
		User u = new User();
		u.setUserName("test");
		u.setPhoneNum("test");
		u.setPassword("test");*/
		return super.add(user);
	}

}
