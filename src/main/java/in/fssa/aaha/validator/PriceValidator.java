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

   public static void validateId(int productId) throws ValidationException {
	   if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}
   }
					
	public static void validate(Price price) throws ValidationException {
		if (price == null) {
			throw new ValidationException("Invalid Price input");
		}

		if (!(price.getPrice() >= 100 && price.getPrice() <= 10000)) {
			throw new ValidationException("Price should be between a minimum of 100 and a maximum of 10000.");
		}
	}

	/**
	 * Validates a Price object for update.
	 *
	 * @param productId The ID of the associated product.
	 * @param newPrice  The updated Price object to validate.
	 * @throws ValidationException If the Price object is invalid or conflicts with
	 *                             existing data.
	 */
	public static void validateUpdate(int productId, Price newPrice) throws ValidationException {
		if (newPrice == null) {
			throw new ValidationException("Invalid Price input");
		}

		if (productId <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		if (newPrice.getPrice() <= 100 || newPrice.getPrice() >= 10000) {
			throw new ValidationException("Price should be between a minimum of 100 and a maximum of 10000.");
		}

		PriceDAO priceDao = null;
		try {
			priceDao = new PriceDAO();
			priceDao.isProductExists(productId);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}

		try {
			priceDao = new PriceDAO();
			priceDao.isPriceAlreadyExists(productId, newPrice);
		} catch (DAOException e) {
			throw new ValidationException(e);
		}
	}
}