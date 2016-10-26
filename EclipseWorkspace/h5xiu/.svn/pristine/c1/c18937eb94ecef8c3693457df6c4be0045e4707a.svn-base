package com.h5.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h5.entity.Product;
import com.h5.service.ProductService;

@Controller
public class TestController {
	
	@Inject
	private ProductService productService;
	
	@RequestMapping("music")
	public String upMusic(){
		return "test/music";
	}
	
	@RequestMapping("/intoUserCenter")
	public String intoUserCenter(){
		return "test/html5";
	}
	
	@RequestMapping("data")
	public String data(){
		return "test/data";
	}
	
	@RequestMapping("getData")
	@ResponseBody
	public Map<String, Object> getContent(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Product product = productService.doPreviewProduct("2");
		resultMap.put("data", product.getContent());
		return resultMap;
	}
	
}
