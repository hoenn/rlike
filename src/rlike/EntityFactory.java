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
	//Player
	public Creature newPlayer(List<String> messages){
		Creature player = new Creature(world, "hero", (char)3, AsciiPanel.brightRed, 100, 20, 5, 9);
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, fov);
		return player;
	}
	
	//Enemies
	public Creature newFungus(int depth){
		Creature fungus = new Creature(world, "fungi", (char)5, AsciiPanel.brightMagenta, 10, 0, 3, 9);
		world.addAtEmptyLocation(fungus, depth);
		new FungusAi(fungus, this);
		return fungus;
	}
	public Creature newFungus(int x, int y, int depth) {
		Creature fungus = new Creature(world, "fungi", (char)5, AsciiPanel.brightMagenta, 10, 0, 3, 9);
		world.addAtEmptyLocation(fungus, x, y, depth);
		new FungusAi(fungus, this);
		return fungus;
		
	}
	public Creature newBat(int depth){
	    Creature bat = new Creature(world, "bat", 'b', AsciiPanel.yellow, 15, 15, 0, 5);
	    world.addAtEmptyLocation(bat, depth);
	    new BatAi(bat);
	    return bat;
	}
	public Creature newGiantRat(int depth, Creature player) {
		Creature rat = new Creature(world, "giant rat", 'r', ExtraColors.darkBrown, 25, 15, 5, 5);
		world.addAtEmptyLocation(rat,  depth);
		new GiantRatAi(rat, player);
		return rat;
	}
	public Creature newTrogg(int depth) {
		Creature trogg = new Creature(world, "trogg", 'g', AsciiPanel.cyan, 10, 25, 5, 15);
		world.addAtEmptyLocation(trogg, depth);
		new TroggAi(trogg);
		return trogg;
	}
	
	//Key Creature
	public Creature newDeath(Creature player, int depth) {
		Creature death = new Creature(world, "Dark Lord", (char)234, ExtraColors.mediumOrchid, 100000, 10000, 10000, 10000);
		world.addAtEmptyLocation(death,  0);
		new DeathAi(death, player, depth, this);
		return death;
	}
	
	//Armor and Weapons
	public Item newKnife(int depth) {
		Item knife = new Item('!', AsciiPanel.white, "knife");
		world.addAtEmptyLocation(knife, depth);
		knife.modifyAttackValue(3);
		return knife;
	}
	public Item newShortsword(int depth) {
		Item shortSword = new Item('!', ExtraColors.slateGray, "shortsword");
		world.addAtEmptyLocation(shortSword, depth);
		shortSword.modifyAttackValue(7);
		return shortSword;
	}
	public Item newStaff(int depth) {
		Item staff = new Item('!', ExtraColors.rosyBrown, "staff");
		world.addAtEmptyLocation(staff, depth);
		staff.modifyAttackValue(4);
		staff.modifyDefenseValue(3);
		return staff;
	}
	public Item newEnchantedSword(int depth) {
		Item enchSword = new Item('!', AsciiPanel.brightRed, "enchanted sword");
		world.addAtEmptyLocation(enchSword, depth);
		enchSword.modifyAttackValue((int)(Math.random()*10)+10);
		enchSword.modifyDefenseValue((int)(Math.random()*10));
		return enchSword;
	}
	public Item newEnchantedArmor(int depth) {
		Item enchArmor = new Item('{', AsciiPanel.brightBlue, "enchanted armor");
		world.addAtEmptyLocation(enchArmor, depth);
		enchArmor.modifyDefenseValue((int)(Math.random()*25));
		return enchArmor;
	}
	public Item newClothArmor(int depth) {
		Item clothArmor = new Item('{', AsciiPanel.white, "cloth armor");
		world.addAtEmptyLocation(clothArmor, depth);
		clothArmor.modifyDefenseValue(3);
		return clothArmor;
	}
	public Item newLeatherArmor(int depth) {
		Item leatherArmor = new Item('{', ExtraColors.olive, "light armor");
		world.addAtEmptyLocation(leatherArmor,  depth);
		leatherArmor.modifyDefenseValue(8);
		return leatherArmor;
	}
	public Item newHeavyArmor(int depth) {
		Item heavyArmor = new Item('{', ExtraColors.slateGray, "chain mail");
		world.addAtEmptyLocation(heavyArmor, depth);
		heavyArmor.modifyDefenseValue(15);
		return heavyArmor;
	}
	public Item newRandomWeapon(int depth) {
		int i = (int)(Math.random()*4);
		switch(i) {
			case 0: return newShortsword(depth);
			case 1: return newStaff(depth);
			case 2: return newKnife(depth);
			case 3: return newShovel(depth);
		}
		return null;
	}
	public Item newRandomArmor(int depth) {
		int i = (int)(Math.random()*3);
		switch(i) {
			case 0: return newLeatherArmor(depth);
			case 1: return newHeavyArmor(depth);
			case 2: return newClothArmor(depth);
		}
		return null;
	}
	
	
	//Usable items
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
	
	//Key Items
	public Item newShovel(int depth) {
		Item shovel = new Item('1', AsciiPanel.brightYellow, "shovel");
		world.addAtEmptyLocation(shovel, depth);
		shovel.modifyAttackValue(5);
		return shovel;
	}
	public Item newVolumeOne(int depth){
        Item item = new Item((char)240, AsciiPanel.brightWhite, "mysterious book: Volume 1");
        world.addAtEmptyLocation(item, depth);
        return item;
    }
	public Item newVolumeTwo(int depth){
        Item item = new Item((char)240, ExtraColors.wheat, "mysterious book: Volume 2");
        world.addAtEmptyLocation(item, depth);
        return item;
    }
	public Item newVolumeThree(int depth){
        Item item = new Item((char)240, ExtraColors.lightCoral, "mysterious book: Volume 3");
        world.addAtEmptyLocation(item, depth);
        return item;
    }

}
