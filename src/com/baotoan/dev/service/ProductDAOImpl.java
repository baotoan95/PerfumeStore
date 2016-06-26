package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.CategoryDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.dao.PromotionDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Product;
import com.baotoan.dev.entity.ProductDetail;
import com.baotoan.dev.utils.GenerateCode;
import com.baotoan.dev.utils.ProductConstantValues;

public class ProductDAOImpl extends StandardConnection implements ProductDAO {
	private CategoryDAO cateDAO = new CategoryDAOImpl();
	private PromotionDAO promoDAO = new PromotionDAOImpl();
	
	@Override
	public List<Product> getAll() {
		String sql = "SELECT * FROM products";
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<Product> result = new ArrayList<Product>();
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt(1));
				prod.setName(rs.getString(2).trim());
				prod.setAvatar(rs.getString(3));
				prod.setViews(rs.getInt(4));
				prod.setSellCount(rs.getInt(5));
				prod.setDescript(rs.getString(6));
				prod.setCategory(cateDAO.getCategoryById(rs.getInt(7)));
				prod.setPromotion(promoDAO.getPromotionById(rs.getInt(8)));
				prod.setUpdateDay(rs.getDate(9));
				prod.setGift(rs.getBoolean(10));
				result.add(prod);
			}
			for(int i = 0; i < result.size(); i++) {
				result.get(i).addProductDetail(getProductDetailById(result.get(i).getId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> getProducts(int getBy, Object attribute, int currentPage, int numRecordPerPage) {
		String sql = "";
		
		switch (getBy) {
		case ProductConstantValues.BY_PROMOTION: // Khuyến mãi
			sql = "SELECT * FROM products WHERE promotionId IS NOT NULL";
			break;
		case ProductConstantValues.BY_GIFT: // Khuyến mãi
			sql = "SELECT * FROM products WHERE products.gift = 1";
			break;
		case ProductConstantValues.BY_TOPVIEW: // Xem nhiều
			sql = "SELECT * FROM products ORDER BY views DESC";
			break;
		case ProductConstantValues.BY_CHAR: // Xem prefix
			sql = "SELECT * FROM products WHERE products.name LIKE '" + attribute + "%'";
			break;
		case ProductConstantValues.BY_LATEST: // Sản phẩm mới
			sql = "SELECT * FROM products ORDER BY updateDay DESC";
			break;
		case ProductConstantValues.BY_BESTSELL: // Bán chạy
			sql = "SELECT * FROM products ORDER BY products.sellCount DESC";
			break;
		case ProductConstantValues.BY_MANUFACTURER: // Nhà sản xuất
			sql = "SELECT products.id,name,imageUrl,views,sellCount,descript,categoryId,promotionId,updateDay,gift FROM products INNER JOIN categories ON products.categoryId = categories.id WHERE categories.brand = '" + attribute + "' ORDER BY RAND()";
			break;
		case ProductConstantValues.BY_SEARCH: // Theo tên sản phẩm
			sql = "SELECT * FROM products WHERE products.`name` LIKE '%"+attribute+"%' or products.`name` LIKE '%"+attribute+"' or products.`name` LIKE '"+attribute+"%'";
			break;
		case ProductConstantValues.BY_PRICE: // Theo tên sản phẩm
			String where = "";
			switch ((int)attribute) {
			case 1:
				where = "product_details.price <= 300000";
				break;
			case 2:
				where = "product_details.price >= 300000 AND product_details.price <= 600000";
				break;
			case 3:
				where = "product_details.price >= 600000 AND product_details.price <= 900000";
				break;
			case 4:
				where = "product_details.price >= 900000";
				break;
			default:
				break;
			}
			sql = "SELECT products.id,name,product_details.imageUrl,views,sellCount,descript,categoryId,promotionId,updateDay,gift,product_details.price FROM product_details INNER JOIN products ON products.id = product_details.productId WHERE " + where + " ORDER BY RAND()";
			break;
		default:
				sql = "SELECT * FROM products";
		}
		
		int totalRecordResult = 0;
		try {
			ResultSet rs = connection.getStatement().executeQuery("SELECT COUNT(*) FROM (" + sql + ") AS BT_TABLE");
			rs.next();
			totalRecordResult = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		int numPageNeedShow = 5;
		String html = GenerateCode.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Product> data = new ArrayList<Product>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt(1));
				prod.setName(rs.getString(2).trim());
				prod.setAvatar(rs.getString(3));
				prod.setViews(rs.getInt(4));
				prod.setSellCount(rs.getInt(5));
				prod.setDescript(rs.getString(6));
				prod.setCategory(cateDAO.getCategoryById(rs.getInt(7)));
				prod.setPromotion(promoDAO.getPromotionById(rs.getInt(8)));
				prod.setUpdateDay(rs.getDate(9));
				prod.setGift(rs.getBoolean(10));
				if(ProductConstantValues.BY_PRICE == getBy) {
					prod.setPrice(rs.getInt(11));
				}
				data.add(prod);
			}
			for(int i = 0; i < data.size(); i++) {
				data.get(i).addProductDetail(getProductDetailById(data.get(i).getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("html", html);
		map.put("data", data);
		return map;
	}

	@Override
	public boolean addProduct(Product product) {
		String sql = "INSERT INTO products(name,imageUrl,views,sellCount,descript,categoryId,promotionId,updateDay,gift) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, product.getName());
			pre.setString(2, product.getAvatar());
			pre.setInt(3, product.getViews());
			pre.setInt(4, product.getSellCount());
			pre.setString(5, product.getDescript());
			pre.setInt(6, product.getCategory().getId());
			pre.setInt(7, product.getPromotion().getId());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pre.setString(8, format.format(product.getUpdateDay()));
			pre.setBoolean(9, product.isGift());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delProduct(int productID) {
		String sql = "DELETE FROM products WHERE id = " + productID;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		String sql = "UPDATE products set name=?,imageUrl=?,views=?,sellCount=?,descript=?,categoryId=?,promotionId=?,updateDay=?,gift=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, product.getName());
			pre.setString(2, product.getAvatar());
			pre.setInt(3, product.getViews());
			pre.setInt(4, product.getSellCount());
			pre.setString(5, product.getDescript());
			pre.setInt(6, product.getCategory().getId());
			pre.setInt(7, product.getPromotion().getId());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pre.setString(8, format.format(product.getUpdateDay()));
			pre.setBoolean(9, product.isGift());
			pre.setInt(10, product.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product getProductById(int id) {
		String sql = "SELECT * FROM products WHERE id = " + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt(1));
				prod.setName(rs.getString(2).trim());
				prod.setAvatar(rs.getString(3));
				prod.setViews(rs.getInt(4));
				prod.setSellCount(rs.getInt(5));
				prod.setDescript(rs.getString(6));
				prod.setCategory(cateDAO.getCategoryById(rs.getInt(7)));
				prod.setPromotion(promoDAO.getPromotionById(rs.getInt(8)));
				prod.setUpdateDay(rs.getDate(9));
				prod.setGift(rs.getBoolean(10));
				prod.setListDetail(getProductDetailByProductId(prod.getId()));
				return prod;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addProductDetail(ProductDetail productDetail) {
		String sql = "INSERT INTO product_details(productId,capacity,price,market_price, imageUrl) values(?,?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setInt(1, productDetail.getProductId());
			pre.setString(2, productDetail.getCapacity());
			pre.setInt(3, productDetail.getPrice());
			pre.setInt(4, productDetail.getMarket_price());
			pre.setString(5, productDetail.getImageUrl());
			return pre.executeUpdate() > 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delProductDetailById(int id) {
		String sql = "DELETE FROM product_details WHERE id = " + id;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delProductDetailByProductId(int productId) {
		String sql = "DELETE FROM product_details WHERE productId = " + productId;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProductDetail(ProductDetail productDetail) {
		String sql = "UPDATE product_details set capacity=?, price=?, market_price=?, imageUrl=? WHERE productId = ?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, productDetail.getCapacity());
			pre.setInt(2, productDetail.getPrice());
			pre.setInt(3, productDetail.getMarket_price());
			pre.setString(4, productDetail.getImageUrl());
			pre.setInt(5, productDetail.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ProductDetail getProductDetailById(int id) {
		String sql = "SELECT * FROM product_details WHERE id = " + id + " ORDER BY price";
		ProductDetail proDetail = null;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while (rs.next()) {
				proDetail = new ProductDetail();
				proDetail.setId(rs.getInt(1));
				proDetail.setProductId(rs.getInt(2));
				proDetail.setCapacity(rs.getString(3));
				proDetail.setPrice(rs.getInt(4));
				proDetail.setMarket_price(rs.getInt(5));
				proDetail.setImageUrl(rs.getString(6));
			}
			return proDetail;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductDetail> getProductDetailByProductId(int productId) {
		String sql = "SELECT * FROM product_details WHERE productId = " + productId + " ORDER BY price";
		List<ProductDetail> result = new ArrayList<ProductDetail>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				ProductDetail proDetail = new ProductDetail();
				proDetail.setId(rs.getInt(1));
				proDetail.setProductId(rs.getInt(2));
				proDetail.setCapacity(rs.getString(3));
				proDetail.setPrice(rs.getInt(4));
				proDetail.setMarket_price(rs.getInt(5));
				proDetail.setImageUrl(rs.getString(6));
				result.add(proDetail);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
