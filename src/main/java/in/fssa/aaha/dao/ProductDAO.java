package in.fssa.aaha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.util.ConnectionUtil;
import in.fssa.aaha.interfaces.Productinterface;
	
	public class ProductDAO implements Productinterface {

	   /**
	    * 
	    * @param product
	    */
		public void create(Product product) {
			
			Connection conn = null;
			PreparedStatement ps = null;

			try {
				String query = "INSERT INTO product (name,category_id) VALUES (?,?)";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1,product.getName());
				ps.setInt(2, product.getCategory_id());
				
				
				ps.executeUpdate();
				
				System.out.println("Product created Successfully");
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException("Product is created");
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
		}
		
           /**
            * 
            * @param productId
            * @param name
            */
		public void updateProduct(int productId, String name) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    
		    try {
		        String query = "UPDATE product SET name = ? WHERE id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setString(1, name);
		        ps.setInt(2, productId);
		        
		        int rowsAffected = ps.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            System.out.println("Product with ID " + productId + " updated successfully");
		        } else {
		            System.out.println("No product found with ID " + productId);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new RuntimeException("Error updating product");
		    } finally {
		        ConnectionUtil.close(conn, ps);
		    }
		}



		

	  /**
	   * 
	   * @param id
	   */
		public void deleteProduct(int id) {
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				String query = "DELETE FROM product WHERE id = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate();
				
				System.out.println("Product deleted Successfully");
				
			}catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
		}

	       /**
	        * 
	        */
		public Set<Product> listAllProducts() {

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Set<Product> allProducts = new HashSet<>(); 
			
			try {
				String query = "SELECT * FROM product WHERE isActive = 1";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategory_id(rs.getInt("category_id"));
					product.setActive(rs.getBoolean("isActive"));
					
					
					allProducts.add(product);
				}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
			return allProducts;
		}

	     /**
	      * 
	      * @param category_id
	      * @return
	      */
		public Set<Product> listallProductsByCategoryId(int category_id) {
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Set<Product> listOfProductsByCategoryId = new HashSet<>(); 
			
			try {
				String query = "SELECT * FROM product WHERE category_id = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1,category_id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategory_id(rs.getInt("category_id"));
					
					
					listOfProductsByCategoryId.add(product);
				}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
			return listOfProductsByCategoryId;
			
		}
     
		
	

		@Override
		public Set<Product> listAllProductsByCategoryId(int category_id) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void updateProduct(int productId, String name, int categoryId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Product findProductDetailsByProductId(int productId) {
			// TODO Auto-generated method stub
			return null;
		}

		
	}
	
	


	