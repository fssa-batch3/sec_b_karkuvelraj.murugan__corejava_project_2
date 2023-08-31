package in.fssa.aaha.util;

import in.fssa.aaha.exception.ValidationException;

public class IntUtil{
	/**
	 * 
	 * @param id
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidId(int id, String inputName) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException(inputName.concat(" cannot be zero or below zero"));
		}
	}

	/**
	 * 
	 * @param range
	 * @param inputRange
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidRange(int range, String inputRange) throws ValidationException {
		if (range < 1 || range > 1000) {
			throw new ValidationException(inputRange.concat(" range between 1 and 1000"));
		}
	} 
}
