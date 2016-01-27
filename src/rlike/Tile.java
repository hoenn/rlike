package rlike;
import java.awt.Color;

import asciiPanel.AsciiPanel;

import java.awt.Color;
import asciiPanel.AsciiPanel;

public enum Tile {
	FLOOR((char)250, AsciiPanel.yellow),
	WALL((char)177, ExtraColors.darkBrown),
	BOUNDS('x', AsciiPanel.brightBlack), 
	
	STAIRS_DOWN('>', AsciiPanel.white), 
	STAIRS_UP('<', AsciiPanel.white), 
	
	UNSEEN(' ', AsciiPanel.white);
	
	private char glyph;
	public char glyph() { return glyph; }
	
	private Color color;
	public Color color() { return color; }
	
	Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}

	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}

	public boolean isDiggable() {
		return this == Tile.WALL;
	}
}
