package com.h5.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.h5.basic.exceptions.ValidateFailException;

/**
 * 密码工具
 * @author Paul Iverson
 *
 */
public class CryptogramUtil {

	private static final int ADD_NUM = 30000;
	
	private static Logger logger = Logger.getLogger(CryptogramUtil.class);
	
	/**
	 * 移位加密
	 * */
	public static String encode(long id) {
		
		return String.valueOf(ADD_NUM + id);
	}
	
	/**
	 * 移位解密
	 * */
	public static long decode(String id) {
		
		return (Long.parseLong(id) - ADD_NUM);
	}
	
	/**
	 * 给出AES加密解密的随机16位key和iv
	 * */
	public static String getRandomKey() {
		int[] keyChar = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		StringBuffer keyBuffer = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			keyBuffer.append(keyChar[new Random().nextInt(keyChar.length)]);
		}
		
		return keyBuffer.toString();
	}
	
	/**
     * 加密
     * @param content 需要加密的内容
     * @param key [未解密]的加密密码
     * @return 加密后的字符串数据
	 * @throws Exception 
     */
	public static String encrypt(String content, String key) throws Exception {
		key = getRealyKey(key);
		
		return encrypt(content, key, key);
	}
	
	/**
     * 加密
     * @param content 需要加密的内容
     * @param key 加密密码
     * @param iv 加密向量
     * @return 加密后的字符串数据
	 * @throws Exception 
     */
	public static String encrypt(String content, String key, String iv) throws Exception {
		byte[] keyByte = key.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(keyByte, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding"); //"算法/模式/补码方式"
		IvParameterSpec ivps = new IvParameterSpec(iv.getBytes("utf-8"));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivps);
        
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        
        return Base64.encodeBase64String(encrypted);
	}
	
	/**
	 * 针对本系统的解密方案
	 * @param content 需要解密的内容
     * @param key [未解密]的加密密码
     * @return 解密后的Map<前端的key,前端传入的数据>
	 * @throws Exception 
	 * */
	public static Map<String, Object> decryptAll(String content, String key) throws Exception {
		
		key = getRealyKey(key);
		
		logger.debug("\n\n\n真正的密码:" + key);
		
		content = content.replace(" ", "+");
		
		logger.debug("\n\n\n解密前:" + content);
		
		String decodedData = decrypt(content, key, key);
		
		logger.debug("解密后的数据是:" + decodedData);
		
		return parseDataToMap(decodedData);
	}
	
	private static Map<String, Object> parseDataToMap(String content) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			String[] datas = content.split("&");
			for (String data : datas) {
				int equalsFlagPosition = data.indexOf("=");
				String key = data.substring(0, equalsFlagPosition);
				String value = data.substring(equalsFlagPosition + 1);
				dataMap.put(key, value);
			}
		} catch(Exception ex) {
			throw new ValidateFailException("input data can't parse to request data, the input is:" + content);
		}
		
		return dataMap;
	}
	
	/**
	 * 得到真实的key
	 * @param key加密后的秘钥
	 * @throws Exception 
	 * */
	private static String getRealyKey(String key) throws Exception {
	
		return decrypt(key, Constants.ENCRYPT_INIT_KEY, Constants.ENCRYPT_INIT_KEY);
	}
	
	/**
     * 解密
     * @param content 需要解密的内容
     * @param key [未解密]的加密密码
     * @return 解密后的字节数据
	 * @throws Exception 
     */
	public static String decrypt(String content, String key) throws Exception {
		content = content.replace(" ", "+");
		key = getRealyKey(key);
        logger.debug("\n\n\n\n-----key:  "+ key);
		return decrypt(content, key, key); 
    }
	
	/**
     * 解密
     * @param content 需要解密的内容
     * @param key 加密密码
     * @param iv 加密向量
     * @return 解密后的字节数据
	 * @throws Exception 
     */
	private static String decrypt(String content, String key, String iv) throws Exception {
		byte[] keyByte = key.getBytes("utf-8"); 
		SecretKeySpec skeySpec = new SecretKeySpec(keyByte, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding"); //"算法/模式/补码方式"
        IvParameterSpec ivps = new IvParameterSpec(iv.getBytes("utf-8"));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivps);
        byte[] encrypted = Base64.decodeBase64(content);
        byte[] original = cipher.doFinal(encrypted);
        
        return java.net.URLDecoder.decode(new String(original), "utf-8"); 
    }
	
	/**
	 *MD5加密
	 * @param content
	 * @return
	 */
	public static String md5(String content) throws Exception{
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = content.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void main(String[] args) throws Exception {
		
//		String key = getRandomKey();
//		String iv = key;// iv必须和key一致
//		
//		String content = "981134442@qq.com";
//		
//		String en1 = encrypt(content, key, iv);
//		
//		System.out.println("密文1:" + en1);
//		
//		System.out.println("明文1:" + decrypt(en1, key, key));
		
		/* SyntaxError: JSON.parse: unexpected character at line 1 column 1 of the JSON data
		http://localhost:8080/shangwangding/res/js/test/jquery-1.11.1.js
			Line 8482
		*/
		
//		String v = "C7GRyAwgrHw40r P2fb6pA==";
//		
//		Map<String, Object> s = decrypt(v, "0101101010010001");
//		
//		for (Map.Entry<String, Object> m : s.entrySet()) {
//			System.out.println("key:" + m.getKey() + ", value:" + m.getValue());
//		}
//		String s = "IyFjFYjVlmBRCtsImBgHXh5ZpQsCftQySHb6fG0XOoY=";
		
		String specialCode = "+";
		md5("string");
		System.out.println("----" + java.net.URLDecoder.decode(specialCode, "utf-8") + "----");
	}
}
