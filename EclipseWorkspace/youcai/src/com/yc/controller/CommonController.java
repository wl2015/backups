package com.yc.controller;


import java.util.HashMap;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.yc.util.Constants;
import com.yc.util.CryptogramUtil;


/**
 * 
 * @author Paul Iverson
 *
 */
@Controller
public class CommonController {

	private static final Logger log = Logger.getLogger(CommonController.class);
	
	/*@w
	public CommonService commonService;*/
	
	
	/***
	 * 获取aes_key
	 * */
	@RequestMapping("/code")
	@ResponseBody
	public Map<String, Object> getRandomEncrptCode(HttpServletRequest req) throws Exception {
		Map<String, Object> codeMap = new HashMap<String, Object>();
		
		String ramdomKey = Constants.ENCRYPT_INIT_KEY;
		if (req.getSession().getAttribute(Constants.ENCRYPT_CODE) == null) {
			ramdomKey = CryptogramUtil.getRandomKey(); // 随机产生,加密存入session
			log.debug("\n\n\n生成的没有加密的加密秘钥是:" + ramdomKey + "\n\n\n");
			ramdomKey = CryptogramUtil.encrypt(ramdomKey, Constants.ENCRYPT_INIT_KEY, Constants.ENCRYPT_INIT_KEY);
			req.getSession().setAttribute(Constants.ENCRYPT_CODE, ramdomKey);
			log.debug("\n\n\n加密秘钥是:" + ramdomKey + "\n\n\n");
		} else {
			ramdomKey = (String) req.getSession().getAttribute(Constants.ENCRYPT_CODE);
		}
		
		codeMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
		codeMap.put("msg", ramdomKey);
		
		return codeMap;
	}
	
	/**
	 * 生成二维码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping("getQrcode")
	public void getQrcode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String url = request.getParameter("url");
		log.debug("\n\n\n----url: "+url);
		String text = "http://weibo.com/2643127210";   
        int width = 100;   
        int height = 100;     
        Map<EncodeHintType, String> hints= new Hashtable<EncodeHintType, String>();   
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        OutputStream outStream = response.getOutputStream();
        ImageIO.write(image, "JPEG", outStream);
		outStream.flush();
	}
*/
	/**
	 * 获取二维码地址
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("getQrcodeAddr")
	@ResponseBody
	public String getQrcodeAddr(HttpServletRequest request) throws Exception{
		String uploadUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/wedding321_uploads/upload_json.jsp";
		String string = SystemUtil.getQrcode(uploadUrl, "");
		log.debug("\n\n\n--- address: " + string);
		return string;
	}*/
	
	/**
	 * 管理员admin跳转到发送短信界面
	 * */
	/*@RequestMapping("admin/intoSendMessagePage")
	public String intoSendMessagePage(HttpServletRequest request,@RequestParam("phoneNum") String phoneNum){
		request.setAttribute("phoneNum", phoneNum);
		return "admin/sendMessage";
	}*/
	
	/**
	 * 管理员admin给用户发短信
	 * */
	/*@RequestMapping("admin/sendmessage")
	public String sendMessage(HttpServletRequest request,@RequestParam("phoneNum") String phoneNum,@RequestParam("message") String message){
		try {
			commonService.doSendPhoneMessage(phoneNum, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}*/
}
