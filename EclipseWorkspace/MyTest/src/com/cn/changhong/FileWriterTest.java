package com.cn.changhong;

import java.io.File;
import java.io.FileWriter;

public class FileWriterTest {
  public static void main(String[] args) {
    File file = new File("d:/text.txt");
    try {
      FileWriter fileWriter = new FileWriter(file);
      System.out.println("第一次写入");
      fileWriter.write("第一次测试哈哈哈");
      System.out.println("第一次写入完成");
      System.out.println("第二次写入");
      fileWriter.append("\r\n第二次测试哈哈哈");
      System.out.println("第二次写入完成");
      fileWriter.close();
    } catch (Exception e) {
      System.out.println("异常！！！！！！！！");
    }
  }
}
