package com.h5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h5.entity.User;
import com.h5.service.DemoService;
import com.h5.util.SystemUtil;

/**
 * Demo控制层
 * @author Paul Iverson
 *
 */
@Controller
public class DemoController {

	@Inject
	public DemoService demoService;
	
	private static final Logger log = Logger.getLogger(DemoController.class);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	@RequestMapping(value="/demo")
	@ResponseBody
	public Map<String, Object> getUserList(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> list = demoService.doGetUserList();
		log.debug("\n\n\n\n------size: "+list.size());
		resultMap.put("code", "success");
		resultMap.put("data", list);
		return resultMap;
	}
	
	@RequestMapping("toDemo")
	public String demo(HttpServletRequest request, ModelMap map){
		log.debug("\n\n\n\n------msg: "+"Welcome to Demo!");
		List<User> list = demoService.doGetUserList();
		log.debug("\n\n\n\n------size: "+list.get(0).getUserName());
		map.put("left", 10);
		map.put("height", 100);
		map.put("width", 300);
		map.put("msg", "Nice to meet you!");
		map.put("code", "success");
		map.put("data", list);
		return "demo";
	}	
}
