package com.yc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.Comment;
import com.yc.dao.CommentDAO;

import com.yc.service.CommentService;


/**
 * 菜品管理service 实现类
 * @author 
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentDAO commentDao;




//修改评论短语
  public void updateComment(Comment comment) throws Exception{
      commentDao.updateComment(comment);
  }  
  
  
  //查询所有评论
  public  List<Comment> showAllComments() throws Exception{
      return commentDao.showAllComments();
  }
  //查询好的评论
  public  List<Comment> showAllGoodComments() throws Exception{
      return commentDao.showAllGoodComments();
  }
  //查询坏的评论
  public  List<Comment> showAllBadComments() throws Exception{
      return commentDao.showAllBadComments();
  }
  
  //新增评论
  public void doAddComment(Comment comment) throws Exception{
      commentDao.doAddComment(comment);
  }
  //新增的评论添加到commentShow
  public void addCommentShow(Map<String, Object> commentShowList) throws Exception{
  
      commentDao.addCommentShow(commentShowList);
  }
  
  //根据c_id删除评论comment
  public void deleteComment(int commentId) throws Exception{
      commentDao.deleteComment(commentId);
  }
  //根据c_id删除评论commentShow
  public void deleteCommentShow(int commentId) throws Exception{
      commentDao.deleteCommentShow(commentId);
  }

}
