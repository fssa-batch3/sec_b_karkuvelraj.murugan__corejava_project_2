package in.fssa.aaha.interfaces;
import java.sql.Timestamp;

import in.fssa.aaha.model.Price;

public interface PriceInterface {

	

		public void create(int ProductId, int newPrice, Timestamp dateTime) throws Exception;

		
		
	}