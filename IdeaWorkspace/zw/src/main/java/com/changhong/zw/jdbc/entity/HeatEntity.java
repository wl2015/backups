package com.changhong.zw.jdbc.entity;

/**
 * Created by admin on 16-7-27.
 * 热力图要使用的实体类
 */
public class HeatEntity {
    Long heatId;//id
    String name;//名称，这里是地区名称
    int heat;//值

    public Long getHeatId() {
        return heatId;
    }

    public void setHeatId(Long heatId) {
        this.heatId = heatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }
}
