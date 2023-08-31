package in.fssa.aaha.exception;

/**
 * Custom exception class for service layer related errors.
 */
public class ServiceException extends Exception {

    /**
     * Constructs a new ServiceException with the specified error message.
     *
     * @param errorMessage The error message describing the exception.
     */
    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructs a new ServiceException with the specified cause.
     *
     * @param cause The cause of the exception.
     */
    public ServiceException(Exception cause) {
        super(cause);
    }
}