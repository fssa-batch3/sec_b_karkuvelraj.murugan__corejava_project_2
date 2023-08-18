package in.fssa.aaha.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import in.fssa.aaha.Exception.ValidationException;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.util.ConnectionUtil;
import in.fssa.aaha.util.StringUtil;

public class Productvalidator {
private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";
	   /**
	    * 
	    * @param product
	    * @throws ValidationException
	    */
	public void validateProduct(Product product) throws ValidationException{
		
		if(product == null) {
			throw new ValidationException("Invalid Product input");
		}
		
		 validateName(product.getName());
		
		// business validation
		Connection con = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     
		try {
			String query = "SELECT * FROM product WHERE name = ?";
			con = ConnectionUtil.getConnection();
         ps = con.prepareStatement(query);
         ps.setString(1, product.getName());
         rs = ps.executeQuery();
         
         if(rs.next()) {
       	  throw new ValidationException("product already exists");		
         }
		}catch (SQLException e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
         throw new RuntimeException(e);
     
     } finally {
         ConnectionUtil.close(con, ps);
     }
		
		validateCategoryId(product.getCategory_id());
		
	}
	  /**
	   * 
	   * @param name
	   * @throws ValidationException
	   */
	public  void validateName(String name) throws ValidationException {
        
        StringUtil.rejectIfInvalidString(name, "Name");
        
        if (!Pattern.matches(NAME_PATTERN, name)) {
            throw new ValidationException("Name does not match the pattern");
        }
    
    }
	
	
	
	/**
	 * 
	 * @param productId
	 * @throws ValidationException
	 */
	public void validateProductId1(int productId)throws ValidationException{
		if(productId < 0) {
			throw new ValidationException("Id cannot be negative or zero");
		}
	}
	
	/**
	 * 
	 * @param productId
	 * @throws ValidationException
	 */
	public void validateProductId(int productId)throws ValidationException{
		
		if(productId <= 0) {
			
			throw new ValidationException("Id cannot be negative or zero");
		}
		
		 Connection con = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     
		try {
			String query = "SELECT * FROM product WHERE id = ?";
			con = ConnectionUtil.getConnection();
           ps = con.prepareStatement(query);
           ps.setInt(1, productId);
           rs = ps.executeQuery();
           
           if(rs.next()) {
           	System.out.println("product exists");
           }else {
           	throw new ValidationException("product doesn't exist");
           }		
		} catch (SQLException e) {
			
           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
       
       } finally {
           ConnectionUtil.close(con, ps);
       }
	}
	 /**
	  * 
	  * @param categoryId
	  * @throws ValidationException
	  */
	public void validateCategoryId(int categoryId)throws ValidationException{
		
		 if (categoryId <= 0) {
		        throw new ValidationException("Category ID cannot be negative or zero");
		    }
		 Connection con = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     
		try {
			String query = "SELECT * FROM category WHERE id = ?";
			con = ConnectionUtil.getConnection();
           ps = con.prepareStatement(query);
           ps.setInt(1, categoryId);
           rs = ps.executeQuery();
           
           if(rs.next()) {
           	System.out.println("category exists");
           }else {
           	throw new ValidationException("category doesn't exist");
           }		
		} catch (SQLException e) {
			
           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
       
       } finally {
           ConnectionUtil.close(con, ps);
       }
	}
    
	/**
	 * 
	 * @param id
	 * @param name
	 * @throws ValidationException
	 */
	public void validateProductUpdate(int id, String name) throws ValidationException {
	    if (id <= 0) {
	        throw new ValidationException("Product ID cannot be negative or zero");
	    }

	    if (name == null || name.trim().isEmpty()) {
	        throw new ValidationException("Product name cannot be null or empty");
	    }
	    
	    // You might add more specific validation rules for 'name' if needed

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT * FROM product WHERE id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        if (!rs.next()) {
	            throw new ValidationException("Product doesn't exist");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    } finally {
	        ConnectionUtil.close(con, ps);
	    }
	}


}