package User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.aaha.model.User;
import in.fssa.aaha.service.UserService;

public class ListAllUsers {

	@Test
	public void getAllUsers() {

		UserService UserService = new UserService();

		assertDoesNotThrow(() -> {
			List<User> userList = UserService.getAllUsers();
			userList.forEach(System.out::println);
		});
	}

}
