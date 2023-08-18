package in.fssa.aaha.interfaces;

import java.util.Set;

import in.fssa.aaha.model.Category;
import in.fssa.aaha.model.CategoryEntity;

public interface Categoryinterface {

	void create(Category category);

	void updateName(int id, String categoryName);

	void delete(int id);

	Set<Category> listAllCategroyByCategoryId(int categoryId);
	
	
}