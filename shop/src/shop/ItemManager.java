package shop;

import java.util.ArrayList;

//최종

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

		if (price < 0 || findItemIndexByName(itemName) != -1) {
			System.err.println("가격이 잘못되었거나 중복되는 아이템이 존재합니다.");
			return;
		}

		Item item = new Item(itemName, price);
		items.add(item);
		System.out.println("아이템 등록 완료.");
	}

	private int findItemIndexByName(String itemName) {
		int index = -1;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getItemName().equals(itemName))
				index = i;
		}
		return index;
	}

	// update item
	public Object[] updateItem() {
		Object[] temp = null;
		if (items.size() == 0) {
			System.err.println("상품 추가 후 이용가능합니다.");
			return temp;
		}

		printItems();
		Item item = findItemByName();

		if (item == null)
			return temp;

		int price = Shop.inputNumber("수정할 가격");

		if (price < 1) {
			System.err.println("가격은 1원 이상이어야 합니다.");
			return temp;
		}
		item.setPrice(price);

		temp = new Object[2];
		temp[0] = item.getItemName();
		temp[1] = item.getPrice();
		System.out.println("품목 수정 완료.");
		return temp;
	}

	private Item findItemByName() {
		String itemName = Shop.inputString("품목 이름");
		int index = findItemIndexByName(itemName);

		if (index == -1) {
			System.err.println("찾으시는 품목이 존재하지 않습니다.");
			return null;
		}
		return items.get(index);
	}

	// delete item
	public String deleteItem() {
		if (items.size() == 0) {
			System.err.println("상품 추가 후 이용가능합니다.");
			return null;
		}

		printItems();
		Item item = findItemByName();

		if (item == null)
			return null;

		items.remove(item);
		System.out.println("품목 삭제 완료.");
		return item.getItemName();
	}

	private void printItems() {
		for (Item item : items)
			System.out.println(item);
	}

	public Item getItem() {
		if (Shop.log == -1) {
			return null;
		}

		printItems();
		Item item = findItemByName();

		if (item == null) {
			return item;
		}

		return item.clone();
	}

	public String makeData() {
		String data = "";
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			data += item.getItemName() + "," + item.getPrice();
			if (i < items.size() - 1)
				data += ",";
		}

		return data;
	}
	
	public void setItems(String[] data) {
		if(!data[0].equals("")) {
			for(int i = 0;i<data.length;i+=2) {
				String itemName = data[i];
				int price = Integer.parseInt(data[i+1]);
				Item item = new Item(itemName,price);
				items.add(item);
			}						
		}
	}
}
