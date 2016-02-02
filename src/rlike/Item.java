package rlike;

import java.awt.Color;

public class Item extends Entity{
	
	private int foodValue;
	public int foodValue() { return foodValue; }
	public void modifyFoodValue(int amt) { foodValue += amt; }
	public Item(char glyph, Color color, String name)
	{
		super(glyph, color, name);
	}
}
