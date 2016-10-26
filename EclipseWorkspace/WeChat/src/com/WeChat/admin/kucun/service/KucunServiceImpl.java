package com.WeChat.admin.kucun.service;

import java.sql.Date;
import java.util.List;

import com.WeChat.admin.backEntity.kucun;
import com.WeChat.admin.kucun.dao.KucunDao;
import com.WeChat.admin.kucun.dao.KucunDaoImpl;


public class KucunServiceImpl implements KucunService{
	
	private KucunDao kcDao;
	
	
	
	public void setKcDao(KucunDao kcDao) {
		this.kcDao = kcDao;
	}

	
	public KucunServiceImpl(){
		this.setKcDao(new KucunDaoImpl());
	}

	/**
	 * 根据日期查询库存情况
	 */
	public List<kucun> kucunList(Date dataTime) {
		
		return kcDao.kucunList(dataTime);
	}


	/**
	 * 根据库存ID修改上限
	 */
	public int modifyLimit(int kucunId,int limit) {
		
		return kcDao.modifyLimit(kucunId,limit);
	}


	public int updateKucun(Date before, Date after){
		
		return kcDao.updateKucun(before, after);
	}

	

}