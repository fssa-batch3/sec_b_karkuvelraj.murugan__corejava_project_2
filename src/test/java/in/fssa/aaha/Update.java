package in.fssa.aaha;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Price;
import in.fssa.aaha.service.PriceService;



	public class Update {

		@Test
		public void testUpdatePriceWithValidInput() {
			PriceService priceService = new PriceService();

			Price price = new Price();
			price.setPrice(generateRandomPriceInRange(100, 10000));
			int priceValue =  price.getPrice();

			assertDoesNotThrow(() -> {
				priceService.updateProductPrice(2, priceValue);
			});
		}

		private int generateRandomPriceInRange(int min, int max) {
			return (int) (Math.random() * (max - min + 1)) + min;
		}
	



	@Test
	public void testUpdatePriceMinimumRequiredAmount() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(20);
		// newUser.setActive(true);
		int priceValue =  price.getPrice();
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updateProductPrice(2, priceValue);
		});

		String expectedMessage = "Price should be between a minimum of 100 and a maximum of 10000.";
		String actualMessage = exception.getMessage();
		 System.out.println("Expected: " + expectedMessage);
		 System.out.println("Actual: " + actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdatePriceMaximumRequiredAmount() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(10002);
		int priceValue =  price.getPrice();
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updateProductPrice(2, priceValue);
		});

		String expectedMessage = "Price should be between a minimum of 100 and a maximum of 10000.";
		String actualMessage = exception.getMessage();
//		System.out.println("Expected: " + expectedMessage);
//		System.out.println("Actual: " + actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdatePriceWithInvalidNegativeOrZeroProductId() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(10002);
		int priceValue =  price.getPrice();
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updateProductPrice(-22, priceValue);
		});

		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdatePriceWithInvalidNonExistenProductId() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(2300);
		int priceValue =  price.getPrice();
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updateProductPrice(2222, priceValue);
		});

		String expectedMessage = "Product not available in the product list";
		String actualMessage = exception.getMessage();
//		System.out.println("Expected: " + expectedMessage);
//		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdatePriceWithExistsPrice() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(1000);
		
		int priceValue =  price.getPrice();
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updateProductPrice(1, priceValue);
		});

		String expectedMessage = "Product price is the same";
		String actualMessage = exception.getMessage();
//		System.out.println("Expected: " + expectedMessage);
		System.out.println( actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));

	}
}
