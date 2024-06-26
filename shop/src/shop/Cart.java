package shop;

import java.util.ArrayList;

//최종

public class Cart {
	private ArrayList<Item> myCart;
	private String myId;

	public Cart(String id) {
		this.myId = id;
		myCart = new ArrayList<Item>();
	}

	public String getMyId() {
		return this.myId;
	}

	public void setMyCart(Item item,int amount) {
		if (isExist(item)) {
			System.err.println("장바구니에 이미 존재하는 품목입니다.");
			return;
		}
		item.setAmount(amount);
		myCart.add(item);
		System.out.println("상품 추가가 완료 되었습니다.");
	}

	public void modifyItemPrice(String name, int price) {
		int index = itemIndexByName(name);
		if (index == -1) {
			return;
		}
		Item item = myCart.get(index);
		item.setPrice(price);
	}

	public void modifyItemAmount(String name, int amount) {
		int index = itemIndexByName(name);
		if (index == -1) {
			return;
		}
		Item item = myCart.get(index);
		item.setAmount(amount);
	}

	private int itemIndexByName(String name) {
		int index = -1;
		for (int i = 0; i < myCart.size(); i++) {
			Item item = myCart.get(i);
			if (item.getItemName().equals(name)) {
				index = i;
			}
		}
		return index;
	}

	private boolean isExist(Item item) {
		for (Item targetItem : myCart) {
			if (item.getItemName().equals(targetItem.getItemName()))
				return true;
		}
		return false;
	}

	public int payComplete() {
		int total = 0;
		for (Item item : myCart) {
			total += item.getPrice() * item.getAmount();
		}
		myCart.clear();
		return total;
	}

	public boolean deleteItem(String itemName) {
		for (Item item : myCart) {
			if (item.getItemName().equals(itemName)) {
				myCart.remove(item);
				return true;
			}
		}
		return false;
	}

	public String makeData() {
		String data = "";
		for (int i = 0; i < myCart.size(); i++) {
			data += this.myId;
			Item item = myCart.get(i);
			data += "," + item.getItemName() + "," + item.getPrice() + "," + item.getAmount();
			if(i<myCart.size()-1)
				data+=",";
		}
		return data;
	}
	
	public int getCartSize() {
		return myCart.size();
	}
	
	@Override
	public String toString() {
		String info = "";
		int total = 0;
		for (int i = 0; i < myCart.size(); i++) {
			Item item = myCart.get(i);
			info += String.format("%s : %d개 --> 가격 : %d 원\n", item.getItemName(), item.getAmount(),
					item.getPrice() * item.getAmount());

			total += item.getPrice() * item.getAmount();
		}
		info += String.format("=================\n총 가격 : %d 원", total);
		return info;
	}

}
