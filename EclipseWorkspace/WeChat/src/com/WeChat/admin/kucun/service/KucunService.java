package com.WeChat.admin.kucun.service;

import java.sql.Date;
import java.util.List;

import com.WeChat.admin.backEntity.kucun;



public interface KucunService {
	
	/**
	 * 显示菜品库存列表
	 * @return
	 */
	
	public List<kucun> kucunList(Date dataTime);
	
	
	/**
	 * 根据库存ID修改上限
	 * @param kucun_id
	 * @return
	 */
	public int modifyLimit(int kucunId,int limit);
	
	/**
	 * 更新库存
	 * @param nowT
	 * @return
	 */
	int updateKucun(Date before,Date after);
}

