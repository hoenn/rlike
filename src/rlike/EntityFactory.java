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
	public Creature newDeath() {
		Creature death = new Creature(world, "Dark Lord", (char)234, ExtraColors.mediumOrchid, 100000, 10000, 10000, 10000);
		world.addAtEmptyLocation(death,  0);
		new DeathAi(death);
		return death;
	}
	public Item newRock(int depth){
        Item rock = new Item(',', ExtraColors.slateGray, "rock");
        world.addAtEmptyLocation(rock, depth);
        rock.modifyFoodValue(-10);
        return rock;
    }
	public Item newHerb(int depth) {
		Item herb = new Item('f', AsciiPanel.green, "herb");
		world.addAtEmptyLocation(herb, depth);
		herb.modifyFoodValue(10);
		return herb;
	}
	public Item newShortbread(int depth) {
		Item sbread= new Item('=', AsciiPanel.white, "shortbread");
		world.addAtEmptyLocation(sbread, depth);
		sbread.modifyFoodValue(100);
		return sbread;
	}
	public Item newVictoryItem(int depth){
        Item item = new Item((char)1, AsciiPanel.brightWhite, "Volume 1");
        world.addAtEmptyLocation(item, depth);
        return item;
    }

}
