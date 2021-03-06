package com.h5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 广告实体类
 * @author cjy
 *
 */

@Entity
@Table(name="h5_advertise")
@DynamicInsert
@DynamicUpdate
public class Advertise {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int adverId;
	
	@Column(name="img_url")
	private String img_url;
	
	@Column(name="sequence")
	private int sequence;
	
	@Column(name="create_time")
	private int create_time;
	
	
	
	
	public Advertise() {
		
	}
	
	public Advertise(int id,String url, int sequence, int create_time) {
		this.adverId = id;
		this.img_url = url;
		this.sequence = sequence;
		this.create_time = create_time;
	}
	

	public int getAdverId() {
		return adverId;
	}

	public void setAdverId(int adverId) {
		this.adverId = adverId;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getCreate_time() {
		return create_time;
	}

	public void setCreate_time(int create_time) {
		this.create_time = create_time;
	}
	
	
	
	
	
	
	
}
