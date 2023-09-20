package in.fssa.aaha.service;

import java.util.List;

import in.fssa.aaha.dao.UserDAO;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ServiceException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.User;
import in.fssa.aaha.util.StringUtil;
import in.fssa.aaha.validator.UserValidator;

public class UserService {
	UserDAO userDAO = new UserDAO();

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */

	public List<User> getAllUsers() throws ServiceException {

		List<User> userList;
		try {

			userList = userDAO.findAll();

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to retrieve all users" + e.getMessage());
		}

		return userList;

	}

	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createUser(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validateCreate(newUser);
			userDAO.create(newUser);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateUser(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validateUpdate(newUser);
			UserValidator.rejetcIfUserDoesNotExists(newUser.getId());

			userDAO.update(newUser);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Update User " + e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void deleteUser(int id) throws ValidationException, ServiceException {

		try {

			UserValidator.validateDelete(id);

			userDAO.delete(id);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Delete User");
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findById(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.rejetcIfUserDoesNotExists(id);

			return userDAO.findById(id);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException {

		try {

			StringUtil.rejectIfInvalidString(email, "Email");

			return userDAO.findByEmail(email);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public int logIn(String email, String Password) throws ValidationException, ServiceException {

		int userId;
		try {

			UserValidator.validateLogIn(email, Password);

			UserDAO userDAO = new UserDAO();
			userId = userDAO.logIn(email, Password);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
		return userId;
	}
}