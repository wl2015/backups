package com.cn.changhong;

public class MathTest {
	public static void main(String[] args) {
//		Math.ceil(); //只要有小数都+1
//		Math.floor(); //不取小数
//		Math.round();//四舍五入
		int a=18;
		int b=4;
		System.out.println(Math.ceil((double)a/b));
		System.out.println(Math.floor((double)a/b));
		System.out.println(Math.round((double)a/b));
		System.out.println((int)1.23);
		float s = 1213.23f;
		int c = Math.round(s/60);
		System.out.println(c);
	}
}
