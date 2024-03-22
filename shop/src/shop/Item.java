package shop;

public class Item {
	
	private String itemName;
	private int price;
	private int amount;

	public Item(String itemName, int price) {
		this.itemName = itemName;
		this.price = price;
		this.amount = 0;
	}

	public String getItemName() {
		return this.itemName;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int number) {
		this.amount = number;
	}

	public Item clone() {
		return new Item(this.itemName, this.price);
	}

	@Override
	public String toString() {
		return String.format("%s : %d원", itemName, price);
	}
}
