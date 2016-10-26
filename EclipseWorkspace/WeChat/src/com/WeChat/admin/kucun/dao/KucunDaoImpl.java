package com.WeChat.admin.kucun.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.admin.backEntity.kucun;
import com.WeChat.db.BaseDao;


public class KucunDaoImpl extends BaseDao implements KucunDao{

	/**
	 * 根据日期查询库存情况
	 */
	public List<kucun> kucunList(Date dataTime) {
		
		ResultSet rs = super.query("select * from kucun where dataTime = ?",new Object[]{dataTime}); 
		List<kucun> list = new ArrayList<kucun>();
		 
		try {
			while(rs.next()){
				kucun kcInfo = new kucun();
				kcInfo.setKucun_id(rs.getInt(1));
				kcInfo.setDish_name(rs.getString(2));
				kcInfo.setLimitTop(rs.getInt(3));
				kcInfo.setOrderNum(rs.getInt(4));
				kcInfo.setDataTime(rs.getDate(5));
				
				list.add(kcInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
			
		}
		return list;
	}

	/**
	 * 根据库存ID修改上限
	 */
	public int modifyLimit(int kucunId,int limit) {
		int  rows = super.update("update kucun set limitTop = ? where kucun_id = ?"	,new Object[]{limit,kucunId});
				
		super.close();
				
		return rows;
	}

	/**
	 * 更新库存
	 */
	public int updateKucun(Date before, Date after) {
		
		int  rows = super.update("update kucun set limitTop = ?, orderNum = ?, dataTime = ? where dataTime = ?"	,new Object[]{30,0,after,before});
		
		super.close();
		return rows;
	}


}