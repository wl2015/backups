package com.yc.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.yc.bean.Comment;
import com.yc.service.AdminService;
import com.yc.service.CommentService;
import com.yc.util.ResultCode;


/**
 *
 *短语评价管理控制层 
 * @author 
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;
  
  @Autowired
  private AdminService adminService;

//初始化Log4j的一个实例，让这个实例在以后的打印中，题头都带上这个类名
  Logger log=Logger.getLogger(CommentController.class);
  
  
  /**
   * 新增评论界面
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/addComment")
  public String addComment(HttpServletRequest request) throws Exception{
      return "admin/addComment";
  }
  
  /**
   * 好评管理界面
   * @param request
   * @return
   */
  @RequestMapping("/goodShow")
  public String goodComment(HttpServletRequest request) throws Exception{
      
      List<Comment> commentList = commentService.showAllGoodComments();

      System.out.println(commentList);
      
      request.getSession().setAttribute("commentList", commentList);

      return "admin/goodComment";
  }

  /**
   * 差评管理界面
   * @param request
   * @return
   */
  @RequestMapping("/badShow")
  public String badComment(HttpServletRequest request) throws Exception{
      
      List<Comment> commentList = commentService.showAllBadComments();

      System.out.println(commentList);
      
      request.getSession().setAttribute("commentList", commentList);

      return "admin/badComment";
  }
  
  /**
   * 新增评论(comment表插入数据；commentShow表批量插入数据)
   * @param request
   * @return
   */
  @RequestMapping("/doAddComment")
  @ResponseBody
  public Map<String,Object> doAddComment(HttpServletRequest request) 
          throws Exception{

    Map<String,Object> resultMap = new HashMap<String,Object>();
      
    String commentIntro = request.getParameter("commentIntro");
    int commentType = Integer.parseInt(request.getParameter("commentType"));
    
    Comment comment = new Comment();
    comment.setCommentIntro(commentIntro);
    comment.setCommentType(commentType);
    
    commentService.doAddComment(comment);
    
    System.out.println("返回的新评论内容主键为："+comment.getCommentId());
    
    List<Integer> merIdList = adminService.queryAllMerchantsId();//查找所有商家id，存入List
    
    Map<String, Object> commentShowList = new HashMap<String, Object>();//将商家idList 和 新评论内容主键存入Map，好进行MyBatis操作
  
    commentShowList.put("merIdList", merIdList);  
    commentShowList.put("commentId", comment.getCommentId());

    commentService.addCommentShow(commentShowList);

    return resultMap;
  }
  
  
  /**
   * 修改评论
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/updateComment")
  @ResponseBody
  public Map<String, Object> updateComment(HttpServletRequest request) 
          throws Exception{
      System.out.println("这里是修改评论控制层！！！");
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      Comment comment = new Comment();
      int id = Integer.parseInt(request.getParameter("id"));
      String intro = request.getParameter("intro");

      System.out.println("评论编号："+id+"内容："+intro);
     
      comment.setCommentId(id);
      comment.setCommentIntro(intro);

      commentService.updateComment(comment);

      resultMap.put("data", ResultCode.SUCCESS);
    
    return resultMap;
  }
  
  
  /**
   * 删除评论
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/deleteComment")
  @ResponseBody
  public Map<String, Object> deleteComment(HttpServletRequest request) 
          throws Exception{
     
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      int commentId = Integer.parseInt(request.getParameter("id"));

      System.out.println("评论编号："+commentId);

      commentService.deleteComment(commentId);
      commentService.deleteCommentShow(commentId);

      resultMap.put("data", ResultCode.SUCCESS);
    
    return resultMap;
  }
  

}
