package com.cn.changhong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyEchart {
  
  List<Object> list;

  public List<Object> getList() {
    return list;
  }

  public void setList(List<Object> list) {
    this.list = list;
  }

  public void addMap(String name, String value){
	  if(this.list == null){
		  this.list = new ArrayList<>();
	  }
	  Map<String, Object> map = new HashMap<>();
	  map.put("name", name);
	  map.put("value", value);
	  this.list.add(map);
  }
  
}
