package com.cn.changhong.zhidao.util;

public class ObjectTypeRoomData {
	private String[] times;
	private String userName;
	public ObjectTypeRoomData(String[] times, String userName) {
		super();
		this.times = times;
		this.userName = userName;
	}
	public String[] getTimes() {
		return times;
	}
	public void setTimes(String[] times) {
		this.times = times;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
