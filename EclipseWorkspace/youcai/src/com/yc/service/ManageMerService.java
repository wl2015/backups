package com.yc.service;
import java.util.List;
import java.util.Map;


import com.yc.bean.Merchant;
import com.yc.bean.RejectReason;
import com.yc.util.Page;


/**
 * baseAdminService
 * @author
 *
 */
public interface ManageMerService {
    
    public long getAllMerchantCount() throws Exception;   //获得商家总数
    public long getAllVerifyMerchantCount() throws Exception;   //获得未审核商家总数
    public long getMerchantByKeywordsCount(String keywords) throws Exception;   //获得符合关键词搜索的商家总数
    
    public List<Merchant> queryMerchants() throws Exception;  //查询商家列表
    public List<Merchant> queryVerifyMerchants() throws Exception;  //查询未审核商家列表
    
    public List<Merchant> getMerchants(Page page) throws Exception;  //查询商家列表（分页）
    public List<Merchant> getVerifyMerchants(Page page) throws Exception;  //查询未审核商家列表（分页）
    
    
    public List<Merchant> queryMerchantsByKeyWords(Map<String,Object> map) throws Exception;//关键字查询商家（商家管理）(分页)
    public List<Merchant> queryMerchantsByKey(String Keywords) throws Exception;//关键字查询商家（出货单）
    
    public void deleteMerchant(int id) throws Exception; //删除商家 
    public void deleteMerchantInventory(int id)throws Exception;//删除商家库存
    public void deleteMerchantComment(int id)throws Exception;//删除商家评语
    
    public Merchant getMerchantById(int id) throws Exception; //根据id获取商家信息
    
    public void verifyPass(int id) throws Exception; //通过审核
    public void verifyReject(int id) throws Exception; //驳回审核
    public void addRejectReson(RejectReason rejectReason)throws Exception;//添加驳回审核理由
    
    public RejectReason getRejectReason(int merchantId)throws Exception;//根据m_id获取驳回商家理由 
    
    public void addInventory(Map<String, Object> inventoryMap) throws Exception; //新商家生成库存
    public void addComments(Map<String, Object> commentsMap) throws Exception; //新商家初始化评语
  
    /**
     * 商家模式选择
     */
    public int getModeType() throws Exception;//获取商家模式
    public void modifyMode(int modeType) throws Exception;//修改商家模式

}
