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
	
	public void modifyAmountInMyCart(String itemName,int amount) {
		myCart.modifyItemAmount(itemName, amount);
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
	
	public void setAdminPayment(int total) {
		if(Shop.log != 0)
			return;
		this.payment = total;
	}
	
	@Override
	public String toString() {
		return String.format("=== 장바구니 목록 ===\n%s", myCart);
	}
}