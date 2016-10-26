package com.cn.changhong;

/**
 * Created by admin on 16-10-25.
 */
public class PageBean {
    int pageNum;
    int num;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageNum(String pageNum){
      if(pageNum == null){
        pageNum = "1";
    }
        this.pageNum = Integer.valueOf(pageNum);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
