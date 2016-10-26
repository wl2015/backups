package com.yc.service.impl;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yc.service.CommonService;



@Service
public class CommonServiceImpl implements CommonService{

  private static final Logger log = Logger.getLogger(CommonServiceImpl.class);
  
  private static final String postUri = "http://gbk.sms.webchinese.cn";
  private static final String MessageUserName = "youcai";//短信平台用户名
  private static final String KEY = "dfef7875e71cf0dc766b";//短信平台秘钥
  
  /**
   * 发送验证短信
   * 返回1表示成功
   * 返回-1表示电话号码错误，-11表示为非法用户，-4手机号格式不正确;需要告知用户
   * -3表示短信不足，-6IP限制，需要通知管理员
   * -2接口密钥不正确，不是账户登陆密码,-21MD5接口密钥加密不正确,-14 短信内容出现非法字符,-41手机号码为空,-42短信内容为空,-51短信签名格式不正确,接口签名格式为：【签名内容】，可能数据被篡改需要重发
   * */
  
  public String doSendPhoneCheckCode(String phoneNum, int code) throws Exception {
    phoneNum = phoneNum.replaceAll("-", "");
    String result = null;
    int sendSuccess = 1;
    int[] phoneNumErrors = {-1, -11, -4};
    int[] systemErrors = {-3, -6};
    int[] codeErrors = {-2, -21, -14, -41, -42, -51};
    HttpClient client = new HttpClient();
    PostMethod post = new PostMethod(postUri); 
    post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码:不会
    NameValuePair[] data ={ new NameValuePair("Uid", MessageUserName),
        new NameValuePair("Key", KEY),
        new NameValuePair("smsMob",phoneNum),
        new NameValuePair("smsText", "有菜平台验证码:"+code+",请您及时完成验证。如非本人操作请忽略本短信。")};
    post.setRequestBody(data);
    
    client.executeMethod(post);
    Header[] headers = post.getResponseHeaders();
    int statusCode = post.getStatusCode();
    log.error("\n\nstatusCode:"+statusCode+"\n\n");
    for(Header h : headers){
      log.error(h.toString()+"......getResponseHeaders\n\n");
    }
    
    String sendResult = new String(post.getResponseBodyAsString().getBytes("gbk"));
    post.releaseConnection();
    log.error(sendResult+"..............sendResult\n\n");
    for (int co : codeErrors) {
      if (co == statusCode) {
        log.error("发送短信验证码时出错[statusCode:" + statusCode + "][返回数据:" + sendResult + "][可能原因：数据被黑客篡改][电话号码:" + phoneNum +"]");
        result = "请重新尝试";
        return result;
      }
    }
    for (int pe : phoneNumErrors) {
      if (pe == statusCode) {
        log.error("发送短信验证码时出错[statusCode:" + statusCode + "][返回数据:" + sendResult + "][可能原因：电话号码有误][电话号码:" + phoneNum +"]");
        result = "电话号码号码有误";
        return result;
      }
    }
    for (int se : systemErrors) {
      if (se == statusCode) {
        log.error("发送短信验证码时出错[statusCode:" + statusCode + "][返回数据:" + sendResult + "][可能原因：ip被屏蔽或者没短信了][电话号码:" + phoneNum +"]");
        result = "业务繁忙请稍后再试";
        return result;
      }
    }
    if (sendSuccess == statusCode) {
      log.info("成功发送短信验证码[statusCode:" + statusCode + "][返回数据:" + sendResult + "][验证码:"+ code + "][电话号码:" + phoneNum +"]");
      result = "验证码已经发送,请注意查收";
      return result;
    }
    
    return result;
  }
  
  
  /**
   * /**
   * 发送自定义短信
   * @param phoneNum
   * @param RejectReason
   * @return
   * @throws Exception
   */

  public String doSendPhoneForRejectReason(String phoneNum, String RejectReason) throws Exception {
      phoneNum = phoneNum.replaceAll("-", "");
      String result = null;
      int sendSuccess = 1;
      int[] phoneNumErrors = {-1, -11, -4};
      int[] systemErrors = {-3, -6};
      int[] codeErrors = {-2, -21, -14, -41, -42, -51};
      HttpClient client = new HttpClient();
      PostMethod post = new PostMethod(postUri); 
      post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码:不会
      NameValuePair[] data ={ new NameValuePair("Uid", MessageUserName),
          new NameValuePair("Key", KEY),
          new NameValuePair("smsMob",phoneNum),
          new NameValuePair("smsText", "有菜平台已驳回审核:"+RejectReason+",请您重新申请。如非本人操作请忽略本短信。")};
      post.setRequestBody(data);
      
      client.executeMethod(post);
      Header[] headers = post.getResponseHeaders();
      int statusCode = post.getStatusCode();
      log.error("\n\nstatusCode:"+statusCode+"\n\n");
      for(Header h : headers){
        log.error(h.toString()+"......getResponseHeaders\n\n");
      }
      
      String sendResult = new String(post.getResponseBodyAsString().getBytes("gbk"));
      post.releaseConnection();
      log.error(sendResult+"..............sendResult\n\n");
      for (int co : codeErrors) {
        if (co == statusCode) {
          log.error("发送短信验证码时出错[statusCode:" + statusCode + "][返回数据:" + sendResult + "][可能原因：数据被黑客篡改][电话号码:" + phoneNum +"]");
          result = "请重新尝试";
          return result;
        }
      }
      for (int pe : phoneNumErrors) {
        if (pe == statusCode) {
          log.error("发送短信验证码时出错[statusCode:" + statusCode + "][返回数据:" + sendResult + "][可能原因：电话号码有误][电话号码:" + phoneNum +"]");
          result = "电话号码号码有误";
          return result;
        }
      }
      for (int se : systemErrors) {
        if (se == statusCode) {
          log.error("发送短信验证码时出错[statusCode:" + statusCode + "][返回数据:" + sendResult + "][可能原因：ip被屏蔽或者没短信了][电话号码:" + phoneNum +"]");
          result = "业务繁忙请稍后再试";
          return result;
        }
      }
      if (sendSuccess == statusCode) {
        log.info("成功发送短信验证码[statusCode:" + statusCode + "][返回数据:" + sendResult + "][理由:"+ RejectReason + "][电话号码:" + phoneNum +"]");
        result = "验证码已经发送,请注意查收";
        return result;
      }
      
      return result;
    }
}
