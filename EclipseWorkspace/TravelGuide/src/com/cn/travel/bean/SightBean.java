package com.cn.travel.bean;

import java.util.List;

public class SightBean {
  public int sightId;//景点Id
  
  public String sightName;//景点名
  
  public Double sightLng;//百度地图横坐标
  
  public Double sightLat;//百度地图纵坐标
  
  public String address;//详细地址
  
  public String sightIntro;//景点介绍
  
  public String district;//该景点所属区域
  
  public List<TypeBean> typeBeans;//标签列表
  public int getSightId() {
    return sightId;
  }

  public void setSightId(int sightId) {
    this.sightId = sightId;
  }

  public String getSightName() {
    return sightName;
  }

  public void setSightName(String sightName) {
    this.sightName = sightName;
  }

  public Double getSightLng() {
    return sightLng;
  }

  public void setSightLng(Double sightLng) {
    this.sightLng = sightLng;
  }

  public Double getSightLat() {
    return sightLat;
  }

  public void setSightLat(Double sightLat) {
    this.sightLat = sightLat;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSightIntro() {
    return sightIntro;
  }

  public void setSightIntro(String sightIntro) {
    this.sightIntro = sightIntro;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public List<TypeBean> getTypeBeans() {
    return typeBeans;
  }

  public void setTypeBeans(List<TypeBean> typeBeans) {
    this.typeBeans = typeBeans;
  }
}
