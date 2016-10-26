package com.h5.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.h5.basic.exceptions.ValidateFailException;


/**
 * 数据验证工具
 * */
public final class ValidateUtil {

	private static final Logger logger = Logger.getLogger(ValidateUtil.class);
	
	public static final String[] ALLOWED_MOVIES = {".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"};
	public static final String[] ALLOWED_PIC = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
	
	/**
	 * 验证空字符串
	 * */
	public static String valiStrIsEmpty(String str) {
		if (strIsEmpty(str))
			throw new ValidateFailException(str + " is null");
		return str;
	}
	
	/**
	 * 验证数字字符串
	 */
	public static int valiStrIsNum(String str) {
		if (!strIsNumber(str))
			throw new ValidateFailException(str + " is't number");
		
		return Integer.parseInt(str);
	}
	
	/**
	 * 验证电话号码字符串
	 */ 
	public static String valiStrIsPhone(String str) {
		if (!strIsPhone(str))
			throw new ValidateFailException(str + " is't phone number");
		
		return str;
	}
	
	/**
	 * 验证邮箱字符串
	 * */
	public static String valiStrIsEmail(String str) {
		if (!strIsEmail(str))
			throw new ValidateFailException(str + " is't email");
		
		return str;
	}
	
	/**
	 * 验证身份证字符串
	 * */
	public static String valiStrIsIdCard(String str) {
		if (!strIsIdCard(str))
			throw new ValidateFailException(str + " is't a id card");
		
		return str;
	}

	/**
	 * 验证视频地址
	 * @param str:需要验证的字符串
	 * @param webSiteName:url来源于哪个网站
	 * @return 传入的字符串本身
	 * */
	public static String valiStrIsMovie(String str, String webSiteName) {
		if (strIsEmpty(webSiteName) || strIsEmpty(str)) {
			throw new ValidateFailException("str or webSiteName is null");
		} else if(strIsAllowUrl(str, webSiteName) && checkUrlCoda(str, ALLOWED_MOVIES)) {
			return str;
		} else {
			throw new ValidateFailException(str + " is not allowed movie, just " + ALLOWED_MOVIES.toString() + " is allowed");
		}
	}
	
	private static boolean checkUrlCoda(String str, String[] regx) {
		int keyNum = 0;
		for (String r : regx) {
			if (str.indexOf(r) != -1) {
				keyNum ++;
			}
		}
		if (keyNum == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证图片地址
	 * @param str:需要验证的字符串
	 * @param webSiteName:url来源于哪个网站
	 * @return 传入的字符串本身
	 * */
	public static String valiStrIsPic(String str, String webSiteName) {
		if (strIsEmpty(webSiteName) || strIsEmpty(str)) {
			throw new ValidateFailException("str or webSiteName is null");
		} else if(strIsAllowUrl(str, webSiteName) && checkUrlCoda(str, ALLOWED_PIC)) {
			return str;
		} else {
			throw new ValidateFailException(str + " is not allowed picture, just " + ALLOWED_MOVIES.toString() + " is allowed");
		}
	}
	
	/**
	 * 验证url是否为允许的url
	 * @param str:需要验证的字符串
	 * @param webSiteName:url来源于哪个网站
	 * @return 传入的字符串本身
	 * */
	public static String valiStrIsAllowedUrl(String str, String webSiteName) {
		
		logger.debug("\n\n\n" + webSiteName + "\n\n\n");
		
		if (strIsEmpty(webSiteName) || strIsEmpty(str)) {
			throw new ValidateFailException("str or webSiteName is null");
		} else if (strIsAllowUrl(str, webSiteName)) {
			return str;
		} else {
			throw new ValidateFailException(str + " is not allowed url");
		}
	}
	
	/**
	 * 判断是否为空字符串
	 * 等于null,长度为0,trim后长度为0均返回true
	 * */
	public static boolean strIsEmpty(String str) {
		if (null == str) return true;
		if (str.trim().length() == 0) return true;
		
		return false;
	}
	
	/**
	 * 判断是否为数字
	 * */
	public static boolean strIsNumber(String str) {
		
		return validateInput(str, 0, str.length(), "0");
	}
	
	/**
	 * 判断是否为电话号码
	 * */
	public static boolean strIsPhone(String str) {
		
		//String reg = "(1){1}([0-9]){2}(-){1}([0-9]){4}(-){1}([0-9]){4}";
		String reg = "(1){1}([3,4,5,8]){1}([0-9]){5}([0-9]){4}";
		
		Pattern pattern = Pattern.compile(reg);
		
		Matcher mac = pattern.matcher(str);
		
		if (mac.matches()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断是否为邮箱
	 * */
	public static boolean strIsEmail(String str) {
		
		return validateInput(str, 0, str.length(), "e");
	}
	
	/**
	 * 判断是否为身份证
	 * */
	public static boolean strIsIdCard(String str) {
		
		return true;
	}

	/**
	 * 判断资源来源是否被允许
	 * */
	public static boolean strIsAllowUrl(String str, String webSiteName) {
		if (str.startsWith(webSiteName)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 验证数据输入
	 * @param str
	 *            input value
	 * @param min
	 *            min length
	 * @param max
	 *            max length
	 *            如果不需要则验证的长度则max,min均传入0
	 * @param type
	 *            (只含有下面说明的) 
	 *            0代表含有数字 
	 *            a代表含有小写字母 
	 *            A代表含有大写字母 
	 *            _代表含有下划线
	 *             
	 *            E或e代表邮箱验证
	 *            
	 * @return true:正确 false:错误
	 * */
	public static boolean validateInput(String str, int min, int max, String type) {
		
		if (strIsEmpty(str)) return false;
		
		StringBuffer vali = new StringBuffer("[");
		
		if (type.contains("0"))
			vali.append("0-9");
		
		if (type.contains("a"))
			vali.append("a-z");
		
		if (type.contains("A"))
			vali.append("A-Z");
		
		if (type.contains("_"))
			vali.append("_");
		
		if (type.contains("S"))
			vali.append("\\S");
		vali.append("]");
		if (max != 0) {
			int len = str.trim().length();
			if (len < min || len > max) return false;
			vali.append("{");
			vali.append(min);
			vali.append(",");
			vali.append(max);
			vali.append("}");
		} else {
			vali.append("*");
		}
		
		if (type.equalsIgnoreCase("e")) {
			vali = new StringBuffer("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		}
		
		Pattern pattern = Pattern.compile(vali.toString());
		Matcher mat = pattern.matcher(str);
		
		if (mat.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String test  = "182-0038-8563";
		System.out.println(test.replaceAll("-", ""));
	}
	
}
