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
		Creature player = new Creature(world, (char)3, AsciiPanel.brightRed);
		world.addAtEmptyLocation(player);
		new PlayerAi(player);
		return player;
	}
	public Creature newFungus()
	{
		Creature fungus = new Creature(world, (char)5, AsciiPanel.magenta);
		world.addAtEmptyLocation(fungus);
		new FungusAi(fungus, this);
		return fungus;
	}
}
