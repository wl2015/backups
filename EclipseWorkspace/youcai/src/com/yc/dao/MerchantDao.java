package com.yc.dao;

import java.util.List;

import com.yc.bean.Merchant;




public interface MerchantDao {
  public Merchant merchantLogin(String phone, String passwod) throws Exception;//商户登录
  /*public Merchant login(Merchant merchant) throws Exception;//商户登录
 
  public List<Merchant> getMerchantList() throws Exception;//商户登录*/
  public Integer isMerchantREgisted(String phonenum) throws Exception;//验证该注册手机号是否已注册
  public int doRegist(Merchant merchant) throws Exception;//商户注册
  public int updateMerchantPassword(Merchant merchant) throws Exception;//商户修改密码
  public int updateMerchantIntro(int m_id, String m_intro)throws Exception;//商户修改简介
  public int updateMerchantLogo(int merchantId, String merchantLogo)throws Exception;//商户修改图标
  
}
