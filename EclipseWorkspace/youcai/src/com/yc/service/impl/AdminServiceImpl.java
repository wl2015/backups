package com.yc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.Admin;
import com.yc.bean.Advance;
import com.yc.bean.AdvanceDetail;
import com.yc.bean.CreateAdvance;
import com.yc.bean.Finance;
import com.yc.bean.Limits;
import com.yc.bean.Message;
import com.yc.bean.MessageText;
import com.yc.bean.Order;
import com.yc.bean.RefundOrder;
import com.yc.bean.Sales;
import com.yc.bean.SalesTotal;
import com.yc.bean.SalesList;
import com.yc.dao.AdminDAO;
import com.yc.dao.RefundMessageDao;
import com.yc.service.AdminService;
import com.yc.util.Page;

/**
 * 管理员service 实现类
 * @author 
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private AdminDAO adminDao;
  @Autowired
  private RefundMessageDao refundMessageDao;




  /**
   * 管理员账号登录
   */
  @Override
  public Admin accountLogin(Admin admin) throws Exception {
    Admin adminInfo =adminDao.accountLogin(admin);
    return adminInfo;
  }
  /**
   * 管理员手机登录
   */
  @Override
  public Admin phoneLogin(Admin admin) throws Exception {
    Admin adminInfo =adminDao.phoneLogin(admin);
    return adminInfo;
  }
  /**
   * //判断密码是否存在
   */
  public int isExistPassword(int adminId,String beforePassword)throws Exception{
      return adminDao.isExistPassword(adminId,beforePassword);
  }
  /**
   * 修改密码
   */
  @Override
  public void updateAdminPwd(Admin admin) throws Exception {
     adminDao.updateAdminPwd(admin);
  }
  /**
   * 重置理员密码
   */
  public boolean repeatPassword(Admin admin) throws Exception{
      
      int isModified = adminDao.repeatPassword(admin);
      if(isModified > 0){
        return true;
      }
      else{
        return false;
      }
  }

  /**
   * 根据账号查询是否有此人
   */
  @Override
  public Admin queryAdminForAccount(Admin admin) throws Exception {
    
    return adminDao.queryAdminForAccount(admin);
  }
  
  /**
   * 根据手机查询是否有此人
   */
  public Admin queryAdminForPhone(Admin admin) throws Exception{
      return adminDao.queryAdminForPhone(admin);
  }

//查询是否由此手机号
  public int getAdminPhone(String adminPhoneNum)throws Exception{
      return adminDao.getAdminPhone(adminPhoneNum);
  }
  
  
  /**
   * 发送站内信
   */
  
  public void writeMessageText(MessageText msg) throws Exception{//生成一条站内信内容，并获取其主键
      adminDao.writeMessageText(msg);
  }
  public List<Integer> queryAllMerchantsId() throws Exception{//查询所有商家id   
      return adminDao.queryAllMerchantsId();
  }
  public void sendMessage(Map<String, Object> messageList) throws Exception{//群发站内信给商家
      adminDao.sendMessage(messageList);
  }
  public List<MessageText> getMessageTextList(int month)throws Exception{//查询本月的站内信
      return adminDao.getMessageTextList(month);
  }
  
  
/**
 * 出货管理
 */
  public void addSales(Sales sales) throws Exception{//生成一条出货记录
      adminDao.addSales(sales);
  }
  public List<SalesTotal> salesTotalList(Date dateFrom, Date dateTo) throws Exception{//查询菜品销售额
      return adminDao.salesTotalList(dateFrom,dateTo);
  }
  
  public List<SalesList> getSalesList(int month)throws Exception{//获得本月出货单记录
      return adminDao.getSalesList(month);
  }
    
    
    
/**
 * 财务管理    
 */
      
    //查询用户退款单列表
    public List<RefundOrder> queryRefundOrderList() throws Exception {
        return adminDao.queryRefundOrderList();
    }
    
    //获取退款单详情 
    public RefundOrder getRefundOrder(int orderId)throws Exception{
        return adminDao.getRefundOrder(orderId);
    }
  //获得用户退款单数量
    public int getRefundOrderCount()throws Exception{
        return adminDao.getRefundOrderCount();
    }
    //确认打款预付款单给商家
    public void payCreateAdvance(int createAdvanceId)throws Exception{
        adminDao.payCreateAdvance(createAdvanceId);
    }  
  //确认退款给用户
    public void payRefundOrder(int orderId)throws Exception{
        adminDao.payRefundOrder(orderId);
        refundMessageDao.insertMessage(orderId);
    }
  //驳回用户退款申请
    public void rejectRefundOrder(int orderId)throws Exception{
        adminDao.rejectRefundOrder(orderId);
    }
  //删除用户退款理由 
    public void deleteRefundReason(int orderId)throws Exception{
        adminDao.deleteRefundReason(orderId);
    }
    
    
/**
 * 预付款管理
 */
    //查询所有预付款
      
    public List<AdvanceDetail> queryAdvance(int merchantId,int year,int month) throws Exception {
        
        return adminDao.queryAdvance(merchantId,year,month);
    }
    
    //通过商家id和指定月份获取预付款数量
    public int getAdvanceCountByMerchantId(int merchantId,int year,int month) throws Exception{
        return adminDao.getAdvanceCountByMerchantId(merchantId,year,month);
    }
      
    //生成一条预付款单通过商家id
    public CreateAdvance createAdvanceByMerchantId(int merchantId,int year,int month) throws Exception{
        return adminDao.createAdvanceByMerchantId(merchantId,year,month);
    }
    //查询预付款单列表
    public List<CreateAdvance> queryCreateAdvance() throws Exception {
        return adminDao.queryCreateAdvance();
    }
    //生成预付款单记录（插入数据库）
    public void addCreateAdvance(CreateAdvance createAdvance) throws Exception {
       adminDao.addCreateAdvance(createAdvance);
    }
  //获得可生成预付款记录的orderIdList
    public List<Integer> getAdvanceOrderIdList(int year,int month)throws Exception{
        return adminDao.getAdvanceOrderIdList(year,month);
    }
  //将生成预付款单的记录ad_status状态更改 
    public void updateAdvanceStatus(List<Integer> orderIdList)throws Exception{
        adminDao.updateAdvanceStatus(orderIdList);
    }
  //自动确认用户收货状态
    public void doUserStatusOrder(List<Integer> doUserOrderIdList)throws Exception{
        adminDao.doUserStatusOrder(doUserOrderIdList);
    }
  //获得该自动确认的预付款
    public List<AdvanceDetail> getShouldDoAdvance(int merchantId,int year,int month) throws Exception{
        return adminDao.getShouldDoAdvance(merchantId, year, month);
    }
    
    
/**
 * 管理员权限管理
 */
    //得到管理员个人职位信息 
    public List<Limits> getAdminLimitsInfo(int adminId)throws Exception{
        return adminDao.getAdminLimitsInfo(adminId);
    }
    //获得所有管理员（分页）
    public List<Admin> getAdminList(Page page)throws Exception{
        return adminDao.getAdminList(page);
    }
    //获得管理员数量
    public long getAllAdminCount()throws Exception{
        return adminDao.getAllAdminCount();
    }
  //获得单个管理员信息
    public Admin getAdminById(int adminId)throws Exception{
        return adminDao.getAdminById(adminId);
    }
    
  //创建管理员
    public void addAdmin(Admin admin)throws Exception{
        adminDao.addAdmin(admin);
    }
  //新的管理员注册权限
    public void registerLimits(Map<String, Object> limitIdList) throws Exception{
        adminDao.registerLimits(limitIdList);
    }
    
  //删除管理员
    public void deleteAdmin(int adminId)throws Exception{
        adminDao.deleteAdmin(adminId);
    }
  //根据a_id删除权限使用者limitUser
    public void deleteLimitUser(int adminId)throws Exception{
        adminDao.deleteLimitUser(adminId);
    }
    
  //修改管理员信息
    public void updateAdmin(Admin admin)throws Exception{
        adminDao.updateAdmin(admin);
    }

  //判断管理员账户是否存在(创建管理员)
    public int isExistAccount(Admin admin)throws Exception{
        return adminDao.isExistAccount(admin);
    }
    
  //判断管理员手机是否存在(创建管理员)
    public int isExistPhone(Admin admin)throws Exception{
        return adminDao.isExistPhone(admin);
    }

}
