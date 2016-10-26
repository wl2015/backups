package com.h5.service;



import java.util.List;
import com.h5.entity.Admin;
import com.h5.entity.Recharge;
import com.h5.entity.Upgrade;
import com.h5.entity.Advertise;
import com.h5.entity.User;




public interface AdminService {
	//验证管理员登录
	public Admin doAdLogin(String phoneNum, String pass);
	
	//获取所有用户信息列表
	public List<User> doGetUserList();
		
	//根据用户ID升级用户为VIP
	public void doUpGradeUserIntoVipByUserId(String userId);
		
	//根据用户ID进行充值
	public void doChargeForUserByUserId(String userId, String denomination);
		
	//记录充值升级VIP信息(from为0表示管理员为用户升级，为1表示用户自己升级)
	public void doRecordUpGradeVipInfo(Upgrade upgrade);
		
	//记录充值信息(from为0表示管理员为用户充值，为1表示用户自己充值)
	public void doRecordChargeInfo(Recharge recharge);
	
	//显示所有上传的banner图片
	public List<Advertise> showPic();
	
	//上传banner图片,路径存入数据库
	public boolean upAdvertise(Advertise banner);
	
}
