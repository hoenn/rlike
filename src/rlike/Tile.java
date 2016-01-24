package rlike;
import java.awt.Color;

import asciiPanel.AsciiPanel;

public enum Tile
{
	FLOOR((char)250, AsciiPanel.yellow),
	WALL((char)177, AsciiPanel.yellow),
	BOUNDS('x', AsciiPanel.brightBlack);
	
	public final char glyph;
	public final Color color;
	
	Tile(char g, Color c)
	{
		glyph = g;
		color = c;
	}
	public boolean isGround()
	{
		return this !=WALL && this!= BOUNDS;
	}
	public boolean isDiggable() 
	{
		return this == Tile.WALL;
	}
}
