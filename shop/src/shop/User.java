package shop;

import java.util.ArrayList;

public class User {
	private String id;
	private String password;
	private Cart myCart;

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public User clone() {
		return new User(this.id, this.password);
	}
}
