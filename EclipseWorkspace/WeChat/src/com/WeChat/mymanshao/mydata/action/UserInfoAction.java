package com.WeChat.mymanshao.mydata.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.WeChat.entity.User;
import com.WeChat.mymanshao.mydata.service.UserInfoService;
import com.WeChat.mymanshao.mydata.service.UserInfoServiceImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport{

	private User user;
	
	private User userinfo;
	
	private int userId;
	
	private int userinfoId;
	

	private UserInfoService userService = new UserInfoServiceImp();
	
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserinfoId() {
		return userinfoId;
	}

	public void setUserinfoId(int userinfoId) {
		this.userinfoId = userinfoId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}

	public String UserInfo(){
		userinfo = (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		UserInfoService users = new UserInfoServiceImp();
		List<User> userlist = users.DogetUserInfo(userinfo);
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("userinfolist", userlist);
		
		return "infosuccess";
	}
	
	
	//获取用户ID
	public String getUserid(){
		setUser(userService.getById(userId));
		return "success";	
	}
	
	//保存或修改
	public String saveOrUpdate(){
		userService.SaveorUpdate(getUser());		
		return "updatesuccess";
	}
	
	

	//显示用户密码
	public String getUseridPass(){
		setUser(userService.getByIdPass(userId));
		return "successpass";
		
	}
	
	//修改密码
	public String saveOrUpdatePass(){
		userService.SaveorUpdatePass(getUser());
		return "updatepass";
	
	}
	
	
	//显示用户电话
	public String getUseridPhone(){
		setUser(userService.getByIdPhone(userId));
		return "successphone";
	}

	//修改电话
	public String saveOrUpdatePhone(){
		userService.SaveorUpdatePhone(getUser());
		return "updatephone";
	}
}
