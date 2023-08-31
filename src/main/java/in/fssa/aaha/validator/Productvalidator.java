package in.fssa.aaha.validator;

import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.util.StringUtil;

/**
 * Validator class for validating Product objects.
 */
public class Productvalidator {

	/**
	 * Validates a Product object for creation.
	 *
	 * @param product The Product object to validate.
	 * @throws ValidationException If the Product object is invalid or conflicts
	 *                             with existing data.
	 */
	public static void validate(Product product) throws ValidationException {
		if (product == null) {
			throw new ValidationException("Invalid input");
		}

		if (product.getCategory_id() <= 0) {
			throw new ValidationException("Invalid Category Id");
		}

		StringUtil.rejectIfInvalidString(product.getName(), "Product name");
		StringUtil.rejectIfInvalidString(product.getDescription(), "Product Description");
		ProductDAO productDAO = new ProductDAO();

		try {
			productDAO = new ProductDAO();
			boolean productExists = productDAO.isProductAlreadyExists(product.getName());
			if (productExists) {
				throw new ValidationException("Product name is already exists");
			}
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}

	/**
	 * Validates a Product object for update.
	 *
	 * @param id        The ID of the Product to update.
	 * @param newUpdate The updated Product object.
	 * @throws ValidationException If the updated Product object is invalid or
	 *                             conflicts with existing data.
	 */
	public static void validateUpdate(int id, Product newUpdate) throws ValidationException {
		if (newUpdate == null) {
			throw new ValidationException("Invalid Product input");
		}

		if (id <= 0) {
			throw new ValidationException("Invalid Product id");
		}

		StringUtil.rejectIfInvalidString(newUpdate.getName(), "Product Name");
		StringUtil.rejectIfInvalidString(newUpdate.getDescription(), "Product Description");

		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			productDao.findById(id);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}
		
		

	}

	/**
	 * Validates a Product ID for deletion.
	 *
	 * @param newId The ID to validate.
	 * @throws ValidationException If the Product ID is invalid, not found, or has
	 *                             already been removed.
	 */
	public static void validateDeleteId(int newId) throws ValidationException {

		if (newId <= 0) {
			throw new ValidationException("Invalid Product id");
		}
		ProductDAO productDao = null;
		try {
			productDao = new ProductDAO();
			productDao.findById(newId);

		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		try {
			productDao = new ProductDAO();
			boolean val = productDao.isDeletedProduct(newId);
			if (val) {
				throw new ValidationException("This product has already been removed");
			}

		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	}


}
