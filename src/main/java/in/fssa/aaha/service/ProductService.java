package in.fssa.aaha.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ServiceException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.model.ProductEntity;

import in.fssa.aaha.model.Price;
import in.fssa.aaha.model.PriceEntity;

import in.fssa.aaha.service.PriceService;

import in.fssa.aaha.validator.Productvalidator;

/**
 * Service class for managing Product entities.
 */
public class ProductService {

	public void create(Product newProduct) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO(); // create instance for dao
			Productvalidator.validate(newProduct); // validate the product

			Price productPrice = newProduct.getPrice(); // get price from product entity
			int priceValue = productPrice.getPrice(); // store the price

			// validate the price between 100 and 10000
			if (priceValue <= 100 || priceValue >= 10000) {
				throw new ValidationException("Price should be between 100 and  10000.");
			}

			int productId = productDao.create(newProduct);

			PriceService priceService = new PriceService();
			priceService.createPrice(productId, priceValue);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

	}

	/**
	 * Retrieves a list of all products.
	 *
	 * @return A list of Product objects.
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<Product> ListAllProducts() throws ServiceException, ValidationException {
		ProductDAO productDao = new ProductDAO();
		List<Product> allProducts = productDao.ListAllProducts();
		PriceService priceService = new PriceService();

		for (Product product : allProducts) {

			Price price = priceService.getPrice(product.getId());
			product.setPrice(price);
		}

		// get price
		return allProducts;
	}

	public void update(int id, Product newUpdate) throws ServiceException, ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			Productvalidator.validateUpdate(id, newUpdate);
			productDao.update(id, newUpdate);

			PriceService priceservice = new PriceService();
			int price = newUpdate.getPrice().getPrice();

			priceservice.updateProductPrice(id, price);

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

//			Price price = new Price();
//			price.getPrice();
//			PriceService prser = new PriceService();
//			prser.getPrice(newId);
			Price price = new PriceService().getPrice(newId);
			product.setPrice(price);
//		  System.out.println(price);

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

		/*
		 * System.out.println("in service");
		 */
		List<Product> product = new ArrayList<>();

		try {
			ProductDAO productDao = new ProductDAO();
			product = productDao.listAllTheProductsByCategoryId(id);
			PriceService priceService = new PriceService();

			for (Product product1 : product) {

				Price price = priceService.getPrice(product1.getId());
				product1.setPrice(price);
				 System.out.println(price);

			}
			  } catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

		return product;
	}

}