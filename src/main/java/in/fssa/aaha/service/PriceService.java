package in.fssa.aaha.service;
import java.time.LocalDateTime;

import in.fssa.aaha.dao.PriceDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ServiceException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Price;
import in.fssa.aaha.validator.PriceValidator;

/**
 * Service class for managing Price entities.
 */
public class PriceService {
	/**
	 * Creates a new price entry for a product.
	 *
	 * @param productId The ID of the product for which the price is created.
	 * @param newPrice  The new price object to create.
	 * @throws ValidationException If the provided data is not valid for creating a
	 *                             price.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 */
	public void create(int productId, Price newPrice) throws ValidationException, ServiceException {
		try {
			PriceDAO priceDao = new PriceDAO();
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);
			PriceValidator.validate(productId, newPrice);
			priceDao.create(productId, newPrice, dateTime);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Updates the price of a product and creates a new price entry.
	 *
	 * @param productId The ID of the product for which the price is updated.
	 * @param newPrice  The new price object to update and create.
	 * @throws ServiceException    If an error occurs while interacting with the
	 *                             database.
	 * @throws ValidationException If the provided data is not valid for updating
	 *                             the price.
	 */
	public void updatePrice(int productId, Price newPrice) throws ServiceException, ValidationException {
		PriceDAO priceDao = new PriceDAO();
		PriceValidator.validateUpdate(productId, newPrice);

		try {
			int priceId = priceDao.getPriceIdByProductId(productId);
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);

			priceDao.updateProductPrice(priceId, dateTime);
			priceDao.create(productId, newPrice, dateTime);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}