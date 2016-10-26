package com.cn.fruits.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import sun.misc.BASE64Encoder;

/**
 * 系统工具
 */
public class SystemUtil {

  private static final Logger logger = Logger.getLogger(SystemUtil.class);

  /**
   * @功能 获取请求者IP
   */
  public static String getRequestIp(HttpServletRequest req) {

    String ip = req.getHeader("x-forwarded-for");

    logger.error("\n\n\n---0-ip-:" + ip);

    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {

      ip = req.getHeader("Proxy-Client-IP");
      logger.error("---1-ip-:" + ip);
    }

    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {

      ip = req.getHeader("WL-Proxy-Client-IP");
      logger.error("---2-ip-:" + ip);
    }

    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {

      ip = req.getHeader("X-Real-IP");
      logger.error("---3-ip-:" + ip);
    }

    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {

      ip = req.getRemoteAddr();
      logger.error("---4-ip-:" + ip);
    }

    if (!ValidateUtil.strIsEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
      int end = ip.indexOf(",");

      if (end != -1) {

        ip = ip.substring(0, end);
        logger.error("---5-ip-:" + ip);
      }
    }

    logger.error("---最终-ip-:" + ip + "\n\n\n");

    return ip;
  }

  /**
   * 获取mac地址,只能在windows上生效,在Linux上无效
   * 
   * @throws IOException
   */
  /*
   * public static String getMACAddress(String ip) throws IOException { String
   * macAddress = "";
   * 
   * Process process = null; InputStreamReader reader = null; LineNumberReader
   * input = null; try { String getMacCommand = "nbtstat -A " + ip;
   * 
   * process = Runtime.getRuntime().exec(getMacCommand); reader = new
   * InputStreamReader(process.getInputStream()); input = new
   * LineNumberReader(reader);
   * 
   * String str = ""; StringBuffer sb = new StringBuffer(); while ((str =
   * input.readLine()) != null) { // str = new String(str.getBytes("GBK"),
   * "UTF-8"); // logger.error("\n\n\n获取mac地址时进程读取到的信息:" + new
   * String(str.getBytes("UTF-8")) + "\n对应编码为: "+getEncoding(str)+"\n\n\n");
   * sb.append(str); int macFlagPosition = str.indexOf("MAC Address"); if
   * (macFlagPosition > 0) { macAddress = str.substring(macFlagPosition + 14);
   * 
   * break; } } logger.debug("\n\n\n\n------------得到的数据:-----------------");
   * logger.error("编码格式:" + getEncoding(sb.toString())); logger.error("原文:" +
   * sb.toString()); logger.debug("-----------------------------\n\n\n\n"); }
   * catch(IOException e) { throw e; } finally { if (null != process) {
   * process.destroy(); process = null; } if (null != reader) { reader.close();
   * reader = null; } if (null != input) { input.close(); input = null; } }
   * 
   * return macAddress; }
   */

  /**
   * 获取客户端mac地址
   */
  public static String getMACAddress(String ip) throws IOException {

    String[] cmd = { "/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a" };

    String result = callCmd(cmd);

    Pattern pattern = Pattern.compile("((([0-9,A-F,a-f]{1,2}:){1,5})[0-9,A-F,a-f]{1,2})");
    Matcher matcher = pattern.matcher(result);
    while (matcher.find()) {
      result = matcher.group(1);
      if (result.indexOf(ip) <= result.lastIndexOf(ip)) {
        break;
      }
    }

    return result;
  }

  public static String callCmd(String[] cmd) throws IOException {

    String line = "";
    StringBuffer calledMessage = new StringBuffer();

    Process process = null;
    InputStreamReader reader = null;
    LineNumberReader input = null;
    try {
      process = Runtime.getRuntime().exec(cmd);
      reader = new InputStreamReader(process.getInputStream());
      input = new LineNumberReader(reader);

      while ((line = input.readLine()) != null) {
        // str = new String(str.getBytes("GBK"), "UTF-8");
        // logger.error("\n\n\n获取mac地址时进程读取到的信息:" + new
        // String(str.getBytes("UTF-8")) + "\n对应编码为:
        // "+getEncoding(str)+"\n\n\n");
        calledMessage.append(line);
      }
      logger.debug("\n\n\n\n------------得到的数据:-----------------");
      logger.error("编码格式:" + getEncoding(calledMessage.toString()));
      logger.error("原文:" + calledMessage.toString());
      logger.debug("-----------------------------\n\n\n\n");
    } catch (IOException e) {
      throw e;
    } finally {
      if (null != process) {
        process.destroy();
        process = null;
      }
      if (null != reader) {
        reader.close();
        reader = null;
      }
      if (null != input) {
        input.close();
        input = null;
      }
    }

    return calledMessage.toString();
  }

  /**
   * 给出短信验证码
   */
  public static int getPhoneCheckCode() {
    int code = (int) (Math.random() * 1000000);
    while (code <= 100000) {
      code = (int) (Math.random() * 1000000);
    }
    return code;
  }

  /**
   * 返回浏览器的类型
   * 
   * @param request
   * @return
   */
  public static String getBrowserType(HttpServletRequest request) {
    String browser = request.getHeader("User-Agent");
    if (browser == null || browser.equals("")) {
      browser = "未知(unknown)";
    }
    return browser;
  }

  /**
   * 判断字符串编码
   * 
   * @param source:待验证的字符串
   * @return 字符串的编码方式
   */
  public static String getEncoding(String source) {
    String charset = "unknown";

    String[] chars = { "ISO-8859-1", "GB2312", "GBK", "UTF-8" };

    for (String cs : chars) {
      try {
        if (source.equals(new String(source.getBytes(cs), cs))) {
          logger.debug("\n\n------------------------\n" + source + "\n----------------------\n\n");
          charset = cs;
          break;
        }
      } catch (Exception e) {
      }
    }

    return charset;
  }

  /**
   * 生成token
   * 
   * @return
   */
  public static String makeToken() { // checkException
    String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
    // 数据指纹 128位长 16个字节 md5
    try {
      MessageDigest md = MessageDigest.getInstance("md5");
      byte md5[] = md.digest(token.getBytes());
      // base64编码--任意二进制编码明文字符 adfsdfsdfsf
      BASE64Encoder encoder = new BASE64Encoder();
      return encoder.encode(md5);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }


}
