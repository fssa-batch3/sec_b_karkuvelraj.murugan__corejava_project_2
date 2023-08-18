package in.fssa.aaha;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.Exception.ValidationException;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.service.Productservice;

public class TestProduct {

	
	@Test
	public  void testCreateProductWithValidInput() {
		
		Productservice productService = new Productservice();
		
		Product product = new Product();
		product.setName("TechNo");
		product.setCategory_id(2);
		
		assertDoesNotThrow(() ->{
			productService.createProduct(product);
		});
	}
	
	@Test    
	public void testCreateProductWithInvalidInput() {
		
		Productservice productService = new Productservice();
		Exception exception = assertThrows(ValidationException.class, () ->{
			productService.createProduct(null);
		});
		String expectedMessage = "Invalid Product input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		System.out.println(actualMessage);
	}
	
	@Test
	public void testCreateProductWithNameNull() {
		
		Productservice productService = new Productservice();
		
		Product product = new Product();
		
		product.setName(null);
		
		product.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "Name cannot be null or empty";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	@Test
	public void testCreateProductWithNameEmpty() {
		
		Productservice productService = new Productservice();
		
		Product product = new Product();
		
		product.setName("Lenovo");
		
		product.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "Name cannot be null or empty";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	@Test
	public void testCreateProductWithInvalidName() {
		
		Productservice productService = new Productservice();
		
		Product product = new Product();
		
		product.setName("PhoneCharger");
		
		product.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "Name does not match the pattern";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	//delete//
	@Test
	public  void testDeleteProduct() {
		
		Productservice productService = new Productservice();

		Product newProduct = new Product();
		newProduct.setId(3);
		assertDoesNotThrow(() ->{
			productService.deleteProduct(newProduct.getId());
		});
	}
	
	@Test
	public  void testDeleteProductwithInvalidId() {
		
		Productservice productService = new Productservice();

		Product newProduct = new Product();
		newProduct.setId(0);
		assertDoesNotThrow(() ->{
			productService.deleteProduct(newProduct.getId());
		});
	}
	
	// update//

	@Test
    public void testUpdateProduct() {
		Productservice productService = new Productservice();
        Product updateProduct = new Product();
        updateProduct.setId(1);
        updateProduct.setName("Mobiles");
        
        assertDoesNotThrow(() -> {
            productService.updateProduct(updateProduct.getId(), updateProduct.getName());
        });
    }
    
    @Test
    public void testUpdateProductInvalidName() {
    	Productservice productService = new Productservice();
        int productId = 1;
        String invalidName = null; // Or use an empty string: ""
        
        assertThrows(ValidationException.class, () -> {
            productService.updateProduct(productId, invalidName);
        });
    }
	}

