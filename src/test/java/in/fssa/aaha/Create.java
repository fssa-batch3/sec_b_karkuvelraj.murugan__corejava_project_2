package in.fssa.aaha;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Price;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.service.PriceService;
import in.fssa.aaha.service.ProductService;

public class Create {
	
//  	private String generateRandomProductName() {
//		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		StringBuilder dishName = new StringBuilder();
//		for (int i = 0; i < 10; i++) {
//			int index = (int) (Math.random() * alphabet.length());
//			char randomChar = alphabet.charAt(index);
//			dishName.append(Character.toUpperCase(randomChar));
//		}
//		return dishName.toString();
//	}
	
	@Test
	public void testCreateProductWithValideInput() {

		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("DOFVE");
		product.setCategory_id(1);
		product.setDescription("Men  Fit Solid Casual Shit");
		product.setActive(true);
		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(200);
		product.setPrice(price);
		 assertDoesNotThrow(() -> {
			productService.create(product);

		});

//		Exception exception = assertThrows(ValidationException.class, () -> {
//			productService.create(product);
//		});
//
//		String actualMessage = exception.getMessage();
//		System.out.println(actualMessage);
	}

	@Test
	public void testCreateProductWithInvalidInput() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(Exception.class, () -> {
			productService.create(null);
		});
		String expectedMessage = "Invalid input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductNameWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName(null);
		product.setCategory_id(2);
		product.setDescription("iufnweruiof eroufhweruif weiufhwui");
		product.setActive(true);
		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(10000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product name input cannot be null or empty";
		String actualMessage = exception.getMessage();
//		System.out.println(actualMessage);

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductNameWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("");
		product.setCategory_id(1);
		product.setDescription(
				"Shirt with printed casual shirt");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String expectedMessage = "Product name input cannot be null or empty";
		String actualMessage = exception.getMessage();
//        System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductNameWithExistingProductName() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("karku");
		product.setCategory_id(1);
		product.setDescription("Men Regular Fit Solid Casual Shirt");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product name is already exists";
		String actualMessage = exception.getMessage();
//        System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithInvalidCategoryId() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(-22);
		product.setDescription(
				"This type of shirt made in china so you don;t buy this product");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Invalid Category Id";
		String actualMessage = exception.getMessage();
//         System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}

//	@Test
//	public void testCreateProductWithNonExistenCategoryId() {
//		ProductService productService = new ProductService();
//
//		Product product = new Product();
//		product.setName("Targa Super gold");
//		product.setCategory_id(22);
//	
//		product.setDescription(
//				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
//	
//		product.setActive(true);
//
//		PriceService priceService = new PriceService();
//		Price price = new Price();
//		price.setPrice(1000);
//		product.setPrice(price);
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			productService.create(product);
//		});
//
//		String exceptedMessage = "Category does not exist";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(exceptedMessage.equals(actualMessage));
//	}

	@Test
	public void testCreateProductDescriptionWithNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Blue shirt");
		product.setCategory_id(1);
		product.setDescription(null);
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Product Description input cannot be null or empty";
		String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
//		System.out.println();
	}

	@Test
	public void testCreateProductDescriptionWithEmptyString() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Targa Super gold");
		product.setCategory_id(1);
		product.setDescription("");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(1000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String expectedMessage = "Product Description input cannot be null or empty";
		String actualMessage = exception.getMessage();
//        System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductPriceBelow100Rupees() {
		ProductService productService = new ProductService();
		
		Product product = new Product();
		product.setName("VTEXX");
		product.setCategory_id(1);
		product.setDescription("Men Regular Fit Solid Casual Shirt");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(90);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Price should be between 100 and  10000.";
		String actualMessage = exception.getMessage();
//		System.out.println(actualMessage);
		assertTrue(actualMessage.contains(exceptedMessage));
	}

	@Test
	public void testCreateProductPriceUnder10000Rupees() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("AUYGHV");
		product.setCategory_id(1);
		product.setDescription("Men Regular Fit Solid Shirt");
		product.setActive(true);

		PriceService priceService = new PriceService();
		Price price = new Price();
		price.setPrice(6000);
		product.setPrice(price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(product);
		});

		String exceptedMessage = "Price should be between a minimum of 100 and a maximum of 10000";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
