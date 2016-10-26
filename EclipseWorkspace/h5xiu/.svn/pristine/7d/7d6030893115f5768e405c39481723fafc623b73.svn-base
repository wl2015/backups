package com.h5.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 充值
 * */
@Entity

@Table(name="h5_recharge_record")
@DynamicInsert
@DynamicUpdate
public class Recharge {
	@Id
	@GeneratedValue
	@Column(name="id")
	private String id; //id
	
	@Column(name="user_id")
	private String userId; //用户ID
	
	@Column(name="money")
	private BigDecimal money;//充值金额
	
	@Column(name="create_time")
	private Integer createTime;//充值时间
	
	@Column(name="create_ip")
	private String createIp;//充值Ip
	
	@Column(name="method")
	private int method;//充值方式：0表示管理员为用户充值，1表示用户自己充值
	
	public Recharge(){
		
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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
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
