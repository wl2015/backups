package com.yc.util;
/**
 * 返回结果常量
 * @author android_djf
 *
 */
public class ResultCode {

  //ajax传输结果集
  public static final String AJAX_CODE_SUCCESS ="1";//成功
  public static final String AJAX_CODE_FAIL ="2";//失败
  
  //手机号存在
  public static final int PHONENUM_ISEXIST_YES = 1;//存在
  public static final int PHONENUM_ISEXIST_NO = 0;//不存在
  //结果集
  public static final String SUCCESS    = "0";  //成功
  public static final String FAIL     = "1";  //失败
  public static final String EXCEPTION  = "-1"; //异常
  
  
  public static final String USEREXIST  = "10000";//用户已经存在
  public static final String FILENOLEGAL  = "10001";//文件不合法
  public static final String VINT     = "10002";//验证未通过
  public static final String UNUSEREXIST  = "10003";//用户不存在
  public static final String OLDPWDFAIL = "10004";//旧密码输入错误
  
  
  
  /**
   * 创建管理员返回值
   */
  public static final String AJAX_ISEXISTPHONE_CODE = "管理员手机号已存在！";
  public static final String AJAX_ISEXISTACCOUNT_CODE = "管理员账号已存在！";
  public static final String AJAX_CREATESUCCESS_CODE = "创建管理员成功！";
  
  /**
   * 商家的运营模式
   * */
  public static final int OPERATE_MODE_SINGLE = 1;//表示单商家模式
  public static final int OPERATE_MODE_MANY = 2;//表示多商家模式
  
  public static final int TARGET_DISH = 1;//目标物是菜品 
  public static final int TARGET_DISHGROUP = 2;//目标物是套餐
}
