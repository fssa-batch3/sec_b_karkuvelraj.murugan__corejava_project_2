package in.fssa.aaha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.model.User;
import in.fssa.aaha.util.ConnectionUtil;


public class UserDAO {

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */

	public List<User> findAll() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {

			String query = "SELECT id,name,email,phone_number,password,isActive FROM user WHERE isActive = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User newUser = new User();
				newUser.setId(rs.getInt("id"));
				newUser.setUserName(rs.getString("name"));
				newUser.setEmail(rs.getString("email"));
				newUser.setPhoneNumber(rs.getLong("phone_number"));
				newUser.setPassword(rs.getString("password"));
				newUser.setStatus(rs.getBoolean("isActive"));
				userList.add(newUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userList;
	}

	/**
	 * 
	 * @param new_User
	 * @throws PersistenceException
	 */

	public void create(User newUser) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "INSERT INTO user(name, email, phone_number, password) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getUserName().trim());
			ps.setString(2, newUser.getEmail().trim());
			ps.setLong(3, newUser.getPhoneNumber());
			ps.setString(4, newUser.getPassword());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User has been created sucessfully");
			} else {
				throw new DAOException("user has not been created");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	public void update(User newUser) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "UPDATE user SET name=?, password=? WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getUserName());
			ps.setString(2, newUser.getPassword());

			int userId = newUser.getId();
			ps.setInt(3, userId);

			int rowUpdated = ps.executeUpdate();

			if (rowUpdated > 0) {
				System.out.println("User with ID " + userId + " updated successfully.");
			} else {
				System.out.println("No user found with ID " + userId + ". user has not been updated");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * 
	 * @param email
	 * @throws PersistenceException
	 */
	public boolean EmailAlreadyExists(String email) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM user WHERE email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return flag;
	}

	/**
	 * 
	 * @throws PersistenceException
	 */

	public void delete(int id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "UPDATE user SET isActive = 0 WHERE id = ? AND isActive = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("User with ID " + id + " has been deactivated.");
			} else {
				System.out.println("No user found with ID " + id + ". Nothing changed.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * 
	 * @param userId
	 * @throws PersistenceException
	 */
	public boolean userAlreadyExists(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM user WHERE isActive = 1 AND id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return flag;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public User findById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT id,name,email,phone_number,password,isActive FROM user WHERE isActive = 1 && id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setStatus(rs.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		System.out.println(user);
		return user;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */

	public User findByEmail(String email) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT id,name,email,phone_number,password,isActive FROM user WHERE isActive = 1 AND email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(user);
		return user;
	}

	public int logIn(String email, String password) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int user = -1;

		try {
			String query = "SELECT id FROM user WHERE email = ? AND password = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

}