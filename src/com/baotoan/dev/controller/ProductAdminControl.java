package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.BrandDAO;
import com.baotoan.dev.dao.CategoryDAO;
import com.baotoan.dev.dao.IntendedDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.dao.PromotionDAO;
import com.baotoan.dev.entity.Brand;
import com.baotoan.dev.entity.Category;
import com.baotoan.dev.entity.Intended;
import com.baotoan.dev.entity.Product;
import com.baotoan.dev.entity.Promotion;
import com.baotoan.dev.service.BrandDAOImpl;
import com.baotoan.dev.service.CategoryDAOImpl;
import com.baotoan.dev.service.IntendedDAOImpl;
import com.baotoan.dev.service.ProductDAOImpl;
import com.baotoan.dev.service.PromotionDAOImpl;
import com.baotoan.dev.utils.Loader;
import com.baotoan.dev.utils.ProductConstantValues;

/**
 * Servlet implementation class ProductAdminControl
 */
public class ProductAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDAO = new ProductDAOImpl();
	private BrandDAO brandDAO = new BrandDAOImpl();
	private IntendedDAO intendedDAO = new IntendedDAOImpl();
	private PromotionDAO promotionDAO = new PromotionDAOImpl();
	private CategoryDAO cateDAO = new CategoryDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		if(action.equalsIgnoreCase("all")) {
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> data = proDAO.getProducts(ProductConstantValues.DEFAULT, "", page, 30);
			String pagination = ((String) data.get("html")).replaceAll("page", "product?ac=all&page");
			List<Product> listProduct = (List<Product>) data.get("data");
			request.setAttribute("pagination", pagination);
			request.setAttribute("listProduct", listProduct);
			request.getRequestDispatcher("/WEB-INF/admin/pages/products.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("w")) {
			setRequire(request);
			
			if(null != request.getParameter("id")) { // Update
				int id = Integer.parseInt(request.getParameter("id"));
				Product product = proDAO.getProductById(id);
				request.setAttribute("product", product);
			}
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-product.jsp").forward(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	public void setRequire(HttpServletRequest request) {
		List<Brand> listBrands = brandDAO.getAll();
		List<Intended> listIntendeds = intendedDAO.getAll();
		List<Promotion> listPromotions = (List<Promotion>) promotionDAO.getPromotions(1, 1000).get("data");
		request.setAttribute("listBrands", listBrands);
		request.setAttribute("listIntendeds", listIntendeds);
		request.setAttribute("listPromotions", listPromotions);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "resources/images/products/";
		String pathFile = getServletContext().getRealPath("/") + path;
		setRequire(request);
		Map<String, String> result = Loader.upload(pathFile, request);
		
		String name = result.get("name");
		int brand = Integer.parseInt(result.get("brand"));
		int intended = Integer.parseInt(result.get("intended"));
		Category category = cateDAO.getCategory(brand, intended);
		Promotion promotion = promotionDAO.getPromotionById(Integer.parseInt(result.get("promotion")));
		Date date = new Date();
		String desc = result.get("desc");
		boolean gift = Boolean.parseBoolean(result.get("gift"));
		String filePath = result.get("avatar");
		String file = filePath.substring(filePath.indexOf("images"), filePath.length());
		String message = "";
		Product newPro = null;
		if(result.get("status").equalsIgnoreCase("true")) { // Upload success
			if(result.get("ac").equalsIgnoreCase("add")) {
				if(proDAO.addProduct(new Product(0, name, file, 0, category, 0, desc, promotion, date, gift))) {
					message = "Thêm thành công";
				} else {
					message = "Chưa thêm thành công";
				}
			} else {
				int id = Integer.parseInt(result.get("id"));
				Product p = proDAO.getProductById(id);
				newPro = new Product(id, name, file, p.getViews(), category, p.getSellCount(), desc, promotion, date, gift);
				if(proDAO.updateProduct(newPro)) {
					message = "Cập nhật thành công";
				} else {
					message = "Cập nhật thất bại";
				}
			}
		} else {
			if(result.get("ac").equalsIgnoreCase("edit")) {
				int id = Integer.parseInt(result.get("id"));
				Product p = proDAO.getProductById(id);
				file = file.indexOf(".") > 0 ? file : p.getAvatar();
				newPro = new Product(id, name, file, p.getViews(), category, p.getSellCount(), desc, promotion, date, gift);
				if(proDAO.updateProduct(newPro)) {
					message = "Cập nhật thành công";
				} else {
					message = "Cập nhật thất bại";
				}
			}
		}
		request.setAttribute("product", newPro);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/admin/pages/edit-product.jsp").forward(request, response);
	}

}
