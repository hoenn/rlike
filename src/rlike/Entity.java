package rlike;

import java.awt.Color;

public class Entity {
	protected char glyph;
	public char glyph() {
		return glyph;
	}
	protected Color color;
	public Color color() {
		return color;
	}
	protected String name;
	public String name() {
		return name;
	}
	
	public Entity(char glyph, Color color, String name) {
		this.glyph = glyph;
		this.color = color;
		this.name = name;
	}
}
