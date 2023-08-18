package in.fssa.aaha;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import in.fssa.aaha.service.Productservice;
import in.fssa.aaha.model.Category;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.Exception.ValidationException;
import org.junit.jupiter.api.Test;



public class TestCategory {

    @Test
    public void testCreateCategoryWithValidInput() {
    	CategoryService categoryService = new CategoryService();

        Category newCategory = new Category();
        newCategory.setName("LaptopCharger");
        assertDoesNotThrow(() -> {
            categoryService.create(newCategory);
        });
    }
    
    

	@Test
	public void getAll() {
	CategoryService categpryService = new CategoryService();
		Set<Category> AllCategory;
		try {
			AllCategory = categpryService.listAllCategory();
			System.out.print(AllCategory);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

    @Test
    public void testCreateCategoryWithInvalidInput() {
        CategoryService categoryService = new CategoryService();
        Exception exception = assertThrows(ValidationException.class, () -> {
            categoryService.create(null);
        });
        String expectedMessage = "Invalid category input";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage)); // Use contains to handle additional error details if any
        System.out.println(actualMessage);
    }
    
    @Test
	public void testDeleteCategroy() {
		
    	CategoryService categoryService = new CategoryService();

		Category newCategory = new Category();
		newCategory.setId(1);
		assertDoesNotThrow(() ->{
			categoryService.delete(newCategory.getId());
		});
    }
		
		@Test
		public void testUpdateCategoryName() {
			
			CategoryService categoryService = new CategoryService();

			Category updateCategory = new Category();
			updateCategory.setId(4);
			updateCategory.setName("LapCharger");
			assertDoesNotThrow(() ->{
				categoryService.updateCategoryName(updateCategory.getId(),updateCategory.getName());
			});
		
	}

   
}