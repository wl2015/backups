package com.changhong.zw.jdbc.entity;

/**
 * Created by admin on 16-7-27.
 */
public class PeopleEntity {
    Long peopleId;//id
    String name;//姓名
    int sex;//性别

    public Long getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Long peopleId) {
        this.peopleId = peopleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
