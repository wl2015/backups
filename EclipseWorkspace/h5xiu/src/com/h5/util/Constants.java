package com.h5.util;

/**
 * 系统常量
 * @author Paul Iverson
 *
 */
public class Constants {

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
	
	// 用户登录Session名
	public static final String LOGINUSER_SESSION_NAME_USER = "LOGINUSER_SESSION_NAME_USER";
	public static final String LOGINUSER_SESSION_NAME_ADMIN = "LOGINUSER_SESSION_NAME_ADMIN";
	
	/**
	 * 文件上传的项目名称
	 * */
	public static final String FILE_UPLOAD_SITE_NAME = "/wedding321_uploads";
	
	/**
	 * 文件上传的IP
	 * */
	public static final String FILE_UPLOAD_SITE_IP = "182.92.170.213";
	
	/**
	 * 发送短信验证码的key
	 * */
	public static final String SEND_PHONE_CODE = "SEND_PHONE_CODE";
	
	public static final String PATH = "path"; 
	public static final String LPATH="http://localhost:8080/h5xiu";
	public static final int SEND_CODE_WAIT_TIME=60;
	
}
