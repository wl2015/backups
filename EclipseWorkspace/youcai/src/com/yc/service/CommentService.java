package com.yc.service;

import java.util.List;
import java.util.Map;

import com.yc.bean.Comment;


/**
 * baseAdminService
 * @author
 *
 */
public interface CommentService {
  
  //修改评论短语
    public void updateComment(Comment comment) throws Exception;  
    
   //查询所有评论
    public  List<Comment> showAllComments() throws Exception;
    //查询好的评论
    public  List<Comment> showAllGoodComments() throws Exception;
    //查询坏的评论
    public  List<Comment> showAllBadComments() throws Exception;
    
    //新增评论
    public void doAddComment(Comment comment) throws Exception;
    //新增的评论添加到commentShow
    public void addCommentShow(Map<String, Object> commentShowList) throws Exception;
    
    //根据c_id删除评论comment
    public void deleteComment(int commentId) throws Exception;
    //根据c_id删除评论commentShow
    public void deleteCommentShow(int commentId) throws Exception;

}
