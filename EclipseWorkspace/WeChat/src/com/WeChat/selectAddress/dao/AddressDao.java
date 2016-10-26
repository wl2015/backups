package com.WeChat.selectAddress.dao;

import java.util.List;
import com.WeChat.entity.FrontAddress;
import com.WeChat.entity.UserInfo;


public interface AddressDao{

	
	/**
	 * 查询所有地址
	 * @return
	 */
	
	public List<FrontAddress> getAllAddress();
	
	/**
	 * 关键字查询地址
	 * @param keyword
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
	 * 添加用户地址信息
	 * @param e
	 */
	public int save(UserInfo user);
	
	/**
	 * 查询用户地址信息
	 * @return
	 */
	public UserInfo getUserInfo(int userinfoId);
	
	/**
	 * 查询某用户所有地址
	 * @return
	 */
	public List<UserInfo> getAllUserInfo(int userId);
	
	/**
	 * 删除用户某个地址信息
	 * @param userInfo_id
	 * @return
	 */
	public int delete(int userInfo_id);
	
	/**
	 * 修改用户某个地址信息
	 * @param userInfoId
	 * @return
	 */
	public int update(UserInfo userinfo);
}