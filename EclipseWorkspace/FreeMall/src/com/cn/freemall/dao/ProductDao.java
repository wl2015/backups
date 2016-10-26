package com.cn.freemall.dao;

import java.util.List;

import com.cn.freemall.bean.ProductBean;
import com.cn.freemall.entity.ProductEntity;

public interface ProductDao {
  public List<ProductBean> selectAllProductDao()throws Exception;
  
  public int insertNewProductDao(ProductEntity productEntity)throws Exception;

  public List<ProductBean> selectProductsByTypeIdDao(int typeId)throws Exception;

  public boolean updateProductInfoDao(ProductEntity productEntity)throws Exception;

  public List<ProductBean> selectProductsByUserIdDao(int userId)throws Exception;

  public ProductBean selectProductByProductIdDao(int productId)throws Exception;
  
  public boolean updateStateByProductIdDao(int productId)throws Exception;
}
