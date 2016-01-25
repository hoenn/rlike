package rlike;

import asciiPanel.AsciiPanel;

public class CreatureFactory
{
	private World world;
	
	public CreatureFactory(World world)
	{
		this.world = world;
	}
	public Creature newPlayer()
	{
		Creature player = new Creature(world, (char)3, AsciiPanel.brightRed, 100, 20, 5);
		world.addAtEmptyLocation(player);
		new PlayerAi(player);
		return player;
	}
	public Creature newFungus()
	{
		Creature fungus = new Creature(world, (char)5, AsciiPanel.magenta, 10, 0, 0);
		world.addAtEmptyLocation(fungus);
		new FungusAi(fungus, this);
		return fungus;
	}
}
