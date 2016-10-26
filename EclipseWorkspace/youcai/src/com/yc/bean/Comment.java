package com.yc.bean;

/**
 * 短语评价实体类
 * @author Administrator
 *
 */
public class Comment {
  
    private  int commentId;
    private  String commentIntro;
    private  int commentType;
    
    public int getCommentId() {
        return commentId;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public String getCommentIntro() {
        return commentIntro;
    }
    public void setCommentIntro(String commentIntro) {
        this.commentIntro = commentIntro;
    }
    public int getCommentType() {
        return commentType;
    }
    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }
    
   
   
    
   
}
