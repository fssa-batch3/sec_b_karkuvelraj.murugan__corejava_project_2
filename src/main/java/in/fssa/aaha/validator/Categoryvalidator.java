package in.fssa.aaha.validator;

import in.fssa.aaha.dao.CategoryDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Category;

/**
 * Validator class for validating Category objects.
 */
public class Categoryvalidator {

	/**
	 * Validates a Category object.
	 *
	 * @param category The Category object to validate.
	 * @throws ValidationException If the Category object is invalid.
	 */
	public static void validate(Category category) throws ValidationException {
		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		if (category.getName() == null || "".equals(category.getName().trim())) {
			throw new ValidationException("Category name cannot be null or empty");
		}

		if (category.getId() <= 0) {
			throw new ValidationException("Invalid category ID");
		}

		if (category.getCategory_type_id() <= 0) {
			throw new ValidationException("Invalid category type ID");
		}
	}

	/**
	 * Validates a category ID.
	 *
	 * @param newId The category ID to validate.
	 * @throws ValidationException If the category ID is invalid or not found in the
	 *                             database.
	 */
	public static void validateId(int newId) throws ValidationException {
		if (newId <= 0) {
			throw new ValidationException("Invalid category ID");
		}

		CategoryDAO categoryDao = null;
		try {
			categoryDao = new CategoryDAO();
			categoryDao.findById(newId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}

	/**
	 * Validates a category type ID.
	 *
	 * @param categoryTypeId The category type ID to validate.
	 * @throws ValidationException If the category type ID is invalid or not found
	 *                             in the database.
	 */
	public static void validateCategoryTypeId(int categoryTypeId) throws ValidationException {
		if (categoryTypeId <= 0) {
			throw new ValidationException("Invalid category type ID");
		}

		CategoryDAO categoryDao = null;
		try {
			categoryDao = new CategoryDAO();
			categoryDao.isCategoryTypeIdExists(categoryTypeId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}
}