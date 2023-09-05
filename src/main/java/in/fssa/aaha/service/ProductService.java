package in.fssa.aaha.service;
import java.util.Iterator;
import java.util.List;

import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ServiceException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.model.Price;
import in.fssa.aaha.validator.Productvalidator;

/**
 * Service class for managing Product entities.
 */
public class ProductService {

	/**
	 * Creates a new product.
	 *
	 * @param newProduct The new product to create.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided data is not valid for creating a
	 *                             product.
	 */
	public void create(Product newProduct) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO(); // create instance for dao
			Productvalidator.validate(newProduct);    //validate the product

			Price productPrice = newProduct.getPrice();   // get price from product entity
			int priceValue = productPrice.getPrice();     //store the price

			
			// validate the price between 100 and 10000
			if (priceValue <= 100 || priceValue >= 10000) {
				throw new ValidationException("Price should be between 100 and  10000.");
			}

			int productId = productDao.create(newProduct);
			PriceService priceService = new PriceService();
			priceService.create(productId, productPrice);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	/**
	 * Retrieves a list of all products.
	 *
	 * @return A list of Product objects.
	 */
	public List<Product> ListAllProducts() {
		ProductDAO productDao = new ProductDAO();
		List<Product> allProducts = productDao.ListAllProducts();
		PriceService priceService = new PriceService();
		/*
		 * for(Product product :allProducts) { Price price =
		 * priceService.findByProductId(product.getId()); product.setPrice(price); }
		 */

		// get price
		return allProducts;
	}

	/**
	 * Updates a product by its ID.
	 *
	 * @param id        The ID of the product to update.
	 * @param newUpdate The updated product object.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided data is not valid for updating
	 *                             the product.
	 */
	public void update(int id, Product newUpdate) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			Productvalidator.validateUpdate(id, newUpdate);
			productDao.update(id, newUpdate);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}


	/**
	 * Deletes a product by its ID.
	 *
	 * @param id The ID of the product to delete.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided ID is not valid.
	 */
	public void delete(int id) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			Productvalidator.validateDeleteId(id);
			productDao.delete(id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	public Product findById(int newId) throws ServiceException, ValidationException {
		Product product = null;
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			Productvalidator.validateId(newId);
			product = productDao.findById(newId);
//			int price = new PriceService().getPrice(newId);
//			product.setPrice(price);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return product;
	}

	/**
	 * Lists all products belonging to a specific category.
	 *
	 * @param id The ID of the category.
	 * @return A list of Product objects belonging to the specified category.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided category ID is not valid.
	 */
	public List<Product> listAllTheProductsByCategoryId(int id) throws ServiceException, ValidationException {
		List<Product> product = null;
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();  
			product = productDao.listAllTheProductsByCategoryId(id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return product;
	}
	

}