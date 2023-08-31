package in.fssa.aaha;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.service.CategoryService;
import in.fssa.aaha.service.ProductService;
public class Find {

//	category 
	
	
	@Test
	public void testFindAllProducts() {
		ProductService productService = new ProductService();
		List<Product> allProducts;
		try {
			allProducts = productService.ListAllProducts();

			System.out.println(allProducts);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	category 
	@Test
	public void testFindAllProductByCategoryId() {
		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			System.out.println(productService.listAllTheProductsByCategoryId(2));
		});
	}

	@Test
	public void testFindProductWithNegativeCategoryId() {
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.listAllTheProductsByCategoryId(-20);
		});

		String exceptedMessage = "Invalid Category id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testFindProductWithNonExistentCategoryId() {
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.listAllTheProductsByCategoryId(50);
		});

		String expectedMessage = "Invalid Category id";
		String actualMessage = exception.getMessage();

		System.out.println("Expected: " + expectedMessage);
		System.out.println("Actual: " + actualMessage);

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	

}
