package User;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.User;
import in.fssa.aaha.service.UserService;

public class CreateUser {

	// Create
	@Test
	public void testCreateUserWithValidInput() {

		User user = new User();
		user.setUserName("Karkuvelraj");
		user.setEmail("kark1411@gmail.com");
		user.setPassword("12345@Bkya");
		user.setPhoneNumber(9092381410L);

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.createUser(user);
		});
	}

	@Test
	void testCreateUserWithInvalidInput() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "Invalid user Input";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateUserWithExistingEmail() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setUserName("rajmurugan");
			user.setEmail("kark@gmail.com");
			user.setPassword("12345@Bell");
			user.setPhoneNumber(9092381410L);

			userService.createUser(user);
		});
		String expectedMessage = "Email aldready exists try with other email address";

		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserEmailWithNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setUserName("karkuvel");
			user.setEmail(null);
			user.setPassword("12345@Bkya");
			user.setPhoneNumber(9092381410L);

			userService.createUser(user);

		});

		String expectedMessage = "Email cannot be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithEmailEmpty() { //
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setUserName("karkuvel");
			newUser.setEmail("");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("Bakya@623b");

			userService.createUser(newUser);

		});

		String expectedMessage = "Email cannot be empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setUserName("bakyalakshmi");
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword(null);

			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setUserName("bakyalakshmi");
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("");

			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be empty";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithWrongPasswordPattern() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setUserName("bakyalakshmi");
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("fghjk34567");

			userService.createUser(newUser);
		});
		String expectedMessage = "Password doesn't match the required format.The password must contain atleast one Uppercase,one Lowercase,one Special character,one number";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setUserName("bakyalakshmi");
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("Bakya@623b");

			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	// user name test case
	@Test
	void testCreateUserWithUserNameNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setUserName(null);
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("Bakya@623b");

			userService.createUser(newUser);
		});
		String expectedMessage = "User name cannot be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithUserNameEmpty() { //
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setUserName("");
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("Bakya@623b");

			userService.createUser(newUser);
		});
		String expectedMessage = "User name cannot be empty";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateUserWithInvalidUserName() { //
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setUserName("12345sdfg");
			newUser.setEmail("bakyalakshmi@gmail.com");
			newUser.setPhoneNumber(9092381410L);
			newUser.setPassword("Bakya@623b");

			userService.createUser(newUser);
		});
		String expectedMessage = "User name should contain only alphabetic characters";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	/*
	 * private String generateRandomEmail() { String alphabet =
	 * "abcdefghijklmnopqrstuvwxyz"; StringBuilder email = new StringBuilder();
	 * 
	 * for (int i = 0; i < 10; i++) { int index = (int) (Math.random() *
	 * alphabet.length()); char randomChar = alphabet.charAt(index);
	 * email.append(randomChar); }
	 * 
	 * email.append("@gmail.com"); return email.toString(); }
	 */
}