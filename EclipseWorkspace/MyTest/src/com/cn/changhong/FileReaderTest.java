package com.cn.changhong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReaderTest {
  public static void main(String[] args) {
    File file = new File("d:/text.txt");
    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String ss;
      while((ss = bufferedReader.readLine())!=null){
        System.out.println(ss);
      }
    } catch (Exception e) {
      System.out.println("异常！！！！！！");
    }
  }
}
