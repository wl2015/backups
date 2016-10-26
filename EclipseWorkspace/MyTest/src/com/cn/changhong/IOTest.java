package com.cn.changhong;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class IOTest {
  public static void main(String[] args) {
    try {
      DataInputStream inputStream = new DataInputStream(
          new BufferedInputStream(new FileInputStream("d:/text.txt")));
      System.out.println(inputStream.readLine());
//      DataOutputStream outputStream = new DataOutputStream(
//          new BufferedOutputStream(new FileOutputStream("d:/text.txt")));
//      Set<String> set = new HashSet<String>();
//      set.add("aaaaaa");
//      set.add("wla");
//      set.add("wla");
//      set.add("bbb");
//      Object[] aa = set.toArray();
//      String ss = "aaa";
//      outputStream.write(ss.getBytes());
//      FileWriter fw=new FileWriter("d:/text.txt");
//      fw.write("www\r\n");
//      fw.write("aaa");
//      fw.flush();
//      fw.close();
    } catch (Exception e) {
      System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
