package com.yc.bean;

/**
 * 驳回审核理由
 * @author Administrator
 *
 */
public class RejectReason {
    
    private int rejectId;
    private int merchantId;
    private String content;
    private String time;
    public int getRejectId() {
        return rejectId;
    }
    public void setRejectId(int rejectId) {
        this.rejectId = rejectId;
    }
    public int getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    
    

}
