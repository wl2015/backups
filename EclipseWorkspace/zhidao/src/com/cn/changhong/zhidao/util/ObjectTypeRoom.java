package com.cn.changhong.zhidao.util;

import java.util.ArrayList;

public class ObjectTypeRoom {
	private String name;
	private String address;
	private String type;
	private ArrayList<ObjectTypeRoomData> data;
	
	public ObjectTypeRoom(String name, String address, String type, ArrayList<ObjectTypeRoomData> data) {
		super();
		this.name = name;
		this.address = address;
		this.type = type;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<ObjectTypeRoomData> getData() {
		return data;
	}
	public void setData(ArrayList<ObjectTypeRoomData> data) {
		this.data = data;
	}

	

}
