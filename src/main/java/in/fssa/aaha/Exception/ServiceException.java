package in.fssa.aaha.Exception;

public class ServiceException extends Exception {
	public ServiceException(String string) {
		super(string);
	}

	public ServiceException(Exception errorMessage) {
		super(errorMessage);
	}

}