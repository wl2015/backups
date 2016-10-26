package com.WeChat.selectAddress.service;

import java.util.List;

import com.WeChat.entity.FrontAddress;
import com.WeChat.entity.UserInfo;


public interface AddressService {

	
	/**
	 * 查询所有地址
	 * @return
	 */
	public List<FrontAddress> getAllAddress();	
	
	/**
	 * 关键字查询地址
	 * @return
	 */
	public List<FrontAddress> getKeyAddress(String keyword);
	
	/**
	 * 根据ID查询地址
	 * @param addressId
	 * @return
	 */
	public FrontAddress getById(int addressId);
	
	/**
	 * 新增或修改用户地址信息
	 * @param user
	 * @return
	 */
	public int saveOrUpdate(UserInfo user);
	
	/**
	 * 查询用户地址信息
	 * @return
	 */
	public UserInfo getUserInfo(int userinfoId);
	
	/**
	 * 查询某用户全部地址信息
	 * @return
	 */
	public List<UserInfo> getAllUserInfo(int userId);
	
	/**
	 * 删除用户的某个地址信息
	 * @param userinfo_id
	 * @return
	 */
	public int delete(int userinfo_id);
	
	
}