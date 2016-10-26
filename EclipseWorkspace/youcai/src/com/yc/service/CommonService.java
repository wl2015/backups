package com.yc.service;

public interface CommonService {

  /**
   * 发送短信验证码,返回发送结果
   * */
  public String doSendPhoneCheckCode(String phoneNum, int code) throws Exception;
  
  /**
   * 发送自定义短信
   * @param phoneNum
   * @param RejectReason
   * @return
   * @throws Exception
   */
  public String doSendPhoneForRejectReason(String phoneNum, String RejectReason) throws Exception;
  
}
