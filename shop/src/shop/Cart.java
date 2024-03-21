package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> myCart;
	private String myId;

	
	public Cart(String id) {
		this.myId = id;
		myCart = new ArrayList<Item>();
	}
	
	@Override
	public String toString() {
		String info = "";
		for (int i = 0; i < myCart.size(); i++) {
			Item item = myCart.get(i);
			info += String.format("%s : %d개 --> 총 가격 : %d", item.getItemName(), item.getAmount(),
					item.getPrice() * item.getAmount());
			if (i < myCart.size() - 1)
				info += "\n";
		}
		return info;
	}
	
}
