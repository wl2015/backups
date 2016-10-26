package com.WeChat.admin.Frontaddress.action;

import java.util.ArrayList;
import java.util.List;

import com.WeChat.admin.Common.entity.PageMessage;
import com.WeChat.admin.Frontaddress.service.AddressService;
import com.WeChat.admin.Frontaddress.service.AddressServiceImpl;
import com.WeChat.entity.FrontAddress;
import com.opensymphony.xwork2.ActionSupport;

public class AddressAction extends ActionSupport{
	private int frontaddressId;
	private String frontaddress;
	private List<FrontAddress> frontaddresslist;
	private String keyword;
	private int state;
	private int pageConut;
	private int pageMax;
	private List<PageMessage> pagemessagelist;
	
	public List<PageMessage> getPagemessagelist() {
		return pagemessagelist;
	}
	public void setPagemessagelist(List<PageMessage> pagemessagelist) {
		this.pagemessagelist = pagemessagelist;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPageConut() {
		return pageConut;
	}
	public void setPageConut(int pageConut) {
		this.pageConut = pageConut;
	}
	public int getPageMax() {
		return pageMax;
	}
	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getFrontaddressId() {
		return frontaddressId;
	}
	public void setFrontaddressId(int frontaddressId) {
		this.frontaddressId = frontaddressId;
	}
	public String getFrontaddress() {
		return frontaddress;
	}
	public void setFrontaddress(String frontaddress) {
		this.frontaddress = frontaddress;
	}
	
	public List<FrontAddress> getFrontaddresslist() {
		return frontaddresslist;
	}
	public void setFrontaddresslist(List<FrontAddress> frontaddresslist) {
		this.frontaddresslist = frontaddresslist;
	}
	
	/**
	 * 显示全部或模糊指定地址
	 * */
	public String AddressShow(){
		AddressService addressService =new AddressServiceImpl();
		pagemessagelist = new ArrayList<PageMessage>();
		//显示全部
		if(getState()==0){
			int pageNum = addressService.getFrontAddressPageNum();
			pageMax =pageNum;
			for(int i = 1;i<=pageNum; i++){
				PageMessage page = new PageMessage();
				page.setCount(i);
				String ss ="第"+i+"页";
				page.setCountmessage(ss);
				pagemessagelist.add(page);
			}
			if(getPageConut()==0){
				frontaddresslist = addressService.getFrontAddresslist(0);
				pageConut = 1;
			}
			else{
				frontaddresslist = addressService.getFrontAddresslist(getPageConut()-1);
				pageConut = getPageConut();
			}
			state=0;
		}
		//显示模糊
		else if(getState()==1){
			int pageNum = addressService.getFrontAddressesByKeywordPageNum(getKeyword());
			pageMax =pageNum;
			for(int i = 1;i<=pageNum; i++){
				PageMessage page = new PageMessage();
				page.setCount(i);
				String ss ="第"+i+"页";
				page.setCountmessage(ss);
				pagemessagelist.add(page);
			}
			if(getPageConut()==0){
				frontaddresslist = addressService.getFrontAddressesByKeyword(getKeyword(),0);
				pageConut = 1;
			}
			else{
				frontaddresslist = addressService.getFrontAddressesByKeyword(getKeyword(),getPageConut()-1);
				pageConut = getPageConut();
			}
			state=1;
			keyword=getKeyword();
		}
		return "AddressShowSuccess";
	}
	/**
	 * 修改指定地址
	 * */
	public String UpdateFrontaddress(){
		AddressService addressService = new AddressServiceImpl();
		addressService.updateFrontAddress(getFrontaddressId(), getFrontaddress());
		return "UpdateFrontaddressSuccess";
	}
	/**
	 * 添加指定地址
	 * */
	public String AddFrontAddress(){
		AddressService addressService = new AddressServiceImpl();
		addressService.addFrontAddress(getFrontaddress());
		return "AddFrontAddressSuccess";
	}
	/**
	 * 删除指定地址
	 * */
	public String DeleteFrontAddress(){
		AddressService addressService = new AddressServiceImpl();
		addressService.deleteFrontAddressById(getFrontaddressId());
		return "DeleteFrontAddressSuccess";
	}
}

