package com.yc.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.basic.exceptions.CustomException;
import com.yc.bean.Comment;
import com.yc.bean.Dish;
import com.yc.bean.Limits;
import com.yc.bean.Merchant;
import com.yc.bean.RejectReason;
import com.yc.service.CommentService;
import com.yc.service.CommonService;
import com.yc.service.DishService;
import com.yc.service.ManageMerService;
import com.yc.service.impl.CommonServiceImpl;
import com.yc.util.Constants;
import com.yc.util.CryptogramUtil;
import com.yc.util.InfoMation;
import com.yc.util.Page;
import com.yc.util.ResultCode;
import com.yc.util.TimeUtil;
import com.yc.util.ValidateUtil;


/**
 *
 *管理商家控制层 
 * @author 
 *
 */
@Controller
@RequestMapping("/manageMer")
public class ManageMerController {

  @Autowired
  private ManageMerService manageMerService;
  
  @Autowired
  private DishService dishService;
  
  @Autowired
  private CommentService commentService;
  

  
  //初始化Log4j的一个实例，让这个实例在以后的打印中，题头都带上这个类名
  Logger log=Logger.getLogger(ManageMerController.class);

  //管理商家页面(分页)
  @RequestMapping("/show")
  public String show(ModelMap model,HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
    
      String pageNow = request.getParameter("pageNow");
      Page page = null;
      List<Merchant> merchantList = null;
      int totalCount = (int) manageMerService.getAllMerchantCount();
      if (pageNow != null) {
        page = new Page(totalCount, Integer.parseInt(pageNow));
        merchantList = manageMerService.getMerchants(page);

      } else {
        
        page = new Page(totalCount, 1);
        merchantList = manageMerService.getMerchants(page);
        
        if (merchantList == null || merchantList.size() < 1) {
          //跳转错误提示页面
            request.setAttribute("errorMsg", "对不起，暂时没有商家");
            request.getRequestDispatcher("/WEB-INF/pages/testError.jsp").forward(request, response);
        }
      }
      
      model.addAttribute("merchantList", merchantList);
      model.addAttribute("page", page);

    return "admin/merchantManage";
  }
  
  //审核商家页面（分页）
  @RequestMapping("/showVerify")
  public String showVerify(Model model,HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      
      String pageNow = request.getParameter("pageNow");
      Page page = null;
      List<Merchant> verifyMerchantList = null;
      int totalCount = (int) manageMerService.getAllVerifyMerchantCount();
      if (pageNow != null) {
          
        page = new Page(totalCount, Integer.parseInt(pageNow));
        verifyMerchantList = manageMerService.getVerifyMerchants(page);
      } else {
        
        page = new Page(totalCount, 1);
        verifyMerchantList = manageMerService.getVerifyMerchants(page);
        
        if (verifyMerchantList == null ||verifyMerchantList.size() < 1) {
            //跳转错误提示页面
            request.setAttribute("errorMsg", "对不起，暂时没有新注册商家");
            request.getRequestDispatcher("/WEB-INF/pages/testError.jsp").forward(request, response);
        }
      }
      
      model.addAttribute("verifyMerchantList", verifyMerchantList);
      model.addAttribute("page", page);
      
      return "admin/newMerchant";
  }
 
  
  /**
   * 查询商家
   * @param request
   * @return
   */
  @RequestMapping("/queryMerchants")
  @ResponseBody
  public Map<String, Object> query(HttpServletRequest request) 
          throws Exception{
     
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      List<Merchant> merchantList = manageMerService.queryMerchants();

      resultMap.put("merchantList", merchantList);
    
    return resultMap;
  }
  
  /**
   * 关键词查询商家(分页)
   */
  @RequestMapping(value="queryMerchantsByKeyWords")
  public String queryMerchantsByKeyWords(Model model,HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      
      Map<String,Object> map = new HashMap<String, Object>();
      
      String keywords = request.getParameter("keywords");
      
      if (keywords != null) {
          keywords = new String(keywords.getBytes("iso8859-1"),"utf-8");//将get请求参数转码，否则是乱码
          request.getSession().setAttribute("keywords", keywords);//是否合理？？？session会被覆盖吗？

          String pageNow = request.getParameter("pageNow");
          Page page = null;
          List<Merchant> merchantList = null;
          int totalCount = (int) manageMerService.getMerchantByKeywordsCount(keywords);//得到符合关键词查询的商家数量
          if (pageNow != null) {
            page = new Page(totalCount, Integer.parseInt(pageNow));
            map.put("page", page);
            map.put("keywords", keywords);
            merchantList = manageMerService.queryMerchantsByKeyWords(map);

          } else {
            
            page = new Page(totalCount, 1);
            map.put("page", page);
            map.put("keywords", keywords);
            merchantList = manageMerService.queryMerchantsByKeyWords(map);
            
            if (merchantList == null || merchantList.size() < 1) {
                //跳转错误提示页面
                request.setAttribute("errorMsg", "对不起，没有符合条件的商家");
                request.getRequestDispatcher("/WEB-INF/pages/testError.jsp").forward(request, response);
            }
          }
          
          model.addAttribute("merchantList", merchantList);
          model.addAttribute("page", page);
    } else {
        //跳转错误提示页面
        request.setAttribute("errorMsg", "关键词不能为空！");
        request.getRequestDispatcher("/WEB-INF/pages/testError.jsp").forward(request, response);
    }

      return "admin/merchantByKeywords";
  }

  
  /**
   * 获取单个商家信息
   * @param request
   * @return
   */
  @RequestMapping("/getMerchantById")
  public String getMerchantById(HttpServletRequest request,HttpServletResponse response) throws Exception{

    String id = request.getParameter("id");
    
    if (id != null) {
        int merchantId = Integer.parseInt(id);
        Merchant merchant = manageMerService.getMerchantById(merchantId);
        
      //将重要数据解密
        System.out.println("身份证号加密后："+merchant.getIdCard());
        System.out.println("银行卡加密后："+merchant.getBankCard());
        System.out.println("邮箱加密后："+merchant.getMerchantMail());

        try {
            String idCard = InfoMation.decryptBasedDes(merchant.getIdCard());
            String bankCard = InfoMation.decryptBasedDes(merchant.getBankCard());
            String mail = InfoMation.decryptBasedDes(merchant.getMerchantMail());
            
            System.out.println("身份证号解密后："+idCard);
            System.out.println("银行卡解密后："+bankCard);
            System.out.println("邮箱解密后："+mail);
            
            merchant.setIdCard(idCard);
            merchant.setBankCard(bankCard);
            merchant.setMerchantMail(mail);

            request.getSession().setAttribute("merchant", merchant);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("商家信息解密失败！");
        }

    } else {
        throw new CustomException("商家Id值为空！");
    }
    
    return "admin/merchantInfo";
  }
  
  
  /**
   * 获取单个未审核商家信息
   * @param request
   * @return
   */
  @RequestMapping("/getVerifyMerchantById")
  public String getVerifyMerchantById(Model model,HttpServletRequest request,HttpServletResponse response) 
          throws Exception{

      
    String id = request.getParameter("id");
    if (id != null) {
        int verifyMerchantId = Integer.parseInt(id);
        
        Merchant verifyMerchant = manageMerService.getMerchantById(verifyMerchantId);
        //将重要数据解密
        System.out.println("身份证号加密后："+verifyMerchant.getIdCard());
        System.out.println("银行卡加密后："+verifyMerchant.getBankCard());
        System.out.println("邮箱加密后："+verifyMerchant.getMerchantMail());

        try {
            String idCard = InfoMation.decryptBasedDes(verifyMerchant.getIdCard());
            String bankCard = InfoMation.decryptBasedDes(verifyMerchant.getBankCard());
            String mail = InfoMation.decryptBasedDes(verifyMerchant.getMerchantMail());
            
            System.out.println("身份证号解密后："+idCard);
            System.out.println("银行卡解密后："+bankCard);
            System.out.println("邮箱解密后："+mail);
            
            verifyMerchant.setIdCard(idCard);
            verifyMerchant.setBankCard(bankCard);
            verifyMerchant.setMerchantMail(mail);
            
            if (verifyMerchant.getMerchantStatus() == 2) {
                
                RejectReason rejectReason = manageMerService.getRejectReason(verifyMerchant.getMerchantId());    
                model.addAttribute("rejectReason", rejectReason);
            }
            
            model.addAttribute("verifyMerchant", verifyMerchant);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("未审核商家信息解密失败！");
            
        }

    } else {
        throw new CustomException("未审核商家Id值为空！");
    }

    return "admin/verifyMerchantInfo";
  }
  
  
  
  /**
   * 删除商家
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/deleteMerchant")
  @ResponseBody
  public Map<String, Object> deleteMerchant(HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      

      String id = request.getParameter("id");
      
      if (id != null) {
        int merchantId = Integer.parseInt(id);
        manageMerService.deleteMerchant(merchantId);//删除商家
        manageMerService.deleteMerchantInventory(merchantId);//删除商家库存
        manageMerService.deleteMerchantComment(merchantId);//删除商家评语
      } else {
          throw new CustomException("商家id为空！");
      }
        
      resultMap.put("data", ResultCode.SUCCESS);
      
      return resultMap;
  }
  
  
  
  /**
   * 查询未审核商家
   * @param request
   * @return
   */
  @RequestMapping("/queryVerifyMerchants")
  @ResponseBody
  public Map<String, Object> queryVerify(HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      List<Merchant> verifyMerchantList = manageMerService.queryVerifyMerchants();

      resultMap.put("verifyMerchantList", verifyMerchantList);
    
    return resultMap;
  }
  
  /**
   * 通过审核
   * @param request
   * @return
 * @throws Exception 
   */
  @RequestMapping("/verifyMerchant")
  @ResponseBody
  public Map<String,Object> verifyPass(HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      Map<String,Object> resultMap = new HashMap<String, Object>();
      
      String id = request.getParameter("merchantId");  
      if (id != null) {
        int verifyMerchantId = Integer.parseInt(id);
        
        manageMerService.verifyPass(verifyMerchantId);
        
        //初始化库存
        List<Dish> dishList = dishService.showAllDishs();//查找所有菜品id，存入List
        
        Map<String, Object> inventoryMap = new HashMap<String, Object>();//将菜品idList 和 商家id存入Map，好进行MyBatis操作
      
        inventoryMap.put("dishList", dishList);  
        inventoryMap.put("merchantId", id);
      
        manageMerService.addInventory(inventoryMap);//新商家生成库存
        
        //初始化评语
        List<Comment> commentList = commentService.showAllComments();
        
        Map<String, Object> commentsMap = new HashMap<String, Object>();//将评论idList 和 商家id存入Map，好进行MyBatis操作
        
        commentsMap.put("commentList", commentList);  
        commentsMap.put("merchantId", id);
      
        manageMerService.addComments(commentsMap);//新商家初始化评语
        
    } else {
        throw new CustomException("审核的商家id为空！");
    }

      return resultMap;
  }
  
  /**
   * 驳回申请
   * @param request
   * @return
 * @throws Exception 
   */
  @RequestMapping("/verifyReject")
  @ResponseBody
  public Map<String,Object> verifyReject(HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      Map<String,Object> resultMap = new HashMap<String, Object>();
      
      String merchantId = request.getParameter("merchantId");  
      String linkPhone = request.getParameter("linkPhone");
      String rejectReason = request.getParameter("rejectReason");
      
      if (merchantId != null) {
        int verifyMerchantId = Integer.parseInt(merchantId);
        System.out.println("rejectReason="+rejectReason);
        
        RejectReason reason = new RejectReason();
        reason.setMerchantId(verifyMerchantId);
        reason.setContent(rejectReason);
        String rejectTime = TimeUtil.getCurrentTimeAndDate();
        reason.setTime(rejectTime);
        
        manageMerService.verifyReject(verifyMerchantId);
        manageMerService.addRejectReson(reason);//添加驳回理由
        
       /* CommonServiceImpl commonServiceImpl = new CommonServiceImpl(); 
        commonServiceImpl.doSendPhoneForRejectReason(linkPhone, rejectReason);//发送短信给申请商家，告诉理由
*/        
        resultMap.put("code",ResultCode.SUCCESS);
    } else {
        throw new CustomException("审核的商家id为空！");
    }

      return resultMap;
  }
  
  /**
   * 删除新商家申请
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/deleteVerifyMerchant")
  public String deleteVerifyMerchant(HttpServletRequest request,HttpServletResponse response) 
          throws Exception{

      String id = request.getParameter("id");
      
      if (id != null) {
        int merchantId = Integer.parseInt(id);
        manageMerService.deleteMerchant(merchantId);//删除商家
      } else {
          throw new CustomException("商家id为空！");
      }
      
      return "forward:/manageMer/showVerify.do";
  }
  
  
  

  /**
   * 选择商家模式
   * @param model
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  
  //获取商家模式
  @RequestMapping("/getMerchantMode")
  public String getMerchantMode(ModelMap model,HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      
      int modeType = manageMerService.getModeType();
      
      request.getSession().setAttribute("modeType", modeType);
      
      return "admin/merchantMode";
  }
  
  //修改商家模式
  @RequestMapping("/modifyMode")
  @ResponseBody
  public Map<String,Object> modifyMode(ModelMap model,HttpServletRequest request,HttpServletResponse response) 
          throws Exception{
      
      Map<String,Object> resultMap = new HashMap<String, Object>();
      
      int modeType = Integer.parseInt(request.getParameter("modeType"));
      System.out.println("modeType=..............."+modeType);
      manageMerService.modifyMode(modeType);
      
      resultMap.put("code", "SUCCESS");
      return resultMap;
  }

}
