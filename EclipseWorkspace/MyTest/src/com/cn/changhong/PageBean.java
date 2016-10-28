package com.cn.changhong;

/**
 * Created by admin on 16-10-25.
 */
public class PageBean {
    String pageNum;
    String num;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    public void setPageBean(String pageNum, String num){
    	this.pageNum = pageNum;
    	this.num = num;
    }
}
