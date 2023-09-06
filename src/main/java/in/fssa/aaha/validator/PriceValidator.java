package in.fssa.aaha.validator;
import in.fssa.aaha.dao.PriceDAO;
import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Price;

/**
 * Validator class for validating Price objects.
 */
public class PriceValidator {


	public static void validate(int productId, int price) throws ValidationException {
		

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if (!(price >= 100 && price <= 10000)) {
			throw new ValidationException("Price should be between a minimum of 100 and a maximum of 10000.");
		}
	}


	public static void validateUpdate(int productId, int newPrice) throws ValidationException {
//		if (newPrice == null) {
//			throw new ValidationException("Invalid Price input");
//		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if ( newPrice <= 100 || newPrice >= 10000) {
			throw new ValidationException("Price should be between a minimum of 100 and a maximum of 10000.");
		}

		PriceDAO priceDAO = null;
		try {
			priceDAO = new PriceDAO();
			priceDAO.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

	
	}

	public static void validateProductId(int productId) throws ValidationException {
		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}
		PriceDAO priceDAO = null;
		try {
			priceDAO = new PriceDAO();
			priceDAO.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
		
	}
}