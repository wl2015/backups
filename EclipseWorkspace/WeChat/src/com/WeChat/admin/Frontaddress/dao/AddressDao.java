package com.WeChat.admin.Frontaddress.dao;

import java.util.List;

import com.WeChat.entity.FrontAddress;

public interface AddressDao {
	public List<FrontAddress> getFrontAddresslist(int pageCount);
	public void updateFrontAddress(int frontaddressId, String frontaddress);
	public void addFrontAddress(String frontaddress);
	public void deleteFrontAddressById(int frontaddressId);
	public List<FrontAddress> getFrontAddressesByKeyword(String keyword,int pageCount);
	public int getFrontAddressPageNum();
	public int getFrontAddressesByKeywordPageNum(String keyword);
}
