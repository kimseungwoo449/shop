package shop;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> items;
	private static ItemManager instance = new ItemManager();
	
	private ItemManager() {
		items = new ArrayList<Item>();
	}
	
	public static ItemManager getInstance() {
		return instance;
	}
	
	// enroll item
	public void enrollItem() {
		String itemName = Shop.inputString("품목 이름");
		int price = Shop.inputNumber("가격 설정");
		
		if(price<0||findItemIndexByName(itemName)!=-1) {
			System.err.println("가격이 잘못되었거나 중복되는 아이템이 존재합니다.");
			return;
		}
		
		Item item = new Item(itemName,price);
		items.add(item);
		System.out.println("아이템 등록 완료.");
	}
	
	private int findItemIndexByName(String itemName) {
		int index = -1;
		for(int i =0;i<items.size();i++) {
			Item item = items.get(i);
			if(item.getItemName().equals(itemName))
				index = i;
		}
		return index;
	}
	
	// update item
	public void updateItem() {
		if(items.size()==0) {
			System.err.println("상품 추가 후 이용가능합니다.");
			return;
		}
		
		printItems();
		Item item = findItemByName();
		
		if(item==null)
			return;
		
		System.out.println(item);
		int price = Shop.inputNumber("수정할 가격");
		
		if(price<1) {
			System.err.println("가격은 1원 이상이어야 합니다.");
			return;
		}
		
		item.setPrice(price);
		System.out.println("품목 수정 완료.");
	}
	
	private Item findItemByName() {
		String itemName = Shop.inputString("품목 이름");
		int index = findItemIndexByName(itemName);
		
		if(index==-1) {
			System.err.println("찾으시는 품목이 존재하지 않습니다.");
			return null;
		}
		return items.get(index);
	}

	// delete item
	public void deleteItem() {
		if(items.size()==0) {
			System.err.println("상품 추가 후 이용가능합니다.");
			return;
		}
		
		printItems();
		Item item = findItemByName();
		
		if(item==null)
			return;
		
		items.remove(item);
		System.out.println("품목 삭제 완료.");
	}
	
	private void printItems() {
		for(Item item : items)
			System.out.println(item);
	}
}
