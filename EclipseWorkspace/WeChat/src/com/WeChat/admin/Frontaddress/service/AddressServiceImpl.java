package com.WeChat.admin.Frontaddress.service;

import java.util.List;

import com.WeChat.admin.Frontaddress.dao.AddressDao;
import com.WeChat.admin.Frontaddress.dao.AddressDaoImpl;
import com.WeChat.entity.FrontAddress;

public class AddressServiceImpl implements AddressService{
	private AddressDao addressDao;
	public AddressServiceImpl() {
		setAddressDao(new AddressDaoImpl());
	}
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	public List<FrontAddress> getFrontAddresslist(int pageCount){
		return this.addressDao.getFrontAddresslist(pageCount);
	}
	public void updateFrontAddress(int frontaddressId, String frontaddress){
		this.addressDao.updateFrontAddress(frontaddressId, frontaddress);
	}
	public void addFrontAddress(String frontaddress){
		this.addressDao.addFrontAddress(frontaddress);
	}
	public void deleteFrontAddressById(int frontaddressId){
		this.addressDao.deleteFrontAddressById(frontaddressId);
	}
	public List<FrontAddress> getFrontAddressesByKeyword(String keyword,int pageCount){
		return this.addressDao.getFrontAddressesByKeyword(keyword,pageCount);
	}
	public int getFrontAddressPageNum(){
		return this.addressDao.getFrontAddressPageNum();
	}
	public int getFrontAddressesByKeywordPageNum(String keyword){
		return this.addressDao.getFrontAddressesByKeywordPageNum(keyword);
	}
}
