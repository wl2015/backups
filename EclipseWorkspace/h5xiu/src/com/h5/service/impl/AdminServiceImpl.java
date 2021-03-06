package com.h5.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h5.dao.AdminDao;
import com.h5.entity.Admin;
import com.h5.entity.Recharge;
import com.h5.entity.Upgrade;
import com.h5.entity.Advertise;
import com.h5.entity.User;
import com.h5.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDao admindao;

	public Admin doAdLogin(String phoneNum, String pass) {
		
		return admindao.doAdLogin(phoneNum, pass);
	}
	
	//获取所有用户信息列表
	public List<User> doGetUserList() {
		return admindao.getUserList();
	}
	
	//根据用户ID升级用户为VIP
	public void doUpGradeUserIntoVipByUserId(String userId){
		admindao.upGradeUserIntoVipByUserId(userId);
	}

	//根据用户ID进行充值
	public void doChargeForUserByUserId(String userId, String denomination) {
		admindao.chargeForUserByUserId(userId, denomination);
	}
	//记录充值升级VIP信息(from为0表示管理员为用户升级，为1表示用户自己升级)
	public void doRecordUpGradeVipInfo(Upgrade upgrade){
		admindao.recordUpGradeVipInfo(upgrade);
	}
		
	//记录充值信息(from为0表示管理员为用户充值，为1表示用户自己充值)
	public void doRecordChargeInfo(Recharge recharge){
		admindao.recordChargeInfo(recharge);
	}

	//上传banner图片,路径存入数据库
	public boolean upAdvertise(Advertise banner) {
		
		return admindao.upAdvertise(banner);
		
	}
	
	//显示所有上传的banner图片
	public List<Advertise> showPic() {

		return admindao.showPic();
	}
}	
