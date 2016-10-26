package com.h5.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h5.dao.ProductDao;
import com.h5.entity.Product;
import com.h5.service.ProductService;
import com.h5.util.CryptogramUtil;
import com.h5.util.DateUtil;
import com.h5.util.SystemUtil;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDao productDao;

	/**
	 * 根据用户id查询所有作品
	 * */
	public List<Product> doGetAllProductById(String userId) {
		return productDao.getAllProductById(userId);
	}

	/**
	 * 根据作品id删除该作品
	 * */
	public boolean doDeleteProduct(String id) {
		return productDao.deleteProduct(id);
	}

	/**
	 * 根据作品id复制一个新的作品
	 * */
	public Product doCopyProduct(String id, String key, String title, String uploadUrl) {
		Product oldProduct = productDao.getProductById(id);
		Product newProduct = oldProduct;
		
		newProduct.setId("");
		newProduct.setTitle(title);
		newProduct.setCreateTime(DateUtil.convertCurrentDTTMtoInt());
		newProduct.setLastReviseTime(DateUtil.convertCurrentDTTMtoInt());
		newProduct.setVersion(0);
		
		String newProductId = productDao.copyProduct(newProduct);
		
		if (newProductId.equals("-1")){
			String qrCodeUrl = "";
			String qrCodeAddr = "";
			try {
				qrCodeUrl = "HTTP://192.168.191.1:8080/h5xiu/intoProduct?id="+CryptogramUtil.encrypt(newProductId, key);
				qrCodeAddr = SystemUtil.getQrcode(uploadUrl, qrCodeUrl);
				
				//将二维码地址存入数据库
				if (productDao.serQrCodeAddrById(newProductId ,qrCodeAddr)){
					newProduct.setId(CryptogramUtil.encrypt(newProductId, key));
					newProduct.setQrCodeAddr(qrCodeAddr);
					return newProduct;
				}
				else{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
	
	/**
	 * 对作品进行下线处理
	 * */
	public boolean doOffLineProduct(String id) {
		return productDao.offLineProduct(id);
	}

	/**
	 * 预览作品
	 * */
	public Product doPreviewProduct(String id) {
		Product product = productDao.previewProduct(id);
		
		if (product.getMusicId() != null){
			product.setMusic(productDao.getMusicById(id));
		}
		
		return product;
	}
	
/****************************逻辑可能存在问题************************************/
	/**
	 * 保存编辑的作品
	 * */
	public boolean doSaveProduct(Product product) {
		if (product.getMusic() != null){
			return productDao.saveProduct(product) && productDao.saveMusic(product.getMusic());
		}
		
		return productDao.saveProduct(product);
	}
/****************************逻辑可能存在问题************************************/
	
	/**
	 * 编辑作品：获取作品部分信息
	 * */
	public Product doEditProduct(String id) {
		Product product = productDao.editProduct(id);
		if (product.getMusicId() != null){
			product.setMusic(productDao.getMusicById(id));
		}
		return product;
	}

	/**
	 * 用户创建新的作品
	 * */
	public String doAddProduct(Product product) {
		
		return productDao.addProduct(product);
	}

	/**
	 * 用户创建成功新的作品之后，生成相对应的作品的永久二维码
	 * */
	public boolean doSetQrCodeAddrById(String productId, String qrCodeAddr) {
		return productDao.serQrCodeAddrById(productId, qrCodeAddr);
	}
}
