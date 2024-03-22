package shop;

import java.util.ArrayList;

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
	
	public void setMyCart(Item item) {
		if(isExist(item)) {
			System.err.println("장바구니에 이미 존재하는 품목입니다.");
			return;
		}
		item.setAmount(1);
		myCart.add(item);
		System.out.println("상품 추가가 완료 되었습니다.");
	}
	
	public void modifyItemPrice(String name,int price) {
		for(Item item:myCart) {
			if(item.getItemName().equals(name)) {
				item.setPrice(price);
			}
		}
	}
	
	private boolean isExist(Item item) {
		for(Item targetItem:myCart) {
			if(item.getItemName().equals(targetItem.getItemName()))
				return true;
		}
		return false;
	}
	
	public int payComplete() {
		int total = 0;
		for(Item item:myCart) {
			total+=item.getPrice()*item.getAmount();
		}
		myCart.clear();
		return total;
	}
	
	public void deleteItem(String itemName) {
		for(Item item:myCart) {
			if(item.getItemName().equals(itemName)) {
				myCart.remove(item);
				return;
			}
		}
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
