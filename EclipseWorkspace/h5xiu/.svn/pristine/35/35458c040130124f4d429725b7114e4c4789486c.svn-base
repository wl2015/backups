package com.h5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 升级vip实体类
 * */
@Entity

@Table(name="h5_upgrade_record")
@DynamicInsert
@DynamicUpdate
public class Upgrade {
	@Id
	@GeneratedValue
	@Column(name="id")
	private String id; //id
	
	@Column(name="user_id")
	private String userId; //用户ID
	
	@Column(name="create_time")
	private Integer createTime;//升级时间
	
	@Column(name="create_ip")
	private String createIp;//升级Ip
	
	@Column(name="method")
	private int method;//升级方式：0表示管理员为用户升级，1表示用户自己升级
	
	public Upgrade(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}
	
}
