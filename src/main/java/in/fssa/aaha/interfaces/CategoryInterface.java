package in.fssa.aaha.interfaces;

import java.util.List;

import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Category;

public interface CategoryInterface {
	public abstract List<Category> findAll();

	public abstract Category findById(int id) throws ValidationException, DAOException;

	public abstract List<Category> findCategoryByCategoryTypeId(int typeId) throws Exception;

}