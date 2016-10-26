package com.cn.changhong.jdbc.entity;

import java.util.Date;

/**
 * Created by admin on 16-7-27.
 * 积分实体类
 */
public class IntegralEntity {
    Long integralId;//积分表ID
    int add;//积分收入
    int reduce;//积分支出
    int actual;//实际收支
    Date time;//修改或创建时间

    public Long getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Long integralId) {
        this.integralId = integralId;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }

    public int getReduce() {
        return reduce;
    }

    public void setReduce(int reduce) {
        this.reduce = reduce;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
