package com.WeChat.entity;

import java.sql.Date;



public class CaculateDay {
		 public int cal(Date date){
			int month=date.getMonth();
			int year=date.getYear();
			int day=date.getDate();
		 int sum=0;
		 for(int i=1;i<month;i++){
		 switch(i){
		 case 1:
		 case 3:
		 case 5:
		 case 7:
		 case 8:
		 case 10:
		 case 12:sum+=31; break;
		 case 4:
		 case 6:
		 case 9:
		 case 11:sum+=30; break;
		 case 2: if(((year%4==0) & (year%100!=0)) | (year%

		400==0))sum+=29; else sum+=28;
		 }
		 }
		 return sum=sum+day;
		 }
		/*public static void main(String args[]){
			System.out.println(new CaculateDay().cal(new Date(2004,4,21)));
		}*/
		
}
