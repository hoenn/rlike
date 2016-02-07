package rlike;

public class Inventory {
	private Item[] items;
	private int size;
	public int getSize() { return size; }
	public Item[] getItems() { return items; }
	public Item get(int i) { return items[i]; }
	
	public Inventory(int max) {
		items = new Item[max];
		size = 0;
	}
	
	public void add(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				
				items[i] = item;
				size++;
				break;
			}
		}
	}
	public void removeSlot(int i) {
		if(items[i]!=null) {
			items[i] = null;
			size--;
		}
	}
	public void remove(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == item) {
				items[i] = null;
				size--;
				break;
			}
		}
	}
	public boolean hasItem(String itemName) {
		boolean hasItem = false;
		int i =0;
		while(i<items.length && !hasItem) {
			if(items[i]!=null)
				hasItem = items[i].name.equals(itemName);
			i++;
		}
		return hasItem;
	}
	public boolean isFull() {
		return size == items.length;
	}
}
