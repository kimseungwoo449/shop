package shop;

import java.util.ArrayList;

public class User {

	private String id;
	private String password;
	private Cart myCart;

	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.myCart = new Cart(this.id);
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

	public Cart getMyCart() {
		return this.myCart;
	}

	public void setMyCart(Item item) {
		if (item == null)
			return;
		myCart.setMyCart(item);
	}
	
	@Override
	public String toString() {
		return String.format("ID : %s\nPASSWORD : %s\n=== 장바구니 목록 ===\n%s", id, password, myCart);
	}
}