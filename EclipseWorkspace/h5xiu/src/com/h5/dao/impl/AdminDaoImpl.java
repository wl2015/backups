package com.h5.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.h5.dao.AdminDao;
import com.h5.dao.BaseDao;

import com.h5.entity.Admin;
import com.h5.entity.Recharge;
import com.h5.entity.Upgrade;
import com.h5.entity.User;
import com.h5.entity.Advertise;


@Repository
public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {
	
	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

	private static final int INSERT_OR_UPDATE_SUCCESS = 1;
	
	//获取对应的实体类
	protected Class<Admin> getEntity() {
		
		return Admin.class;
	}
	
	
	//验证管理员登录
	
	public Admin doAdLogin(String phoneNum, String pass){
		String hql="from Admin where phone=? and password=?";
		Query query=super.createQuery(hql, new Object[]{phoneNum,pass});
		Admin a=(Admin)query.uniqueResult();
		return a;		
	}
	
	//上传图片，把图片路径存入数据库
	public boolean upAdvertise(Advertise banner) {
		
		Serializable b=super.add(banner);		
		if(b!=null){
			return true;
		}
		return false;
	}

	//获取所有用户信息列表
	public List<User> getUserList() {
		String hql="SELECT new User(u.userId, u.userName, u.phoneNum, u.registerTime, u.registerIp, u.lastLoginTime, u.lastLoginIp, u.userHeadImg, u.userStatus, u.accountMoney) FROM User u ORDER BY u.registerTime desc";
		Query query=super.createQuery(hql);

		List<User> userList = query.list();
		return userList;
	}
			 
	//根据用户ID升级用户为VIP
	public void upGradeUserIntoVipByUserId(String userId){
		String hql="UPDATE User u SET u.userStatus=2 WHERE u.userId=?";
		Query query = super.createQuery(hql);
	
		query.setInteger(0, Integer.parseInt(userId));
		query.executeUpdate();
	}

	//根据用户ID进行充值
	public void chargeForUserByUserId(String userId, String denomination) {
		String hql="UPDATE User u SET u.accountMoney=u.accountMoney+? WHERE u.userId=?";
		Query query = super.createQuery(hql);
				
		query.setBigDecimal(0, BigDecimal.valueOf(Double.parseDouble(denomination)));
		query.setInteger(1, Integer.parseInt(userId));
		query.executeUpdate();
	}
			
	//记录充值升级VIP信息(from为0表示管理员为用户升级，为1表示用户自己升级)
	public void recordUpGradeVipInfo(Upgrade upgrade){
		try {
			add(upgrade);
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
			
	//记录充值信息(from为0表示管理员为用户充值，为1表示用户自己充值)
	public void recordChargeInfo(Recharge recharge){
		try {
			add(recharge);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//显示所有上传的banner图片
	public List<Advertise> showPic() {
		String hql="SELECT new Advertise(a.id, a.img_url, a.sequence, a.create_time) FROM Advertise a ORDER BY a.create_time desc";
		Query query=super.createQuery(hql);

		List<Advertise> adverList = query.list();
		return adverList;
	}
	
}
