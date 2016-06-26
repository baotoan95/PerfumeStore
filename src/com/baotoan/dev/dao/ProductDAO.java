package com.baotoan.dev.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.dev.entity.Product;
import com.baotoan.dev.entity.ProductDetail;

public interface ProductDAO {
	public List<Product> getAll();
	public Map<String, Object> getProducts(int getBy, Object attribute, int currentPage, int numRecordPerPage);
	public boolean addProduct(Product product);
	public boolean delProduct(int productID);
	public boolean updateProduct(Product product);
	public Product getProductById(int id);
	
	public boolean addProductDetail(ProductDetail productDetail);
	public boolean delProductDetailById(int id);
	public boolean delProductDetailByProductId(int productId);
	public boolean updateProductDetail(ProductDetail productDetail);
	public ProductDetail getProductDetailById(int id);
	public List<ProductDetail> getProductDetailByProductId(int productId);
}
