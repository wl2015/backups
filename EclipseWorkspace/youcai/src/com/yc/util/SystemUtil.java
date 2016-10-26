package com.yc.util;

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


import sun.misc.BASE64Encoder;

/**
 * 系统工具
 * */
public class SystemUtil {

  private static final Logger logger = Logger.getLogger(SystemUtil.class);
  
  /**
   * @功能 获取请求者IP
   * */
  public static String getRequestIp(HttpServletRequest req) {
    
    String ip = req.getHeader("x-forwarded-for");
    
    logger.error("\n\n\n---0-ip-:"+ip);
    
    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
      
      ip = req.getHeader("Proxy-Client-IP");
      logger.error("---1-ip-:"+ip);
    }
    
    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
      
      ip = req.getHeader("WL-Proxy-Client-IP");
      logger.error("---2-ip-:"+ip);
    }
    
    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
    
      ip = req.getHeader("X-Real-IP");
      logger.error("---3-ip-:"+ip);
    }
    
    if (ValidateUtil.strIsEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
      
      ip = req.getRemoteAddr();
      logger.error("---4-ip-:"+ip);
    }
    
    if (!ValidateUtil.strIsEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
      int end = ip.indexOf(",");
      
      if (end != -1) {
        
        ip = ip.substring(0, end);
        logger.error("---5-ip-:"+ip);
      }
    }
    
    logger.error("---最终-ip-:"+ip + "\n\n\n");
    
    return ip;
  }
  
  /**
   * 获取mac地址,只能在windows上生效,在Linux上无效
   * @throws IOException 
   * */
  /*
  public static String getMACAddress(String ip) throws IOException {
    String macAddress = "";
    
    Process process = null;
    InputStreamReader reader = null;
    LineNumberReader input = null;
    try {
      String getMacCommand = "nbtstat -A " + ip;
      
      process = Runtime.getRuntime().exec(getMacCommand);
      reader = new InputStreamReader(process.getInputStream());
      input = new LineNumberReader(reader);
      
      String str = "";
      StringBuffer sb = new StringBuffer();
      while ((str = input.readLine()) != null) {
  //      str = new String(str.getBytes("GBK"), "UTF-8");
//        logger.error("\n\n\n获取mac地址时进程读取到的信息:" + new String(str.getBytes("UTF-8")) + "\n对应编码为: "+getEncoding(str)+"\n\n\n");
        sb.append(str);
        int macFlagPosition = str.indexOf("MAC Address");
        if (macFlagPosition > 0) {
          macAddress = str.substring(macFlagPosition + 14);
          
          break;
        }
      }
      logger.debug("\n\n\n\n------------得到的数据:-----------------");
      logger.error("编码格式:" + getEncoding(sb.toString()));
      logger.error("原文:" + sb.toString());
      logger.debug("-----------------------------\n\n\n\n");
    } catch(IOException e) {
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
    
    return macAddress;
  }
  */
  
  /**
   * 获取客户端mac地址
   * */
  public static String getMACAddress(String ip) throws IOException {
    
    String[] cmd = {
        "/bin/sh",
        "-c",
        "ping " + ip + " -c 2 && arp -a"
    };
    
    String result = callCmd(cmd);
    
    Pattern pattern = Pattern.compile("((([0-9,A-F,a-f]{1,2}:){1,5})[0-9,A-F,a-f]{1,2})");
    Matcher matcher = pattern.matcher(result);
    while (matcher.find()) {
      result = matcher.group(1);
      if (result.indexOf(ip) <= result.lastIndexOf(ip)) {
        break ;
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
  //      str = new String(str.getBytes("GBK"), "UTF-8");
//        logger.error("\n\n\n获取mac地址时进程读取到的信息:" + new String(str.getBytes("UTF-8")) + "\n对应编码为: "+getEncoding(str)+"\n\n\n");
        calledMessage.append(line);
      }
      logger.debug("\n\n\n\n------------得到的数据:-----------------");
      logger.error("编码格式:" + getEncoding(calledMessage.toString()));
      logger.error("原文:" + calledMessage.toString());
      logger.debug("-----------------------------\n\n\n\n");
    } catch(IOException e) {
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
   * */
  public static int getPhoneCheckCode() {
    int code = (int) (Math.random() * 1000000);
    while(code<=100000) {
      code = (int)(Math.random()*1000000);
    }
    return code;
  }
  
  /**
   * 返回浏览器的类型
   * @param request
   * @return
   */
  public static String getBrowserType(HttpServletRequest request){
    String browser = request.getHeader("User-Agent");
    if (browser == null || browser.equals("")){
      browser = "未知(unknown)";
    }
    return browser;
  }
  
  /**
   * HTML操作:过滤字符串中非法信息并替换
   * @param context 需要过滤掉非法字符("<",">",空格)的内容
   * @return String 替换完成的字符串内容
   * */
  public static final String convertSpecialMark(String context) {
    context = context.replaceAll("<", "&lt");
    context = context.replaceAll(">", "&gt");
    context = context.replaceAll("[\\s]", "&nbsp;");
    
    return context;
  }
  
  /**
   * HTML操作:去除script脚本
   * */
  public static final String removeAllScriptFromHtml(String html) {
    if (null == html){
      return html;
    }
    String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
    
    return html.replaceAll(regEx_script, "");
  }
  
  /**
   * HTML操作:去除<a>标签
   * */
  public static String removeATagFromHtml(String html) {
    String result = html.replaceAll("<\\s*a\\s+([^>]*)>", "");
    result = result.replaceAll("<\\s*/\\s*a\\s*>", "");
    return result;
  }
  
  /**
   * HTML操作:去除所有<>标签
   * */
  public static final String removeAllTagFromHtml(String html) {
    String result = html.replaceAll("<\\s*([a-zA-Z]*)\\s*(\\w||\\W)*\\s*[/]*\\s*>", "");
    result = result.replaceAll("<\\s*/\\s*([a-zA-Z]*)\\s*>", "");
    
    return result;
  }
  /**
   * HTML操作:去除所有不完整标签
   * */
  public static final String removeAllNotCompleteTagFromHtml(String html) {
    String result = html.replaceAll("<\\s*([a-zA-Z]*)\\s*(\\w||\\W)*\\s*[/]*\\s*>", "");
    result = result.replaceAll("<.[^>]*", "");
    
    return result;
  }
  
  /**
   * HTML操作:提取字符串中的图片的url
   * @param content 需要取出图片的内容
   * @return List 取出的图片的url
   * */
  public static final List<String> getImgSrcListFromHtml(String content) {
    
//    Matcher imgCotentMatcher = Pattern.compile("<\\s*img\\s+([^>]*)>").matcher(content);
//    
//    List<String> imgUrlList = new ArrayList<String>();
//    
//    Pattern imgUrlPattern = Pattern.compile("src\\s*=\\s*\"([^\"]+)\"");
//    
//    while(imgCotentMatcher.find()) {
//    
//      Matcher imgUrlMatcher = imgUrlPattern.matcher(imgCotentMatcher.group());
//          if(imgUrlMatcher.find()){
//          
//            String srcAttr = imgUrlMatcher.group();
//            imgUrlList.add(srcAttr.substring(srcAttr.indexOf('"')+1, srcAttr.lastIndexOf('"')));  
//          }  
//    }  

    List<String> imgUrlList = getTagAttrbuteValue(content, "img", "src");
    
    return imgUrlList;
  }
  
  /**
   * HTML操作:去除字符串中的图片内容
   * @param context 需要去除图片的字符串
   * @return String 去除了图片的字符串
   * */
  public static String removeImgTagFromHtml(String html) {
    Matcher imgCotentMatcher = Pattern.compile("<\\s*img\\s+([^>]*)>").matcher(html);
    return imgCotentMatcher.replaceAll("");
  }

  /**
   * HTML操作:为<a>标签加上title属性
   * @param aTagStr:<a href="">这个样子的字符串</a>
   * @param title: 需要插入的标题
   * @return <a title="标题" href="">这个样子的字符串</a>
   * */
  public static String insertTitleToATag(String aTagStr, String title) {
    String subFlag = "href";
    
    // 如果原来有href就替换掉为title
    int hrefPosition = aTagStr.indexOf(subFlag);
    String hrefBeforeStr = aTagStr.substring(0, hrefPosition);
    String afterHrefStr = aTagStr.substring(hrefPosition + subFlag.length());
    
    return new StringBuffer(hrefBeforeStr).append("title=\"").append(title).append("\" ").append(subFlag).append(afterHrefStr).toString();
  }
  
  /**
   * HTML操作:为<img>标签加上alt和title属性
   * @param imgTagStr:<img src="xxx"/>或者<img src="xxx"></img>
   * @param title: 需要插入的标题
   * @return <img alt="标题" title="标题" src="xxx"/>或者<img  alt="标题" title="标题" src="xxx"></img>
   * */
  public static String insertTitleToImgTag(String imgTagStr, String alt, String title) {
    String subFlag = "src";
    int srcPosition = imgTagStr.indexOf(subFlag);
    String srcBeforeStr = imgTagStr.substring(0, srcPosition);
    String afterSrcStr = imgTagStr.substring(srcPosition + subFlag.length());
    
    StringBuffer imgBuffer = new StringBuffer(srcBeforeStr);
    imgBuffer.append("alt=\"").append(alt).append("\" ");
    imgBuffer.append("title=\"").append(title).append("\" ");
    imgBuffer.append(subFlag).append(afterSrcStr);

    return imgBuffer.toString();
  }
  
  /**
   * 一次性为HTML中的<a>和<img>添加alt和title;删除空标签<p><a>等等
   * @param String source 要处理的节点
   * @param String title 需要添加的标题 
   * @return 优化后的字符串
   * */
  public static String doArticleSeo(String source, String title) {
    
    String reg = "<\\s*(img|a)\\s+([^>]*)>";
    String imgReg = "<\\s*img";
    String aReg = "<\\s*a";
    String attrReg = "\\s*=\\s*\"([^\"]+)\"";

    Matcher m = Pattern.compile(reg).matcher(source);
    
    while (m.find()) {
      String oneNode = m.group();
      String newNode=oneNode;
      if (Pattern.compile(imgReg).matcher(oneNode).find()) {
        if (oneNode.contains("title")) {
          newNode = oneNode.replaceAll("title" + attrReg, "title=\"" + title + "\"");
        } else {
          newNode = insertAttrbuteValueToNode(oneNode, "title", title);
        }
        if (oneNode.contains("alt")) {
          newNode = newNode.replaceAll("alt" + attrReg, "alt=\"" + title + "\"");
        } else {
          newNode = insertAttrbuteValueToNode(newNode, "alt", title);
        }
      } else if (Pattern.compile(aReg).matcher(oneNode).find()) {
        if (oneNode.contains("title")) {
          newNode = oneNode.replaceAll("title" + attrReg, "title=\"" + title + "\"");
        } else {
          newNode = insertAttrbuteValueToNode(oneNode, "title", title);
        }
      }
      
      source = source.replace(oneNode, newNode);
    }
    
    return source;
  }
  
  /**
   * HTML操作:替换掉<a>标签中的title,如果没有title就加上title
   * */
  
  /**
   * HTML操作:替换掉<img>标签中的alt和title,如果没有就加上这些属性
   * */

  /**
   * HTML操作:为指定节点(只能是一个完整的单节点)添加上指定属性
   * @param String node 要处理的节点 
   * @param String attrName 需要添加的属性名
   * @param String attrVal 需要添加的属性值
   * @return 替换完成后的字符串
   * */
  public static String insertAttrbuteValueToNode(String node, String attrName, String attrVal) {
    Matcher m = Pattern.compile("[\\/]?\\s*>").matcher(node);
    
    String nodeEndFlag = ">";
    if (m.find()) {
      nodeEndFlag = m.group();
    }
    
    node = node.replaceFirst(nodeEndFlag, " " + attrName + "=\"" + attrVal +"\""+ nodeEndFlag);
    
    return node;
  }
  
  /**
   * HTML操作:为指文本中指定tag添加指定属性,如果含有属性则替换掉指定节点的指定属性
   * @param String source, 要处理的原文本 
   * @param String tagName, 标签名称
   * @param String attrName 需要替换的属性名
   * @param String attrVal 需要替换的值
   * @return 替换完成后的字符串 
   * */
  public static String replaceOrAndTagAttribute(String source, String tagName, String attrName, String attrVal) {
    
    String reg = "<\\s*" + tagName +"[^<>]*?\\s*>";
    String attrReg = attrName + "\\s*=\\s*\"([^\"]+)\"";
    String attr = attrName + "=\"" +attrVal + "\"";
    Matcher m = Pattern.compile(reg).matcher(source);
    
    while (m.find()) {
      String oneNode = m.group();
      String newNode = oneNode;
      if (oneNode.contains(attrName)) {
        newNode = oneNode.replaceAll(attrReg, attr);
      } else {
        newNode = insertAttrbuteValueToNode(oneNode, attrName, attrVal);
      }
      source = source.replace(oneNode, newNode);
    }
    
    return source;
  }

  /** 
     * 获取指定HTML标签的指定属性的值 
     * @param source 要匹配的源文本 
     * @param tagName 标签名称 
     * @param attrName 标签的属性名称 
     * @return 属性值列表 
     */
  public static List<String> getTagAttrbuteValue(String source, String tagName, String attrName) {
    
    String reg = "<\\s*" + tagName +"[^<>]*?\\s" + attrName + "\\s*=\\s*\"([^\"]+)\"[^<>]*?>";
    
    List<String> tagAttrList = new ArrayList<String>();
    
    Matcher m = Pattern.compile(reg).matcher(source);
    
    while(m.find()) {
      tagAttrList.add(m.group(1));
    }
    
    return tagAttrList;
  }
  
  /*public static String getQrcode(String uploadUrl, String text) throws Exception{
    if (text == null || text==""){
      text = "http://weibo.com/2643127210"; 
    } 
        int width = 200; //生成二维码图片宽度  
        int height = 200;   //生成二维码图片高度  
        Map<EncodeHintType, String> hints= new Hashtable<EncodeHintType, String>();   
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
    URL responseUrl = new URL(uploadUrl );
    URLConnection connection = responseUrl.openConnection();
    HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
    *//**
         * 设置关键值
         *//*
    httpURLConnection.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
    httpURLConnection.setDoInput(true);
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setUseCaches(false); // post方式不能使用缓存
 
        // 设置请求头信息
    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
    httpURLConnection.setRequestProperty("Charset", "UTF-8");
 
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
        
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // ////////必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"dir\";filename=\"paul.jpeg\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        
        byte[] head = sb.toString().getBytes("utf-8");
        
      OutputStream outStream = httpURLConnection.getOutputStream();
      DataOutputStream dataOut = new DataOutputStream(outStream);
      dataOut.write(head);
      
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ImageIO.write(image, "JPEG", out);
      byte[] dataByte = out.toByteArray();
      logger.debug("\n\n\n---length: "+dataByte.length);
      dataOut.write(dataByte);
      
      // 结尾部分
      byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
      dataOut.write(foot);
      dataOut.flush();
      dataOut.close();
        
      InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuilder resultBuffer = new StringBuilder();
        String tempLine = null;
        
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }    
            if (inputStream != null) {
                inputStream.close();
            }
            
        }
        String data = resultBuffer.toString();
        JSONObject obj = new JSONObject(data);
        return (String) obj.get("url");
  }*/

  /**
   * 判断字符串编码
   * @param source:待验证的字符串
   * @return 字符串的编码方式
   * */
  public static String getEncoding(String source) {
    String charset = "unknown";
    
    String[] chars = {"ISO-8859-1", "GB2312", "GBK", "UTF-8"};
    
    for (String cs : chars) {
      try {
        if (source.equals(new String(source.getBytes(cs), cs)) ) {
          logger.debug("\n\n------------------------\n"+source+"\n----------------------\n\n");
          charset = cs;
          break ;
        }
      } catch(Exception e) {}
    }
    
    return charset;
  }
  
  /**
   * 生成token
   * @return
   */
  public static String makeToken(){  //checkException
    String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
    //数据指纹   128位长   16个字节  md5
    try {
      MessageDigest md = MessageDigest.getInstance("md5");
      byte md5[] =  md.digest(token.getBytes());
        //base64编码--任意二进制编码明文字符   adfsdfsdfsf
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(md5);
    } catch (NoSuchAlgorithmException e) {
                 throw new RuntimeException(e);
    }
  }
  
  
  
  public static void main(String[] args) throws Exception {
    
    String s = "<img src=\"www.baidu.com\" />" +
        "<div>我问" +
        "<a href=\"www.google.com\">google</a>" +
        "<span style=\"color:#aed4f5\">我们只放四天</span>" +
        "</div><img src=\"www.yahoo.com\"></img>" +
        "<div style=\"color:#ff00cc\"><img src=\"www.oschina.com\"/></div>" +
        "<a href=\"www.csdn.com\">草泥马</a>";
    
    String title="什么";
    
    String firstNode = doArticleSeo(s, title);
    
    System.out.println(firstNode);
    
    title="那你";
    
    System.out.println(doArticleSeo(firstNode, title));
    
//    URL url = new URL("http://www.baidu.com");
//    
//    InputStream in = url.openStream();
//    
//    BufferedInputStream bis = new BufferedInputStream(in);
//    
//    byte[] buffer = new byte[2048];
//    
//    String content = "";
//    while (-1 != bis.read(buffer)) {
//      content += new String(buffer);
//    }
//    
//    content = content.replace("\"", "\\\"");
//    
//    String nContent = replaceOrAndTagAttribute(content, "link", "alt", "ZCW");
//    
//    System.out.println(nContent);
//    
//    System.out.println("-------------------------------------------------");
//    
//    String realReg = "<\\s*(link)\\s*([^>]*)>";
//    
//    String reg = "<\\s*(link)[^<>]*?\\s*>";
//    
//    Matcher m = Pattern.compile(reg).matcher(nContent);
//    
//    int num = 0;
//    while (m.find()) {
//      num ++;
//      System.out.println(num + ":" +m.group());
//    }
//    
//    System.out.println("-------------------------------------------------");
//    
//    List<String> linkValue = getTagAttrbuteValue(nContent, "link", "alt");
//    
//    int i = 0;
//    for (String s : linkValue) {
//      i ++;
//      System.out.println(i + ":" +s);
//    }
//    
//    String nContent = content.replace("\"", "\\\"");
//    
//    System.out.println(content);
//
//    System.out.println("-----------------------------");
//    
//    System.out.println(nContent);
    
//    String im = "<img src=\"xxxxx\"/>";
//    
//    System.out.println(insertAttrbuteValueToNode(im, "title", "找你妹"));
//    
//    String href = "<a href=\"www.baidu.com\">百度一下你就知道</a>";
//    href = href + href + href;
//    
//    String nHref = insertTitleToATag(href, "百度一下");
//    
//    System.out.println(nHref);
//    
//    String added = insertAttrbuteValueToNode(nHref, "alt", "试一试");
//    
//    System.out.println(added);
    
    
//    String href = "<a href=\"www.baidu.com\">百度一下你就知道</a>";
//    
//    String a = insertTitleToATag(href, "百度一下");
//
//    System.out.println(a);
//    
//    String img = "<img src=\"xxx\"/>";
////    String img = "<img src=\"xxx\"></img>";
//    
//    String alt = "试一试";
//    String title = "再试一试";
//    
//    String nImg = insertTitleToImgTag(img, alt, title);
//    
//    nImg = nImg + a + nImg + a + nImg;
//    
//    System.out.println(nImg);
//    
//    String source = replaceTagAttribute(nImg, "img", "title", "新的title");
//    
//    System.out.println(source);
//    
//    System.out.println("--------------------------------------");
//    
//    List<String> imgList = getImgSrcListFromHtml(source);
//    
//    for (String s : imgList) {
//      System.out.println(s);
//    }
//    
//    System.out.println("----------------a--------------------");
//    List<String> aList = getTagAttrbuteValue(source, "a", "title");
//    
//    for (String s : aList) {
//      System.out.println(s);
//    }
  }
}
