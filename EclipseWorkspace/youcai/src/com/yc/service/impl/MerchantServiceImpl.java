package com.yc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Merchant;
import com.yc.dao.MerchantDao;
import com.yc.service.MerchantService;


@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {
  @Autowired
  private MerchantDao merchantDao;
  
/*  @Autowired
  private MerchantStarDao merchantStarDao;*/
/*
 * 商户登录(non-Javadoc)
 * @see com.yc.service.MerchantService#login(com.yc.entity.Merchant)
 * @return Merchant
 * @parameter Merchant
 */
  /*public Merchant getMerchant(Merchant merchant) throws Exception{
    return merchantDao.login(merchant);
  }*/
  public Merchant merchantLogin(String phone, String passwod) throws Exception{
    return merchantDao.merchantLogin(phone, passwod);
  }

/*验证该注册手机号是否已注册
 * @parameter String要查询的电话号码
 * @return int 得到的数据条数
 */
  public int getPhonenum(String phonenum) throws Exception{
    return merchantDao.isMerchantREgisted(phonenum);
  }
  
  /*商户注册
   * @parameter Merchant 要插入的merchant商户对象
   * @return boolean 商户注册成功否，0：未插入，1：成功插入
   */
  public boolean addmerchantRegist(Merchant merchant) throws Exception{
    int merchantid =  merchantDao.doRegist(merchant);

    if(merchantid > 0){
      return true;
    }
    else{
      return false;
    }
  }

  /*商户修改密码
   * @parameter 商家对象，包含商家的新密码及phoneNumber
   * @return 返回数据库中被影响的数据条数
   */
  public boolean updateMerchantPassword(Merchant merchant) throws Exception {
    // TODO Auto-generated method stub
    int isModified = merchantDao.updateMerchantPassword(merchant);
    if(isModified > 0){
      return true;
    }
    else{
      return false;
    }
   
  }
  
  /*商户修改简介
   * @parameter m_id:商家id，m_intro:新的简介
   * @return 返回数据库中被影响的数据条数
   */
  public boolean updateMerchantIntro(int merchantId, String merchantIintro)throws Exception {
	    // TODO Auto-generated method stub
    int isModified = merchantDao.updateMerchantIntro(merchantId, merchantIintro);
    if(isModified > 0){
      return true;
    }
    else{
      return false;
    }	    
	}

  /*商户修改图标
   * @parameter merchantId:商家id，merchantLogo:新图标地址
   * @return 返回数据库中被影响的数据条数
   */
  @Override
  public boolean updateMerchantLogo(int merchantId, String merchantLogo)
      throws Exception {
    int isModified = merchantDao.updateMerchantLogo(merchantId, merchantLogo);
    if(isModified > 0){
      return true;
    }
    else{
      return false;
    }   
  }
  
}
