package com.h5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="h5_music")
public class Music {
	
	/**
	 * 主键id
	 * */
	@Id
	@GeneratedValue
	@Column(name="id")
	private String id;
	
	/**
	 * 音乐名字
	 * */
	@Column(name="name")
	private String name;
	
	/**
	 * 音乐地址
	 * */
	@Column(name="addr")
	private String musicAddr;
	
	/**
	 * 创建时间
	 * */
	@Column(name="create_time")
	private int createTime;
	
	/**
	 * 创建ip
	 * */
	@Column(name="create_ip")
	private String createIp;
	
	public Music() {
		
	}
	
	public Music(String id, String name, String musicAddr){
		this.id = id;
		this.name = name;
		this.musicAddr = musicAddr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMusicAddr() {
		return musicAddr;
	}

	public void setMusicAddr(String musicAddr) {
		this.musicAddr = musicAddr;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	
	
}
