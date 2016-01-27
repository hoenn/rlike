package rlike;

import java.util.List;

import asciiPanel.AsciiPanel;

import java.util.List;

import asciiPanel.AsciiPanel;

public class CreatureFactory {
	private World world;
	private FieldOfView fov;
	
	public CreatureFactory(World world, FieldOfView fov){
		this.world = world;
		this.fov = fov;
	}
	
	public Creature newPlayer(List<String> messages){
		Creature player = new Creature(world, (char)3, AsciiPanel.brightRed, 100, 20, 5, 9);
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, fov);
		return player;
	}
	
	public Creature newFungus(int depth){
		Creature fungus = new Creature(world, (char)5, AsciiPanel.brightMagenta, 10, 0, 0, 9);
		world.addAtEmptyLocation(fungus, depth);
		new FungusAi(fungus, this);
		return fungus;
	}
}
