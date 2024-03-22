package shop;

import java.util.ArrayList;

public class User {
	private String id;
	private String password;
	private Cart myCart;
	private int payment;

	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.myCart = new Cart(this.id);
		this.payment = 0;
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
	
	public void modifyPriceInMyCart(String itemName,int price) {
		myCart.modifyItemPrice(itemName, price);
	}
	
	public boolean deleteItem(String item) {
		return myCart.deleteItem(item);
	}
	
	public int getPayment() {
		return this.payment;
	}

	public void setPayment() {
		this.payment = myCart.payComplete();
	}

	@Override
	public String toString() {
		return String.format("ID : %s\nPASSWORD : %s\n=== 장바구니 목록 ===\n%s", id, password, myCart);
	}
}