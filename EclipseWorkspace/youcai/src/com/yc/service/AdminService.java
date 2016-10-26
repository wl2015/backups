package com.yc.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.yc.util.Page;

/**
 * baseAdminService
 * @author
 *
 */
public interface AdminService {
    
  public Admin accountLogin(Admin admin) throws Exception;//账号登录
  public Admin phoneLogin(Admin admin) throws Exception;//手机登录
  
  public int isExistPassword(int adminId,String beforePassword)throws Exception;//判断密码是否存在
  public void updateAdminPwd(Admin admin) throws Exception;//修改管理员密码
  public boolean repeatPassword(Admin admin) throws Exception;//重置理员密码

  
  public Admin queryAdminForAccount(Admin admin) throws Exception;//根据管理员账户名查询是否存在该人
  public Admin queryAdminForPhone(Admin admin) throws Exception;//根据管理员手机号名查询是否存在该人
  
  public int getAdminPhone(String adminPhoneNum)throws Exception;//查询是否由此手机号
  
  
  /**
   * 发送站内信
   */
  public void writeMessageText(MessageText msg) throws Exception;//生成一条站内信内容，并获取其主键
  public List<Integer> queryAllMerchantsId() throws Exception;//查询所有商家id
 
  public void sendMessage(Map<String, Object> messageList) throws Exception;//群发站内信给商家
  public List<MessageText> getMessageTextList(int month)throws Exception;//查询本月的站内信
  
  /**
   * 出货管理
   */
  public void addSales(Sales sales) throws Exception;//生成一条出货记录
  public List<SalesList> getSalesList(int month)throws Exception;//获得本月出货单记录
  
  /**
   * 财务管理
   */
  //public void addFinance(Finance finance) throws Exception;//生成一条财务记录
  
 // public List<Finance> financeList() throws Exception;//查询财务统计
  
  public List<SalesTotal> salesTotalList(Date dateFrom, Date dateTo) throws Exception; //查询菜品销售额
  public List<CreateAdvance> queryCreateAdvance()throws Exception;//查询预付款单
  public List<RefundOrder> queryRefundOrderList()throws Exception;// 查询用户退款单列表
  public RefundOrder getRefundOrder(int orderId)throws Exception;//获取退款单详情 
  public int getRefundOrderCount()throws Exception;//获得用户退款单数量
  public void payCreateAdvance(int createAdvanceId)throws Exception;//确认打款预付款单给商家
  public void payRefundOrder(int orderId)throws Exception;//确认退款给用户
  public void rejectRefundOrder(int orderId)throws Exception;//驳回用户退款申请
  public void deleteRefundReason(int orderId)throws Exception;//删除用户退款理由 
  
  /**
   * 预付款管理
   */
  public List<AdvanceDetail> queryAdvance(int merchantId,int year,int month) throws Exception;//查询所有预付款
  
  public int getAdvanceCountByMerchantId(int merchantId,int year,int month) throws Exception;//通过商家id和指定月份获取预付款数量
  
  public CreateAdvance createAdvanceByMerchantId(int merchantId,int year,int month) throws Exception;//生成一条预付款单通过商家id
  public void addCreateAdvance(CreateAdvance createAdvance)throws Exception;//生成预付款单（插入数据库）
  
  public List<Integer> getAdvanceOrderIdList(int year,int month)throws Exception;//获得可生成预付款记录的orderIdList
  public void updateAdvanceStatus(List<Integer> orderIdList)throws Exception;//将生成预付款单的记录ad_status状态更改 
  public void doUserStatusOrder(List<Integer> doUserOrderIdList)throws Exception;//自动确认用户收货状态
  
  public List<AdvanceDetail> getShouldDoAdvance(int merchantId,int year,int month) throws Exception;//获得该自动确认的预付款
  
  /**
   * 权限管理
   */
  public List<Limits> getAdminLimitsInfo(int adminId)throws Exception;//得到管理员个人职位信息
  public List<Admin> getAdminList(Page page)throws Exception;//获得所有管理员（分页）
  public long getAllAdminCount()throws Exception;//获得管理员数量
  public Admin getAdminById(int adminId)throws Exception;//获得单个管理员信息
  
  public void addAdmin(Admin admin)throws Exception;//创建管理员
  public void registerLimits(Map<String, Object> limitIdList) throws Exception;//新的管理员注册权限
  
  public void deleteAdmin(int adminId)throws Exception;//删除管理员
  public void deleteLimitUser(int adminId)throws Exception;//根据a_id删除权限使用者limitUser
  
  public void updateAdmin(Admin admin)throws Exception;//修改管理员信息
  
  public int isExistAccount(Admin admin)throws Exception;//判断管理员账户是否存在(创建管理员)
  public int isExistPhone(Admin admin)throws Exception;//判断管理员手机是否存在(创建管理员)
}
