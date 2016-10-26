package com.WeChat.selectAddress.action;

import java.util.List;

import com.WeChat.entity.FrontAddress;
import com.WeChat.entity.User;
import com.WeChat.entity.UserInfo;
import com.WeChat.selectAddress.service.AddressServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class addressAction extends ActionSupport {
	
	private AddressServiceImpl addService= new AddressServiceImpl();
	private List<FrontAddress> addressList;
	private FrontAddress fronAddress;
	
	private String keyword;
	
	private UserInfo userInfo;
	
	private User user;
	
	private List<UserInfo> infoList;
	
	
	
	
	
	public List<UserInfo> getInfoList() {
		return infoList;
	}


	public void setInfoList(List<UserInfo> infoList) {
		this.infoList = infoList;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<FrontAddress> getAddressList() {
		return addressList;
	}


	public void setAddressList(List<FrontAddress> addressList) {
		this.addressList = addressList;
	}


	public FrontAddress getFronAddress() {
		return fronAddress;
	}


	public void setFronAddress(FrontAddress fronAddress) {
		this.fronAddress = fronAddress;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public UserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	

	public String execute() throws Exception {
		
		//获取loginAction传过来的session值
		user = (User)ActionContext.getContext().getSession().get("loginUser");
		System.out.print(user.getUser_id());
		infoList = addService.getAllUserInfo(getUser().getUser_id());
		
		return SUCCESS;
	}
	
	/**
	 * 查询所有地址
	 * @return
	 */
	public String searchAll(){
		
		addressList = addService.getAllAddress();
		
		return "searchAll";
	}
	
	/**
	 * 关键字查询地址
	 * @return
	 */
	public String searchKey(){
		
		System.out.println(getKeyword());
		
		addressList = addService.getKeyAddress(getKeyword());
		
		return "searchKey";
	}
	
	/**
	 * 根据ID查询地址
	 * @return
	 */
	public String getAddress(){
		
		
		fronAddress = addService.getById(getFronAddress().getFrontaddress_id());
		
		System.out.println(getFronAddress().getFrontaddress_id());
		
		return "newAddress";
	}
	
	/**
	 * 新增和修改用户某个地址信息
	 * @return
	 */
	public String saveOrUpdate(){
		
		System.out.println(getUserInfo().getUser_id());
		addService.saveOrUpdate(getUserInfo());
		
		return "update";
	}
	
	
	
	/**
	 * 查询用户地址信息
	 * @return
	 */
	public String getByUserInfo(){
		
		//System.out.println(getUserInfo().getUserinfo_id());
		System.out.println(userInfo.getUserinfo_id());
		System.out.println("hellojojoi");
		
		System.out.println(getUserInfo().getUserinfo_id());
		
		userInfo =  addService.getUserInfo(userInfo.getUserinfo_id());
		
		ActionContext.getContext().getSession().put("UserInfo", getUserInfo());
		
		return "userinfo";
	}
	

	/**
	 * 根据用户ID查询某用户的全部地址信息
	 */
	public String getAllUserInfo(){
		
		addService.getAllUserInfo(user.getUser_id());
		
		return "allUserInfo";
	}
	
	/**
	 * 删除用户某个地址信息
	 * @return
	 */
	public String delete(){
		
		addService.delete(userInfo.getUserinfo_id());
		
		return "delete";
	}
	
	
}