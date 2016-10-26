package com.cn.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="sight")
@DynamicInsert
@DynamicUpdate
public class SightEntity {
  @Id
  @GeneratedValue
  @Column(name="sight_id")
  public int sightId;//景点Id
  
  @Column(name="sight_name")
  public String sightName;//景点名
  
  @Column(name="sight_lng")
  public Double sightLng;//百度地图横坐标
  
  @Column(name="sight_lat")
  public Double sightLat;//百度地图纵坐标
  
  @Column(name="address")
  public String address;//详细地址
  
  @Column(name="sight_intro")
  public String sightIntro;//景点介绍
  
  @Column(name="district")
  public String district;//该景点所属区域

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
  
}
