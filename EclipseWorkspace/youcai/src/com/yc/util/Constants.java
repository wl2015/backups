package com.yc.util;
/**
 * 系统常量
 * @author android_djf
 *
 */
public class Constants {

  //RSA需要的公钥匙
  /* 密钥内容 base64 code */
  public static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfRTdcPIH10gT9f31rQuIInLwe"
      + "\r" + "7fl2dtEJ93gTmjE9c2H+kLVENWgECiJVQ5sonQNfwToMKdO0b3Olf4pgBKeLThra" + "\r"
      + "z/L3nYJYlbqjHC3jTjUnZc0luumpXGsox62+PuSGBlfb8zJO6hix4GV/vhyQVCpG" + "\r"
      + "9aYqgE7zyTRZYX9byQIDAQAB" + "\r";
  
  //RSA需要的私钥
  public static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ9FN1w8gfXSBP1/"
      + "\r" + "fWtC4gicvB7t+XZ20Qn3eBOaMT1zYf6QtUQ1aAQKIlVDmyidA1/BOgwp07Rvc6V/" + "\r"
      + "imAEp4tOGtrP8vedgliVuqMcLeNONSdlzSW66alcayjHrb4+5IYGV9vzMk7qGLHg" + "\r"
      + "ZX++HJBUKkb1piqATvPJNFlhf1vJAgMBAAECgYA736xhG0oL3EkN9yhx8zG/5RP/" + "\r"
      + "WJzoQOByq7pTPCr4m/Ch30qVerJAmoKvpPumN+h1zdEBk5PHiAJkm96sG/PTndEf" + "\r"
      + "kZrAJ2hwSBqptcABYk6ED70gRTQ1S53tyQXIOSjRBcugY/21qeswS3nMyq3xDEPK" + "\r"
      + "XpdyKPeaTyuK86AEkQJBAM1M7p1lfzEKjNw17SDMLnca/8pBcA0EEcyvtaQpRvaL" + "\r"
      + "n61eQQnnPdpvHamkRBcOvgCAkfwa1uboru0QdXii/gUCQQDGmkP+KJPX9JVCrbRt" + "\r"
      + "7wKyIemyNM+J6y1ZBZ2bVCf9jacCQaSkIWnIR1S9UM+1CFE30So2CA0CfCDmQy+y" + "\r"
      + "7A31AkB8cGFB7j+GTkrLP7SX6KtRboAU7E0q1oijdO24r3xf/Imw4Cy0AAIx4KAu" + "\r"
      + "L29GOp1YWJYkJXCVTfyZnRxXHxSxAkEAvO0zkSv4uI8rDmtAIPQllF8+eRBT/deD" + "\r"
      + "JBR7ga/k+wctwK/Bd4Fxp9xzeETP0l8/I+IOTagK+Dos8d8oGQUFoQJBAI4Nwpfo" + "\r"
      + "MFaLJXGY9ok45wXrcqkJgM+SN6i8hQeujXESVHYatAIL/1DgLi+u46EFD69fw0w+" + "\r" + "c7o0HLlMsYPAzJw="
      + "\r";
  
  /**
   * 后台数据验证失败
   * */
  public static final int VALIDATE_FAILE_CODE = 5001;
  
  /**
   * ajax请求产生异常的返回码
   * */
  public static final int AJAX_EXCEPTION_CODE = 5033;
  
  /**
   * ajax请求时登录超时的返回码
   * */
  public static final int AJAX_SESSION_TIMEOUT_CODE = 4004;
  
  /**
   * ajax处理成功,提示
   * */
  public static final int AJAX_SUCCESS_ALERT_CODE = 2001;
  
  /**
   * ajax处理失败,提示
   * */
  public static final int AJAX_FAIL_ALERT_CODE = 2003;
  
  /**
   * ajax处理成功,跳转页面
   * */
  public static final int AJAX_SUCCESS_SKIP_CODE = 2005;
  
  /**
   * ajax处理失败,跳转页面
   * */
  public static final int AJAX_FAIL_SKIP_CODE = 2007;
  
  
  /**
   * ajax处理成功,刷新当前页面    
   * */
  public static final int AJAX_SUCCESS_RELOAD_CODE = 2100;
  
  /**
   * ajax处理失败,刷新当前页面    
   * */
  public static final int AJAX_FAIL_RELOAD_CODE = 2103;
  
  /**
   * ajax判断手机是否被注册，没有注册的    
   * */
  public static final int AJAX_SUCESS_PHONENUM_CODE = 1001;
  public static final int AJAX_PHONENUM_NOTEXIST_CODE = 0;
  /**
   * ajax判断手机是否被注册，已经注册的    
   * */
  public static final int AJAX_FAIL_PHONENUM_CODE = 1002; 
  public static final int AJAX_PHONENUM_EXIST_CODE = 1;
  /**
   *ajax判断是否发送了验证码，成功发送 
   * */
  public static final int AJAX_SUCESS_IDENTIFYING_CODE=1003;
  
  /**
   *ajax判断是否发送了验证码，未发送 
   * */
  public static final int AJAX_FAIL_IDENTIFYING_CODE=1004;
  
  /**
   * 登录超时返回的提示语
   * */
  public static final String SESSION_TIMEOUT_ALERT_MESSAGE = "登录信息超时，请先登录后再操作！";
  
  /**
   * 登录验证失败提示语
   * */
  public static final String LOGIN_ERROR = "账号或密码错误";
  /**
   *修改密码失败提示语
   * */
  public static final String MODIFYPASSWORD_ERROR = "MODIFYPASSWORD_ERROR";
  
  /**
   * 发生异常时返回的提示语
   * */
  public static final String EXCEPTION_ALERT_MESSAGE = "系统繁忙,请稍后重试";
  
  /**
   * ajax处理成功并提示的提示语
   * */
  public static final String AJAX_SUCCESS_MESSAGE = "Success";
  
  /**
   * ajax处理失败并提示的提示语
   * */
  public static final String AJAX_FAIL_MESSAGE = "fail";
  
  /**
   * 系统的加密解密的key名字
   * */
  public static final String ENCRYPT_CODE = "ENCRYPT_CODE";
  
  /**
   * 加密数据初始化秘钥
   * */
  public static final String ENCRYPT_INIT_KEY = "0101101010010010";
  
  // 登录Session名
  public static final String LOGINUSER_SESSION_NAME_USER = "LOGINUSER_SESSION_NAME_USER";
  public static final String LOGIN_SESSION_ADMIN = "LOGIN_SESSION_ADMIN";
  public static final String LOGIN_SESSION_MERCHANT="LOGIN_SESSION_MERCHANT";
  
  //权限列表
  public static final String ADMIN_LIMITS = "ADMIN_LIMITS";
  
  /**
   * 权限名称
   */
  public static final int LIMITS_SUPERADMIN = 1;//超级管理员
  public static final int LIMITS_MERCHANTADMIN = 2;//商家管理员
  public static final int LIMITS_DISHSADMIN = 3;//菜品管理员
  public static final int LIMITS_COMMENTSADMIN = 4;//评论管理员
  public static final int LIMITS_SALESADMIN = 5;//出货管理员
  public static final int LIMITS_FINANCEADMIN = 6;//财务管理员
  public static final int LIMITS_MESSAGEADMIN = 7;//站内信
  public static final int LIMITS_ADVANCEADMIN = 8;//预付款管理员


  /**
   * 支付宝
   */
  
public static final String SELLER_EMAIL = "13980730304@163.com";//卖家支付宝账号

  /**
   * 发送短信验证码的key
   * */
  public static final String SEND_PHONE_CODE = "SEND_PHONE_CODE";
  
  
  public static final int SEND_CODE_WAIT_TIME=60;

  
  /**
  *非法的可疑字符错误消息
  */
  public static final String INVALID_SQL_CHAR_EEROR="INVALID_SQL_CHAR_EEROR";
  
  /**
   * 验证码超时错误
   * */
 public static final int SMSCODE_TIMEOUT_ERROR = 61;
 
 
 /**
  * 验证码验证错误
  * */
public static final int SMSCODE_CHECKE_FAIL_ERROR = 61;
/**
 * 单个页面数据个数
 */
public static final int PAGE_SIZE = 10;

/**
 *起始日期
 */
public static final String CURRENT_DATE_STRING = "2015-01-01";

}
