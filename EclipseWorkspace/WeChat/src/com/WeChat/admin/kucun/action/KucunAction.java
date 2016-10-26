package com.WeChat.admin.kucun.action;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;


import com.WeChat.admin.backEntity.kucun;
import com.WeChat.admin.kucun.service.KucunServiceImpl;
import com.WeChat.entity.Dish;
import com.opensymphony.xwork2.ActionSupport;

public class KucunAction extends ActionSupport {
	
	private KucunServiceImpl kcService = new KucunServiceImpl();
	
	private List<kucun> kcList;
	private Dish dish;
	private kucun kc;
	private Date nowT;
	
	public kucun getKc() {
		return kc;
	}
	public void setKc(kucun kc) {
		this.kc = kc;
	}
	public KucunServiceImpl getKcService() {
		return kcService;
	}
	public void setKcService(KucunServiceImpl kcService) {
		this.kcService = kcService;
	}
	
	
	public Date getNowT() {
		return nowT;
	}
	public void setNowT(Date nowT) {
		this.nowT = nowT;
	}
	@JSON(serialize=false)
	public List<kucun> getKcList() {
		return kcList;
	}
	public void setKcList(List<kucun> kcList) {
		this.kcList = kcList;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	
	
	
	/**
	 * 锟斤拷锟斤拷锟节诧拷询锟斤拷锟较匡拷娴�
	 */
	public String execute() throws Exception{
		
		java.util.Date now = new java.util.Date();
		int year = now.getYear();
		int month = now.getMonth();
		int day = now.getDay()+18;//鑾峰彇涓嶅埌绯荤粺鍑嗙‘鏃堕棿锛屽厛鍔犺捣
		Date before = new Date(year, month, day);
		
		Date after = new Date(year, month, day+3);
		
		
		System.out.println(before);
		System.out.println(after);
		
		kcService.updateKucun(before, after);
		
		return SUCCESS;
	}
	
	
	public String search() throws Exception{
		System.out.println(kc.getDataTime());
		kcList = kcService.kucunList(kc.getDataTime());
		
		return "searchSuccess";
	}
	
	/**
	 * 淇敼涓婇檺
	 * @return
	 */
	public String modify(){
		
		kcService.modifyLimit(kc.getKucun_id(), kc.getLimitTop());
		
		return "modifyLimit";
	}
}