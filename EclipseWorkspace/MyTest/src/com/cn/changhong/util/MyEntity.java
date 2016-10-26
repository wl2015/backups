package com.cn.changhong.util;

public class MyEntity {
  public void run(Object o){
    System.out.println("Object");
  }
  public void run(String s){
    System.out.println("String");
  }
  public void action(){
    run(null);
  }
  
  public static void main(String[] args) {
    MyEntity entity = new MyEntity();
    entity.run(null);
  }
}
