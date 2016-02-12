package rlike;
import java.awt.Color;

import asciiPanel.AsciiPanel;

import java.awt.Color;
import asciiPanel.AsciiPanel;

public enum Tile {
	FLOOR((char)250, ExtraColors.mediumOrchid, "The dungeon floor"),
	WALL((char)177, ExtraColors.mediumPurple, "Brittle brick walls"),
	BOUNDS('x', AsciiPanel.brightBlack, "The abyss"), 
	
	STAIRS_DOWN('>', AsciiPanel.white, "Stairs down"), 
	STAIRS_UP('<', AsciiPanel.white, "Stairs up"), 
	
	UNSEEN(' ', AsciiPanel.white, "Unseen");
	
	private char glyph;
	public char glyph() { return glyph; }
	
	private Color color;
	public Color color() { return color; }
	
	private String info;
	public String info() { return info; }
	
	Tile(char glyph, Color color, String info){
		this.glyph = glyph;
		this.color = color;
		this.info = info;
	}

	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}

	public boolean isDiggable() {
		return this == Tile.WALL;
	}
}
