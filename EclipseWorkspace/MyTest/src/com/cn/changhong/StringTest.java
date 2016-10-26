package com.cn.changhong;

import net.sf.json.JSONObject;

public class StringTest {
	public static void main(String[] args) {
		String aa = "200";
		if(aa.equals("200")){
			System.out.println("aaaa");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("aaa", "a");
		jsonObject.put("bb", 200);
		if(jsonObject.get("bb").equals(200)){
			System.out.println("aaaa");
		}
	}
}
