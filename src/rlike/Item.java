package rlike;

import java.awt.Color;

public class Item extends Entity {

	private int foodValue;
	public int foodValue() {
		return foodValue;
	}
	public void modifyFoodValue(int amt) {
		foodValue += amt;
	}

	private int hpValue;
	public int hpValue() {
		return hpValue;
	}
	public void modifyHpValue(int amt) {
		hpValue += amt;
	}

	private int attackValue;
	public int attackValue() {
		return attackValue;
	}
	public void modifyAttackValue(int amount) {
		attackValue += amount;
	}

	private int defenseValue;
	public int defenseValue() {
		return defenseValue;
	}
	public void modifyDefenseValue(int amount) {
		defenseValue += amount;
	}

	public Item(char glyph, Color color, String name) {
		super(glyph, color, name);
	}

	public String info() {
		String info = "";
		if (name.contains("enchanted")) {
			info += "   attack: ??   defense: ??   food: ??   hp: ??";
		} else {
			if (attackValue != 0)
				info += "   attack:" + attackValue;

			if (defenseValue != 0)
				info += "   defense:" + defenseValue;

			if (foodValue != 0)
				info += "   food:" + foodValue;
			
			if (hpValue != 0)
				info += "   hp:" + hpValue;
		}

		return info;
	}

}
