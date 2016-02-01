package rlike;

import java.util.List;

import asciiPanel.AsciiPanel;

import java.util.List;

import asciiPanel.AsciiPanel;

public class EntityFactory {
	private World world;
	private FieldOfView fov;
	
	public EntityFactory(World world, FieldOfView fov){
		this.world = world;
		this.fov = fov;
	}
	
	public Creature newPlayer(List<String> messages){
		Creature player = new Creature(world, "", (char)3, AsciiPanel.brightRed, 100, 20, 5, 9);
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, fov);
		return player;
	}
	
	public Creature newFungus(int depth){
		Creature fungus = new Creature(world, "fungi", (char)5, AsciiPanel.brightMagenta, 10, 0, 0, 9);
		world.addAtEmptyLocation(fungus, depth);
		new FungusAi(fungus, this);
		return fungus;
	}
	public Creature newBat(int depth){
	    Creature bat = new Creature(world, "bat", 'b', AsciiPanel.yellow, 15, 5, 0, 5);
	    world.addAtEmptyLocation(bat, depth);
	    new BatAi(bat);
	    return bat;
	}
	public Item newRock(int depth){
        Item rock = new Item(',', AsciiPanel.green, "rock");
        world.addAtEmptyLocation(rock, depth);
        return rock;
    }
	public Item newVictoryItem(int depth){
        Item item = new Item((char)1, AsciiPanel.brightWhite, "Volume 1");
        world.addAtEmptyLocation(item, depth);
        return item;
    }

}
