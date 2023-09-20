package in.fssa.aaha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.dao.ProductDAO;
import in.fssa.aaha.interfaces.ProductInterface;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.util.ConnectionUtil;

public class ProductDAO implements ProductInterface {


	@Override
	public int create(Product newProduct) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int productId = 0;
		try {
			String query = "INSERT INTO product (name,description,category_id,size,image) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getCategory_id());
			ps.setString(4, newProduct.getSize());
			ps.setString(5, newProduct.getImage());

			
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				productId = rs.getInt(1);
			}
			System.out.println("Product has been created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

		return productId;

	}


	
	@Override
	public void update(int id, Product updateProduct) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE product SET  description=?, name=?, size=?, image=? WHERE isActive=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updateProduct.getDescription());
			ps.setString(2, updateProduct.getName());
			ps.setString(3, updateProduct.getSize());
			ps.setString(4, updateProduct.getImage());
			ps.setInt(5, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Product with ID " + id + " has been updated successfully.");
			} else {
				System.out.println("Product with ID " + id + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}
	
	
	
	public Product findById(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		try {
			String query = "SELECT id, name, size, image, isActive, description FROM product WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Product does not exist");
			}

			product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setSize(rs.getString("size"));
			product.setImage(rs.getString("image"));
			product.setActive(rs.getBoolean("isActive"));
			product.setDescription(rs.getString("description"));
//			product.setPrice(rs.getInt(id));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs); // Close the ResultSet here
		}
		return product;
	}
	
	public boolean isProductAlreadyExists(String name) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT name, isActive FROM product WHERE isActive = true AND name=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}


	@Override
	public void delete(int id) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE product SET isActive=? WHERE isActive=1 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Product with ID " + id + " has been deleted successfully.");
			} else {
				System.out.println("Product with ID " + id + " not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}



	
	public List<Product> listAllTheProductsByCategoryId(int categoryId) throws DAOException {

		List<Product> ProductList = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * From product WHERE isActive= 1 AND category_id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSize(rs.getString("size"));
				product.setImage(rs.getString("image"));
				product.setActive(rs.getBoolean("isActive"));
				product.setDescription(rs.getString("description"));
				product.setCategory_id(rs.getInt("category_id"));
				ProductList.add(product);
				System.out.println(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}

	return ProductList;

	}

	public List<Product> ListAllProducts() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Product> allProducts = new ArrayList<Product>(); 
		
		try {
			String query = "SELECT * FROM product WHERE isActive = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSize(rs.getString("size"));
				product.setImage(rs.getString("image"));
				product.setDescription(rs.getString("description"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setActive(rs.getBoolean("isActive"));
//				product.setPrice(rs.getInt(""));
				
				allProducts.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps,rs);
		}
		return allProducts;
	}


	public boolean isDeletedProduct(int productId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * From product WHERE isActive=0 AND id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
	}






}