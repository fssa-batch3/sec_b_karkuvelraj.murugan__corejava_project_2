package in.fssa.aaha;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Price;
import in.fssa.aaha.service.PriceService;


public class Update {

	public class PriceServiceTest {

		@Test
		public void testUpdatePriceWithValidInput() {
			PriceService priceService = new PriceService();

			Price price = new Price();
			price.setPrice(generateRandomPriceInRange(50, 10000));

			assertDoesNotThrow(() -> {
				priceService.updatePrice(2, price);
			});
		}

		private int generateRandomPriceInRange(int min, int max) {
			return (int) (Math.random() * (max - min + 1)) + min;
		}
	}

	@Test
	public void testUpdatePriceWithNullObjectPrice() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(200);
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(2, null);
		});

		String exceptedMessage = "Invalid Price input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdatePriceMinimumRequiredAmount() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(20);
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(2, price);
		});

		String expectedMessage = "Price should be between a minimum of 50 and a maximum of 50000.";
		String actualMessage = exception.getMessage();
		 System.out.println("Expected: " + expectedMessage);
		 System.out.println("Actual: " + actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdatePriceMaximumRequiredAmount() {
		PriceService priceService = new PriceService();

		Price price = new Price();
		price.setPrice(50002);
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(2, price);
		});

		String expectedMessage = "Price should be between a minimum of 50 and a maximum of 50000.";
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
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(-22, price);
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
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(22, price);
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
		// newUser.setActive(true);
		Exception exception = assertThrows(ValidationException.class, () -> {
			priceService.updatePrice(1, price);
		});

		String expectedMessage = "Product price should be same";
		String actualMessage = exception.getMessage();
//		System.out.println("Expected: " + expectedMessage);
//		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));

	}
}