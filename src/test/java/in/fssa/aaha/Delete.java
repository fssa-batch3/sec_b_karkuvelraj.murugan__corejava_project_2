package in.fssa.aaha;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.service.ProductService;

public class Delete{

	

	@Test
	public void testDeleteProductByInvalidNegativeProductId() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.delete(0);

		});

		String expectedMessage = "Invalid Product id";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testDeleteProductByInvalidNonExistenProductId() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.delete(100);

		});

		String expectedMessage = "The product is not listed among the available products";
		String actualMessage = exception.getMessage();

//		System.out.println("Expected: " + expectedMessage);
//		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testDeleteProductByAlreadyDeletedProductId() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.delete(3);

		});

		String expectedMessage = "This product has already been removed";
		String actualMessage = exception.getMessage();

		// System.out.println("Expected: " + expectedMessage);
		// System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
