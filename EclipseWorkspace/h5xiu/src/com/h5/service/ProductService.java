package com.h5.service;

import java.util.List;

import com.h5.entity.Product;

public interface ProductService {

	public List<Product> doGetAllProductById(String userId);

	public boolean doDeleteProduct(String id);

	public Product doCopyProduct(String id, String key, String title, String uploadUrl);

	public boolean doOffLineProduct(String id);

	public Product doPreviewProduct(String id);

	public boolean doSaveProduct(Product product);

	public Product doEditProduct(String id);

	public String doAddProduct(Product product);

	public boolean doSetQrCodeAddrById(String productId, String qrCodeAddr);

}
