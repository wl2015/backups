package com.yc.service;
import com.yc.bean.Merchant;

public interface MerchantService {
 // public Merchant getMerchant(Merchant merchant) throws Exception;//商户登录
  public Merchant merchantLogin(String phone, String passwod) throws Exception;//商户登录
  public int getPhonenum(String phonenum) throws Exception;//验证该注册手机号是否已注册
  public boolean addmerchantRegist(Merchant merchant) throws Exception;//商户注册
  public boolean updateMerchantPassword(Merchant merchant) throws Exception;//商户修改密码
  public boolean updateMerchantIntro(int m_id, String m_intro)throws Exception;//商户修改简介
  public boolean updateMerchantLogo(int merchantId, String merchantLogo)throws Exception;//商户修改图标
}
