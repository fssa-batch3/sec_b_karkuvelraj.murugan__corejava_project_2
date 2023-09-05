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

	public void create(int productId, Price newPrice) throws ValidationException, ServiceException {
		try {
			PriceDAO priceDao = new PriceDAO();
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);
			PriceValidator.validate(newPrice);
			PriceValidator.validateId(productId);
			priceDao.create(productId, newPrice, dateTime);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public Price getPrice(int productId) throws  ServiceException, ValidationException {
		Price price; 
		try {
			PriceValidator.validateId(productId);
			PriceDAO priceDAO = new PriceDAO();
			price = priceDAO.findPriceByProductId(productId);
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return price;
	}
}