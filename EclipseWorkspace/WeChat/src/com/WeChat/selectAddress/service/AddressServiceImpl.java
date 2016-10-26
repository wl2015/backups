package com.WeChat.selectAddress.service;

import java.util.List;

import com.WeChat.entity.FrontAddress;
import com.WeChat.entity.UserInfo;
import com.WeChat.selectAddress.dao.AddressDao;
import com.WeChat.selectAddress.dao.AddressDaoImpl;



/**
 * 业务逻辑层
 * @author Mical
 *
 */
public class AddressServiceImpl implements AddressService{

	private AddressDao addressDao;

	
	/**
	 * 
	 */
	public AddressServiceImpl(){
		setAddressDao(new AddressDaoImpl());
	}
	
	public void setAddressDao(AddressDao addressDao){
		this.addressDao = addressDao;
	}
	
	/**
	 * 查询所有地址
	 * @return
	 */


	public List<FrontAddress> getAllAddress() {
		
		return addressDao.getAllAddress();
	}

	
	
	/**
	 * 关键字查询地址
	 */
	
	public List<FrontAddress> getKeyAddress(String keyword) {
		
		return addressDao.getKeyAddress(keyword);
	}
	
	
	
	
	/**
	 * 根据ID查询地址
	 */
	public FrontAddress getById(int addressId) {
		
		return addressDao.getById(addressId);
	}

	

	
	/**
	 * 查询某地址信息
	 */
	public UserInfo getUserInfo(int userinfoId) {
		
		return addressDao.getUserInfo(userinfoId);
	}

	
	
	
	/**
	 * 得到用户所有地址信息
	 */
	public List<UserInfo> getAllUserInfo(int userId) {
		
		return addressDao.getAllUserInfo(userId);
	}

	
	/**
	 * 删除用户某个地址信息
	 */
	public int delete(int userinfo_id) {
		
		return addressDao.delete(userinfo_id);
	}

	
	/**
	 * 新增或修改用户地址信息
	 */
	public int saveOrUpdate(UserInfo user) {
		if (user.getUserinfo_id() == 0) {
			return addressDao.save(user);
		} else {
			return addressDao.update(user);
		}
	}


}
