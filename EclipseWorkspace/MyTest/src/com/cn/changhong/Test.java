package com.cn.changhong;

public class Test {
  public static void main(String[] args) {
	  MyEchart echart = new MyEchart();
	  echart.addMap("a1", "aa");
	  echart.addMap("b2", "bb");
	  echart.addMap("c3", "cc");
	  for(int i=0; i< echart.getList().size();i++){
		  System.out.println(echart.getList().get(i));
	  }
  }
}

