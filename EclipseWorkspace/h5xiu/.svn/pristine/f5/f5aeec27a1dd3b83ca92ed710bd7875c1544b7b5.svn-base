package com.h5.controller;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;





import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h5.entity.Admin;
import com.h5.entity.Recharge;
import com.h5.entity.Upgrade;
import com.h5.entity.Advertise;
import com.h5.entity.User;
import com.h5.service.AdminService;
import com.h5.util.Constants;
import com.h5.util.CryptogramUtil;
import com.h5.util.DateUtil;
import com.h5.util.SystemUtil;
import com.h5.util.ValidateUtil;




@Controller
public class AdminController {
	
	
	private static final Logger logger = Logger.getLogger(AdminController.class);

	
	@Inject
	public AdminService adminService;
	
	
	/**
	 * 测试显示照片页面
	 * @return
	 */
	@RequestMapping("/showPic")
	public String showPic(HttpServletRequest request) {
		return "test/index";
	}


	/**
	 * 进入管理员登录页面
	 * @return
	 */
	@RequestMapping("admin/login")
	public String intoAdLogin(HttpServletRequest request) {
		return "admin/adminLogin";
	}
	
	/**
	 * 管理员登录
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("admin/doLogin")
	public String doLogin(HttpServletRequest request, ModelMap map) throws Exception{
		
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		String phone = ValidateUtil.valiStrIsPhone(SystemUtil.removeAllNotCompleteTagFromHtml(CryptogramUtil.decrypt(request.getParameter("encryptPhone"), key)));
		String pwd   = ValidateUtil.valiStrIsEmpty(SystemUtil.removeAllNotCompleteTagFromHtml(CryptogramUtil.decrypt(request.getParameter("encryptPwd"), key)));
		Admin admin = adminService.doAdLogin(phone, pwd);
		
		if (null != admin){
			
			//return "admin/index";
			request.getSession().setAttribute(Constants.LOGINUSER_SESSION_NAME_ADMIN, admin);
			return "admin/adminCenter";
		}else{
			map.put("error", "电话和密码不一致");
			return "admin/adminLogin";
		}
	}
	
	
	

	/**
	 * 管理员admin获取用户user信息列表
	 * */
	@RequestMapping("admin/intoShowUserList")
	public String intoShowUserList(HttpServletRequest request){
		List<User> userList = adminService.doGetUserList();
		request.setAttribute("users", userList);
		return "admin/adminUserTest";
	}
	
	/**
	 * 管理员admin升级用户user为VIP
	 * */
	@RequestMapping("admin/upGrade")
	@ResponseBody
	public Map<String, Object>  adminUpGradeUserIntoVipByUserId(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Upgrade upgrade = new Upgrade();
		String userId = (String)request.getParameter("userId");
		
		//获取当前时间
		int createTime = (int)DateUtil.convertCurrentDTTMtoInt();
		//获取ip地址
		String createIp = SystemUtil.getRequestIp(request);
		
		upgrade.setUserId(userId);
		upgrade.setCreateTime(createTime);
		upgrade.setCreateIp(createIp);
		upgrade.setMethod(0);//(0表示管理员为用户升级，1表示用户自己升级)
		
		//记录升级VIP信息
		adminService.doRecordUpGradeVipInfo(upgrade);
		//升级用户为VIP
		adminService.doUpGradeUserIntoVipByUserId(userId);
		return resultMap;
	}

	/**
	 * 管理员admin为用户user充值
	 * */
	@RequestMapping("admin/charge")
	@ResponseBody
	public Map<String, Object> adminChargeForUser(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = (String)request.getParameter("userId");
		String denomination = (String)request.getParameter("denomination");
		Recharge recharge = new Recharge();
		
		//获取当前时间
		int createTime = (int)DateUtil.convertCurrentDTTMtoInt();
		//获取ip地址
		String createIp = SystemUtil.getRequestIp(request);
		
		recharge.setUserId(userId);
		recharge.setMoney(BigDecimal.valueOf(Double.parseDouble(denomination)));
		recharge.setCreateTime(createTime);
		recharge.setCreateIp(createIp);
		recharge.setMethod(0);//(0表示管理员为用户充值，1表示用户自己充值)
		
		//记录充值信息
		adminService.doRecordChargeInfo(recharge);
		//根据用户ID为用户充值
		adminService.doChargeForUserByUserId(userId, denomination);
		return resultMap;
	}
	
	
	
	/**
	 * 管理员上传图片
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("admin/upAdvertise")
	@ResponseBody
	public Map<String, Object> upAdvertise(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		String img_url=SystemUtil.removeAllTagFromHtml(request.getParameter("s"));
		Advertise banner = new Advertise();
		banner.setImg_url(img_url);
		banner.setSequence(0);
		banner.setCreate_time(DateUtil.convertCurrentDTTMtoInt());
		//添加到数据库
		boolean upAdver=adminService.upAdvertise(banner);
		if(upAdver){		
			
			resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
			resultMap.put("msg", "头像修改成功");
			return resultMap;
		}
		resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		
		return resultMap;
	}
	
	/**
	 * 获取banner图片信息列表
	 * */
	@RequestMapping("admin/doShowPic")
	public String doShowPic(HttpServletRequest request){
		List<Advertise> adverList = adminService.showPic();
		request.setAttribute("advertises", adverList);
		return "test/index";
	}
	
	

}
