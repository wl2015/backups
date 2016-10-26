package com.cn.changhong;

public class Num2RMB {
	private String[] hanArr = {"壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	private String[] unitArr = {"十","百","千"};
	
	private String[] divide(Double num){
		//将一个浮点强制类型转换为long型，即得到它的整数部分
//		long zheng = ;
		Long zheng = num.longValue();
		return new String[]{};
	}
	public static void main(String[] args) {
		Double num = 289318723.0;
		System.out.println(num.longValue());
		Long zheng = num.longValue();
		System.out.println(zheng);
		System.out.println(num.floatValue());
		System.out.println(num.byteValue());
	}
}
