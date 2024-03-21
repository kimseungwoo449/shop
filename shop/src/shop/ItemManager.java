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
}
