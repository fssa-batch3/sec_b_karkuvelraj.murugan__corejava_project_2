package in.fssa.aaha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.interfaces.PriceInterface;
import in.fssa.aaha.model.Price;
import in.fssa.aaha.model.Product;
import in.fssa.aaha.util.ConnectionUtil;

public class PriceDAO implements PriceInterface {


	@Override
	public void create(int productId, int newPrice, Timestamp dateTime) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO product_price (price, product_id, start_date) VALUES (?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newPrice);
			ps.setInt(2, productId);
			ps.setTimestamp(3, dateTime);
			ps.executeUpdate();
			System.out.println("Price created succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}


	public void isProductExists(int productId) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM product where id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Product not available in the product list");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

	}

	/**
	 * 
	 * @param productId
	 * @return
	 * @throws DAOException
	 */
	public int getPriceIdByProductId(int productId) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int priceId = 0;
		try {
			String query = "SELECT * FROM product_price where product_id=? AND end_date is NULL";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("This product price is not available");
			}
			priceId = rs.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps);
		}

		return priceId;
	}
	
	
	
	
	public Price getPriceByProductId(int productId) throws DAOException {
		Price price ;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			String query = "SELECT * FROM product_price where product_id=? AND end_date IS NULL";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("This product price is not available");
			}

			price = new Price();
			price.setId(rs.getInt("id"));
			price.setPrice(rs.getInt("price"));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {

			ConnectionUtil.close(conn, ps , rs);
		}

		return price;
	}


	/**
	 * 
	 * @param priceId
	 * @param dateTime
	 * @throws DAOException
	 */
	public void updateProductPrice(int priceId, Timestamp dateTime) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE product_price SET end_date=? WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setTimestamp(1, dateTime);
			ps.setInt(2, priceId);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Price with ID " + priceId + " has been updated successfully.");
			} else {
				System.out.println("Price with ID " + priceId + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}


	public void isPriceAlreadyExists(int productId, Price newPrice) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM product_price WHERE product_id = ? AND price = ? AND end_date IS NULL";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, newPrice.getPrice());
			rs = ps.executeQuery();
			if (rs.next()) {
				throw new DAOException("Product price should be same");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException(e);
		}
	}

	

}
