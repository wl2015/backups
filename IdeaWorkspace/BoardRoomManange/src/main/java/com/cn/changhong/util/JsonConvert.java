package com.cn.changhong.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义json转换
 * 
 * @author laiwei
 * @date 2015年12月23日
 */
public class JsonConvert extends ObjectMapper {

	private static final long serialVersionUID = 5943215683865528778L;

	public JsonConvert() {
		setSerializationInclusion(Include.NON_NULL);
	}
}
