package User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.service.UserService;

public class DeleteUser {
	@Test
	void testDeleteUserWithValidId() {
		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.deleteUser(1);
		});

	}

	@Test
	void testDeleteUserWithIdZero() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(0);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testDeleteUserWithInvalidId() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(-567);
		});
		String expectedMessage = "User with ID " + 100 + " does not exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}