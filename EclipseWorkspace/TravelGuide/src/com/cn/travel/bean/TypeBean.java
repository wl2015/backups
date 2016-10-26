package com.cn.travel.bean;

/***
 * 景点的标签
 * */
public class TypeBean {
  public int typeId;//景点类型Id
  
  public String typeName;//类型名

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
