package in.fssa.aaha.validator;

import in.fssa.aaha.Exception.ValidationException;
import in.fssa.aaha.dao.CategoryDAO;
import in.fssa.aaha.model.Category;

public class Categoryvalidator {
	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 */
	public static void validate(Category category) throws ValidationException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		if (category.getName() == null || "".equals(category.getName().trim())) {
			throw new ValidationException("Category name cannot be null or Empty");
		}

		if (category.getId() <= 0) {
			throw new ValidationException("Invalid category ID");

		}

		
	}
    /**
     * 
     * @param newId
     * @throws ValidationException
     */
	public static void ValidateId(int newId) throws ValidationException {
		if (newId <= 0) {
			throw new ValidationException("Invalid category ID");
		}

		CategoryDAO categoryDao = null;
		
	}

}
