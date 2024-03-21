package shop;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> users;
	private static UserManager instance = new UserManager();

	private UserManager() {
		users = new ArrayList<User>();
		User admin = new User("admin","1111");
		users.add(admin);
	}

	public static UserManager getInstance() {
		return instance;
	}
}
