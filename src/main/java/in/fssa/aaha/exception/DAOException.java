package in.fssa.aaha.exception;
/**
 * Custom exception class for Data Access Object (DAO) related errors.
 */
public class DAOException extends Exception {

	/**
	 * Constructs a new DAOException with the specified cause.
	 *
	 * @param e The cause of the exception.
	 */
	public DAOException(Exception e) {
		super(e);
	}

	/**
	 * Constructs a new DAOException with the specified error message.
	 *
	 * @param e The error message describing the exception.
	 */
	public DAOException(String e) {
		super(e);
	}
}