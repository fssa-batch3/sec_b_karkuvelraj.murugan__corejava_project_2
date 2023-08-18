package in.fssa.aaha.service;
import in.fssa.aaha.validator.Productvalidator;
import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.model.Product;

import java.util.Set;
public class Productservice {
	 /**
	  * 
	  * @return
	  * @throws Exception
	  */
public Set<Product> listAllProduct()throws Exception{
		
		ProductDAO product = new ProductDAO();
		Set<Product> allProducts = product.listAllProducts();
		
		return allProducts;
	}
	    /**
	     * 
	     * @param category_id
	     * @return
	     * @throws Exception
	     */
	public Set<Product> listallProductsByCategoryId(int category_id) throws Exception {
	    Productvalidator validator = new Productvalidator();
	    validator.validateCategoryId(category_id);

	    ProductDAO productDAO = new ProductDAO();
	    Set<Product> products = productDAO.listallProductsByCategoryId(category_id);
	    
	    return products;
	}
	
	
	
	
	 /**
	  * 
	  * @param product
	  * @throws Exception
	  */
	public void createProduct(Product product)throws Exception{
		
		Productvalidator validator = new Productvalidator();
		validator.validateProduct(product);
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.create(product);
		
	}
	/**
	 * 
	 * @param productId
	 * @throws Exception
	 */
	public void deleteProduct(int productId) throws Exception{
		
		Productvalidator validator = new Productvalidator();
		validator.validateProductId(productId);
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.deleteProduct(productId);
		
	}

	
/**
 * 
 * @param id
 * @param name
 * @throws Exception
 */
	public void updateProduct(int id, String name) throws Exception {
		Productvalidator validator = new Productvalidator();
	    validator.validateProductUpdate(id, name); // You might need to create this validation method

	    ProductDAO productDAO = new ProductDAO();
	    productDAO.updateProduct(id, name);
	}

}