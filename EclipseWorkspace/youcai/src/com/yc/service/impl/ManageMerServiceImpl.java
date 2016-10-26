package com.yc.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.yc.bean.Merchant;
import com.yc.bean.RejectReason;
import com.yc.dao.ManageMerDAO;
import com.yc.dao.MerchantStarDao;
import com.yc.service.ManageMerService;
import com.yc.util.Page;

/**
 * baseAdminService
 * @author
 *
 */
@Service
public class ManageMerServiceImpl implements ManageMerService{
    @Autowired
    private ManageMerDAO managemerDao;
    @Autowired
    private MerchantStarDao merchantStarDao;
    
    
  //获得商家总数
    public long getAllMerchantCount() throws Exception{
        return managemerDao.getAllMerchantCount();
    } 
  //获得未审核商家总数
    public long getAllVerifyMerchantCount() throws Exception{
        return managemerDao.getAllVerifyMerchantCount();
    } 
  //获得符合关键词搜索的商家总数
    public long getMerchantByKeywordsCount(String keywords) throws Exception{
        return managemerDao.getMerchantByKeywordsCount(keywords);
    }  
    
    
  //查询商家列表（分页）
    public List<Merchant> getMerchants(Page page) throws Exception{
        return managemerDao.getMerchants(page);
    } 
  //查询未审核商家列表（分页）
    public List<Merchant> getVerifyMerchants(Page page) throws Exception{
        return managemerDao.getVerifyMerchants(page);
    } 

    //查询商家
    public List<Merchant> queryMerchants() throws Exception {
       
        return managemerDao.queryMerchants();
    }
    public List<Merchant> queryVerifyMerchants() throws Exception{
        return managemerDao.queryVerifyMerchants();
    } 
    
  //关键字查询商家（出货单）
    public List<Merchant> queryMerchantsByKey(String Keywords) throws Exception{
        return managemerDao.queryMerchantsByKey(Keywords);
    }
    //关键字查询商家（商家管理）(分页)
    public List<Merchant> queryMerchantsByKeyWords(Map<String,Object> map) throws Exception{
        return managemerDao.queryMerchantsByKeyWords(map);
    }

    //删除商家
    public void deleteMerchant(int id) throws Exception {
        managemerDao.deleteMerchant(id);
        merchantStarDao.deleteMerchantStar(id);//删除商家星级
    }
  //删除商家库存
    public void deleteMerchantInventory(int id)throws Exception{
        managemerDao.deleteMerchantInventory(id);
    }
  //删除商家评语
    public void deleteMerchantComment(int id)throws Exception{
        managemerDao.deleteMerchantComment(id);
    }
  
  //根据id获取商家信息
    public Merchant getMerchantById(int id) throws Exception{
        return managemerDao.getMerchantById(id);
    }

    //通过审核
    public void verifyPass(int id) throws Exception{
       int merchant = managemerDao.verifyPass(id);
       if(merchant > 0 ){
          merchantStarDao.initMerchantStar(id);
       } 
    }
    //驳回审核
    public void verifyReject(int id) throws Exception{
        managemerDao.verifyReject(id);
    } 
  //添加驳回审核理由
    public void addRejectReson(RejectReason rejectReason)throws Exception{
        managemerDao.addRejectReson(rejectReason);
    }
    
  //根据m_id获取驳回商家理由 
    public RejectReason getRejectReason(int merchantId)throws Exception{
        return managemerDao.getRejectReason(merchantId);
    }
    
    
    //新商家生成库存
    public void addInventory(Map<String, Object> inventoryMap) throws Exception{
        managemerDao.addInventory(inventoryMap);
    }
  //新商家初始化评语
    public void addComments(Map<String, Object> commentsMap) throws Exception{
        managemerDao.addComments(commentsMap);
    }
    
    
    /**
     * 商家模式选择
     */
    
    //获取商家模式
    public int getModeType() throws Exception{
        return managemerDao.getModeType();
    }
    //修改商家模式
    public void modifyMode(int modeType) throws Exception{
        managemerDao.modifyMode(modeType);
    }
}
