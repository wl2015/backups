package com.h5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="h5_product")
@DynamicInsert
@DynamicUpdate
public class Product {
	
	/**
	 * 主键id
	 * */
	@Id
	@GeneratedValue
	@Column(name="id")
	private String id;
	
	/**
	 * 创建者id
	 * */
	@Column(name="author_id")
	private String authorId;
	
	/**
	 * 音乐id(允许为null)
	 * */
	@Column(name="music_id")
	private String musicId;
	
	/**
	 * 作品标题
	 * */
	@Column(name="title")
	private String title;
	
	/**
	 * 作品描述
	 * */
	@Column(name="describes")
	private String describe;
	
	/**
	 * 封面地址
	 * */
	@Column(name="surface_addr")
	private String surfaceAddr;
	
	/**
	 * 创建时间
	 * */
	@Column(name="create_time")
	private int createTime;
	
	/**
	 * 创建者的ip
	 * */
	@Column(name="create_ip")
	private String createIp;
	
	/**
	 * 最后修改时的时间
	 * */
	@Column(name="last_revise_time")
	private int lastReviseTime;
	
	/**
	 * 最后修改时的ip
	 * */
	@Column(name="last_revise_ip")
	private String lastReviseIp;
	
	/**
	 * 作品状态
	 * */
	@Column(name="status")
	private int status;
	
	/**
	 * 永久二维码地址
	 * */
	@Column(name="qr_code_addr")
	private String qrCodeAddr;
	
	/**
	 * 页面内容
	 * */
	@Column(name="content")
	private String content;
	
	/**
	 * 版本号(此作品改动次数)
	 * */
	@Column(name="version")
	private int version;
	
	/**
	 * 音乐
	 * */
	@Transient
	private Music music;
	
	public Product() {
		
	}
	
	public Product(String id, String title, String describe, String surfaceAddr, int lastReviseTime, int createTime){
		this.id = id;
		this.title = title;
		this.describe = describe;
		this.surfaceAddr = surfaceAddr;
		this.lastReviseTime = lastReviseTime;
		this.createTime = createTime;
	}
	
	public Product(String id, String musicId, String content, String qrCodeAddr){
		this.id = id;
		this.musicId = musicId;
		this.content = content;
		this.qrCodeAddr = qrCodeAddr;
	}
	
	public Product(String id, String musicId, String content){
		this.id = id;
		this.musicId = musicId;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getMusicId() {
		return musicId;
	}

	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getSurfaceAddr() {
		return surfaceAddr;
	}

	public void setSurfaceAddr(String surfaceAddr) {
		this.surfaceAddr = surfaceAddr;
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

	public int getLastReviseTime() {
		return lastReviseTime;
	}

	public void setLastReviseTime(int lastReviseTime) {
		this.lastReviseTime = lastReviseTime;
	}

	public String getLastReviseIp() {
		return lastReviseIp;
	}

	public void setLastReviseIp(String lastReviseIp) {
		this.lastReviseIp = lastReviseIp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getQrCodeAddr() {
		return qrCodeAddr;
	}

	public void setQrCodeAddr(String qrCodeAddr) {
		this.qrCodeAddr = qrCodeAddr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
