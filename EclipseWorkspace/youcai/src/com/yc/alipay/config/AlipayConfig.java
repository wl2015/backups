package com.yc.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088021186012315";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	public static String seller_id = partner;
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgVItCJ0HVJ/NwNimkbvvpEdqyK2XTITYJXqvrtpJxFYJoSPGQUpwnpqXphzSc/PqeUiiWHK5E5UsTvsHqKQUz1/LOKGAV3XvUcMBb51aiet6ywwgLsurDspusBlOxx9fMkxiaZTVy15lGRp9EBPD5IJSWTcsInCcqGobm+YiaNAgMBAAECgYBLyhQDslCzwmPpGZXypRctJBfa31UEaplAj6VCNkvGRyYy8swWWJWlS54m19i4vLTUvY65M0T6270tneKWrqaYgL00ZMK6VMw0lkVncVrmMZ5Ci+wvHcKEhccqcc/ZxTzbRUyBThqMBLJifuWsIB9tSYIUglPOHEGyUdSB27T1pQJBAN6fep9E41Q0hkH5OVm7beVmZIeKa+d7DSZHyK2m3ao+HNjOxxz3U3qyesLwiBkxcnC0XlAOZHBeQne8ioeWNB8CQQDBSFWv96NtjNmTiHcDrjkgG83HAdXy7fZvQCl3Yo11UalRMSiXen9dyBq/tc/XyR5bawI4U6JQsx2B7IZRjK/TAkEAmw4cy/2hEC8dK6o4JaWD33k3n6fri+iKuhTZUwmb7k0grmknCafBvv28vzXktRdZi+I0pjHABvu/88GgAtu9JQJBAI9NYCp9QnAgs9hMn2DdNYF229SmqcfZYpMNUTEwoozPJOgLuHrG2dPgXuSwmg71EErbReQZLgkDI+eGQ/gGhP0CQGroxEiR1rq/nmHFGATLwYSGzZoe4eEjohvQoaqGYMPBfzaCAiJmNfT5eQoz6zhMqX+T4jEI0jHMoQAKbmpzDJo=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
