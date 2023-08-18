package in.fssa.aaha.service;

import java.util.Set;

import in.fssa.aaha.dao.CategoryDAO;
import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.model.Category;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.validator.Categoryvalidator;



public class CategoryService {
/**
 * 
 * @param newcategory
 * @throws Exception
 */
	public void create (Category newcategory) throws Exception{
		
		Categoryvalidator.validate(newcategory);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.create(newcategory);
	}
	
	
}

