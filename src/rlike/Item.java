package rlike;

import java.awt.Color;

public class Item extends Entity{
	
	private int foodValue;
	public int foodValue() { return foodValue; }
	public void modifyFoodValue(int amt) { foodValue += amt; }
	
	private int attackValue;
    public int attackValue() { return attackValue; }
    public void modifyAttackValue(int amount) { attackValue += amount; }
    
    private int defenseValue;
    public int defenseValue() { return defenseValue; }
    public void modifyDefenseValue(int amount) { defenseValue += amount; }
    
	public Item(char glyph, Color color, String name)
	{
		super(glyph, color, name);
	}

	
}
