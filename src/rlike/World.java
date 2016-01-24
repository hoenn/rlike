package rlike;
import java.awt.Color;

public class World
{
	private Tile[][] tiles;
	private int width;
	private int height;
	public int width()
	{
		return width;
	}
	public int height()
	{
		return height;
	}
	public World(Tile[][] tiles)
	{
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}
	//Get tile
	public Tile tile(int x, int y)
	{
		if(x<0 || x>= width || y<0 ||y>=height)
			return Tile.BOUNDS;
		else
			return tiles[x][y];
	}
	public void addAtEmptyLocation(Creature creature)
	{
		int x, y;
		
		do
		{
			x = (int) Math.random() * width;
			y = (int) Math.random() * height;
		} while(!tile(x,y).isGround());
		
		creature.x = x;
		creature.y = y;
	}
	public void dig(int x, int y)
	{
		if(tile(x,y).isDiggable())
			tiles[x][y] = Tile.FLOOR;
	}
	public char glyph(int x, int y)
	{
		return tile(x,y).glyph;
	}
	public Color color(int x, int y)
	{
		return tile(x,y).color;
	}
}
