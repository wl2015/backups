package com.yc.controller;



import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yc.alipay.config.AlipayConfig;
import com.yc.alipay.util.AlipayNotify;
import com.yc.alipay.util.AlipaySubmit;

import com.yc.service.UserService;
import com.yc.util.StringUtil;




/**
 * 支付宝Controller
 * @author cjy
 *
 */
@Controller
@RequestMapping(value="/aliapi")
public class AlipayApiController {
 
    private static Log log = LogFactory.getLog(AlipayApiController.class);

        
    @Autowired
    private UserService userService;
     
    @RequestMapping(value="/index")
    public String index(HttpServletRequest request,
            HttpServletResponse response){
                return "alipay/index";//付款的页面。本页面是为了测试而使用的
    }
    
    /**
     * 跳到支付的网站进行数据处理
     * @author wang lei
     * */
    @RequestMapping(value = "/alipayapi", method = RequestMethod.POST,produces = "application/json")  
    public String alipayapi(HttpServletRequest request,HttpServletResponse response,Model model) 
            throws Exception { 
        //得到是否分单的状态
        String orderStatePay = new String(request.getParameter("orderState"));
        System.out.println("orderStatePay="+orderStatePay);
        String result = "";
      //支付类型
        String payment_type = "1";
        //必填，不能修改
        //服务器异步通知页面路径
        String notify_url = "http://www.jialiyoucai.com/youcai/aliapi/notify_url.do";
        //需http://格式的完整路径，不能加?id=123这类自定义参数

        //页面跳转同步通知页面路径
        String return_url = "";
        if (orderStatePay.equals("1")) {//如果是分单下的第一单（要跳转到“可支付下一单界面”）
             return_url = "http://www.jialiyoucai.com/youcai/aliapi/payForOrderOne_return.do";
        } else {//如果不分单或分单的第二单（跳转到“返回主页界面”）
             return_url = "http://www.jialiyoucai.com/youcai/aliapi/payForOrder_return.do";
        }
        
        //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

        //商户订单号
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //商户网站订单系统中唯一订单号，必填

        //订单名称String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        String subject = new String("有菜外卖订单");
        //必填

        //付款金额String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
        String total_fee = new String("0.01");
        //必填

        //商品展示地址String show_url = new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
        String show_url = new String("http://www.jialiyoucai.com/youcai/");
        //必填，需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

        //订单描述
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        //选填

        //超时时间String it_b_pay = new String(request.getParameter("WIDit_b_pay").getBytes("ISO-8859-1"),"UTF-8");
        String it_b_pay = new String("5");
        //选填

        //钱包tokenString extern_token = new String(request.getParameter("WIDextern_token").getBytes("ISO-8859-1"),"UTF-8");
        String extern_token = new String("money");
        //选填
        
        //////////////////////////////////////////////////////////////////////////////////
        
        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
            sParaTemp.put("partner", AlipayConfig.partner);
            sParaTemp.put("seller_id", AlipayConfig.seller_id);
            sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("body", body);
        sParaTemp.put("it_b_pay", it_b_pay);
        sParaTemp.put("extern_token", extern_token);

          
        //建立请求
        try {
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认"); 
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String s = gson.toJson(sHtmlText);
            model.addAttribute("sHtmlText", s);
            request.setAttribute("sHtmlText", s);
            result = "{\"success\":true,\"msg\":\"跳转成功\"}";

            StringUtil.writeToWeb(sHtmlText, "html", response);
            
            return null;
        } catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("ddddddddddddddddddddd");
            }
            result = "{\"success\":false,\"msg\":\"跳转失败，请稍候再试！\"}";
            StringUtil.writeToWeb(result, "html", response);
            return null;
        }
    }  
    /**
     * 同步通知的页面的Controller
     * 不分单和第二单的情况（于分单的第一单情况区别仅仅是跳转页面不同而已）
     * @author cjy
     */
    @RequestMapping(value="/payForOrder_return")
    public String payForOrder_return(HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
          String name = (String) iter.next();
          String[] values = (String[]) requestParams.get(name);
          String valueStr = "";
          for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                : valueStr + values[i] + ",";
          }
          //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
          valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
          params.put(name, valueStr);
        }
      
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        
        if(verify_result){//验证成功
          //////////////////////////////////////////////////////////////////////////////////////////
          //请在这里加上商户的业务逻辑程序代码
            System.out.println("仅仅是验证成功，没有到付款成功");
          //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
          if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
            //判断该笔订单是否在商户网站中已经做过处理
              //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
              //如果有做过处理，不执行商户的业务程序
            //更改订单状态
              System.out.println("执行了return_url！！！out_trade_no="+out_trade_no);
              int result = userService.payForOrder(Integer.parseInt(out_trade_no));
              System.out.println("result+"+result);

          }
          
          //该页面可做页面美工编辑
          
          //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

          //////////////////////////////////////////////////////////////////////////////////////////
        }else{
          //该页面可做页面美工编辑
         
        }

      
        return "user/resultForAlipay";
    }
    
    /**
     * 同步通知的页面的Controller
     * 分单，第一单的情况
     * @author cjy
     */
    @RequestMapping(value="/payForOrderOne_return")
    public String payForOrderOne_return(HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        
      //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
          String name = (String) iter.next();
          String[] values = (String[]) requestParams.get(name);
          String valueStr = "";
          for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                : valueStr + values[i] + ",";
          }
          //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
          valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
          params.put(name, valueStr);
        }
      
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        
        if(verify_result){//验证成功
          //////////////////////////////////////////////////////////////////////////////////////////
          //请在这里加上商户的业务逻辑程序代码
            System.out.println("仅仅是验证成功，没有到付款成功");
          //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
          if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
            //判断该笔订单是否在商户网站中已经做过处理
              //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
              //如果有做过处理，不执行商户的业务程序
            //更改订单状态
              System.out.println("执行了return_url！！！out_trade_no="+out_trade_no);
              int result = userService.payForOrder(Integer.parseInt(out_trade_no));
              System.out.println("result+"+result);
             
              //分单下，表示支付了第一个订单 
              request.getSession().setAttribute("payState", 1);
              System.out.println("商户返回第一单状态");
         
          }
          
          //该页面可做页面美工编辑
          
          //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

          //////////////////////////////////////////////////////////////////////////////////////////
        }else{
          //该页面可做页面美工编辑
         
        }
      
        return "user/resultForAlipay";
    }
    
    /**
     * 异步通知付款状态的Controller
     * @param request
     * @param response
     * @return
     * @author cjy
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/notify_url")
    public String async(HttpServletRequest request,HttpServletResponse response)
            throws Exception{
         Map<String,String> params = new HashMap<String,String>();  
            Map requestParams = request.getParameterMap();  
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {  
                String name = (String) iter.next();  
                String[] values = (String[]) requestParams.get(name);  
                String valueStr = "";  
                for (int i = 0; i < values.length; i++) {  
                    valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";  
                }  
                params.put(name, valueStr);  
            }  
            String tradeNo = request.getParameter("out_trade_no");  
            String tradeStatus = request.getParameter("trade_status");  
            //String notifyId = request.getParameter("notify_id");  
            if(AlipayNotify.verify(params)){//验证成功  
                if(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {  
                    //要写的逻辑。自己按自己的要求写
                    
                    /*//更改订单状态
                    int result = userService.payForOrder(Integer.parseInt(tradeNo));
                    
                    if(result != 0){
                       request.getSession().setAttribute("payState", 1);
                       System.out.println("第一单状态");
                    }*/
                    log.error("ok.......");
                    System.out.println(">>>>>充值成功" + tradeNo);  
                    
                }  
                return "user/resultForAlipay";  
            }else{//验证失败  
                return "alipay/notify_url";  
            }  
         
    }
}