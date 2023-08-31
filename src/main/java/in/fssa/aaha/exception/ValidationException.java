package in.fssa.aaha.exception;

/**
 * Custom exception class for validation errors.
 */
public class ValidationException extends Exception {

	/**
	 * Constructs a new ValidationException with the specified error message.
	 *
	 * @param errorMessage The error message describing the exception.
	 */
	public ValidationException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new ValidationException with the specified cause.
	 *
	 * @param cause The cause of the exception.
	 */
	public ValidationException(Exception cause) {
		super(cause);
	}
}