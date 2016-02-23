package rlike;

import java.util.List;

import asciiPanel.AsciiPanel;

import java.util.List;

import asciiPanel.AsciiPanel;

public class EntityFactory {
	private World world;
	private FieldOfView fov;

	public EntityFactory(World world, FieldOfView fov) {
		this.world = world;
		this.fov = fov;
	}

	// Player
	public Creature newPlayer(List<String> messages) {
		Creature player = new Creature(world, "hero", (char) 3,
				AsciiPanel.brightRed, 100, 7, 1, 9);
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, fov);
		return player;
	}

	// Enemies
	public Creature newFungus(int depth) {
		Creature fungus = new Creature(world, "fungi", (char) 5, AsciiPanel.brightMagenta, 
									10, 0, 3, 9);
		world.addAtEmptyLocation(fungus, depth);
		new FungusAi(fungus, this);
		return fungus;
	}

	public Creature newFungus(int x, int y, int depth) {
		Creature fungus = new Creature(world, "fungi", (char) 5, AsciiPanel.brightMagenta, 
									10, 0, 3, 9);
		world.addAtEmptyLocation(fungus, x, y, depth);
		new FungusAi(fungus, this);
		return fungus;

	}

	public Creature newBat(int depth) {
		Creature bat = new Creature(world, "bat", 'b', AsciiPanel.yellow, 
									15, 10, 0, 5);
		world.addAtEmptyLocation(bat, depth);
		new BatAi(bat);
		return bat;
	}

	public Creature newBat(int x, int y, int depth) {
		Creature bat = new Creature(world, "bat", 'b', AsciiPanel.yellow, 
									15, 10, 0, 5);
		world.addAtEmptyLocation(bat, x, y, depth);
		new BatAi(bat);
		return bat;
	}

	public Creature newGiantRat(int depth, Creature player) {
		Creature rat = new Creature(world, "giant rat", 'r', ExtraColors.darkBrown, 
									25, 12, 5, 5);
		world.addAtEmptyLocation(rat, depth);
		if(Math.random()>.40)
			rat.inventory().add(newCaveCheese(depth));
		new GiantRatAi(rat, player);
		return rat;
	}

	public Creature newTrogg(int depth) {
		Creature trogg = new Creature(world, "trogg", 'g', AsciiPanel.cyan, 
									35, 15, 2, 11);
		trogg.equip(newRandomArmor(depth));
		trogg.equip(newRandomWeapon(depth));
		world.addAtEmptyLocation(trogg, depth);

		new TroggAi(trogg);
		return trogg;
	}

	// Key Creature
	public Creature newDeath(Creature player, int depth) {
		Creature death = new Creature(world, "Dark Lord", (char) 234,
				ExtraColors.mediumOrchid, 100000, 10000, 0, 1000);
		world.addCreatureAtExit(death);
		new DeathAi(death, world, player, depth, this);
		return death;
	}

	// Armor and Weapons
	public Item newKnife(int depth) {
		Item knife = new Item('!', AsciiPanel.white, "knife");
		world.addAtEmptyLocation(knife, depth);
		knife.modifyAttackValue(3);
		knife.modifyThrownAttackValue(13);
		return knife;
	}

	public Item newShortsword(int depth) {
		Item shortSword = new Item('!', ExtraColors.slateGray, "shortsword");
		world.addAtEmptyLocation(shortSword, depth);
		shortSword.modifyThrownAttackValue(3);
		shortSword.modifyAttackValue(5);
		return shortSword;
	}
	public Item newPolearm(int depth) {
		Item polearm = new Item('!', ExtraColors.lightCoral, "polearm");
		world.addAtEmptyLocation(polearm, depth);
		polearm.modifyAttackValue(7);
		polearm.modifyThrownAttackValue(7);
		return polearm;
	}
	public Item newStaff(int depth) {
		Item staff = new Item('!', ExtraColors.rosyBrown, "staff");
		world.addAtEmptyLocation(staff, depth);
		staff.modifyAttackValue(2);
		staff.modifyThrownAttackValue(2);
		staff.modifyDefenseValue(2);
		return staff;
	}

	public Item newEnchantedSword(int depth) {
		Item enchSword = new Item('!', AsciiPanel.brightRed, "enchanted sword");
		world.addAtEmptyLocation(enchSword, depth);
		enchSword.modifyAttackValue((int) (Math.random() * 10) + 5);
		enchSword.modifyDefenseValue((int) (Math.random() * 5));
		enchSword.modifyHpValue((int) ((Math.random() * 200) - 100));
		enchSword.modifyFoodValue((int) (Math.random() * 1000 - 500));
		enchSword.modifyThrownAttackValue((int) (Math.random() * 10) + 5);
		return enchSword;
	}

	public Item newEnchantedArmor(int depth) {
		Item enchArmor = new Item('{', AsciiPanel.brightBlue,
				"enchanted armor");
		world.addAtEmptyLocation(enchArmor, depth);
		enchArmor.modifyDefenseValue((int) (Math.random() * 18));
		enchArmor.modifyThrownAttackValue((int) (Math.random() * 18));
		enchArmor.modifyHpValue((int) ((Math.random() * 200) - 100));
		enchArmor.modifyFoodValue((int) (Math.random() * 1000 - 500));
		return enchArmor;
	}

	public Item newClothArmor(int depth) {
		Item clothArmor = new Item('{', AsciiPanel.white, "cloth armor");
		world.addAtEmptyLocation(clothArmor, depth);
		clothArmor.modifyDefenseValue(2);
		return clothArmor;
	}

	public Item newLeatherArmor(int depth) {
		Item leatherArmor = new Item('{', ExtraColors.olive, "leather armor");
		world.addAtEmptyLocation(leatherArmor, depth);
		leatherArmor.modifyDefenseValue(4);
		leatherArmor.modifyThrownAttackValue(2);
		return leatherArmor;
	}
	public Item newScaleArmor(int depth) {
		Item scaleArmor = new Item('{', AsciiPanel.magenta, "scale mail");
		world.addAtEmptyLocation(scaleArmor, depth);
		scaleArmor.modifyDefenseValue(5);
		scaleArmor.modifyThrownAttackValue(5);
		return scaleArmor;
	}
	public Item newHeavyArmor(int depth) {
		Item heavyArmor = new Item('{', ExtraColors.slateGray, "chain mail");
		world.addAtEmptyLocation(heavyArmor, depth);
		heavyArmor.modifyDefenseValue(7);
		heavyArmor.modifyThrownAttackValue(7);

		return heavyArmor;
	}

	public Item newRandomWeapon(int depth) {
		int i = (int) (Math.random() * 5);
		switch (i) {
		case 0:
			return newShortsword(depth);
		case 1:
			return newStaff(depth);
		case 2:
			return newKnife(depth);
		case 3:
			return newShovel(depth);
		case 4: 
			return newPolearm(depth);
		}
		return null;
	}

	public Item newRandomArmor(int depth) {
		int i = (int) (Math.random() * 4);
		switch (i) {
		case 0:
			return newLeatherArmor(depth);
		case 1:
			return newHeavyArmor(depth);
		case 2:
			return newScaleArmor(depth);
		case 3:
			return newClothArmor(depth);
		}
		return null;
	}

	// Usable items
	public Item newRock(int depth) {
		Item rock = new Item(',', ExtraColors.slateGray, "rock");
		world.addAtEmptyLocation(rock, depth);
		rock.modifyFoodValue(10);
		rock.modifyHpValue(-5);
		rock.modifyAttackValue(2);
		rock.modifyThrownAttackValue(6);
		return rock;
	}

	public Item newHerb(int depth) {
		Item herb = new Item('f', AsciiPanel.green, "herb");
		world.addAtEmptyLocation(herb, depth);
		herb.modifyFoodValue(40);
		herb.modifyHpValue(5);
		return herb;
	}

	public Item newShortbread(int depth) {
		Item sbread = new Item('=', AsciiPanel.white, "shortbread");
		world.addAtEmptyLocation(sbread, depth);
		sbread.modifyFoodValue(250);
		sbread.modifyHpValue(35);
		return sbread;
	}

	public Item newCaveCheese(int depth) {
		Item cCheese = new Item('c', AsciiPanel.yellow, "moldy cave cheese");
		world.addAtEmptyLocation(cCheese, depth);
		cCheese.modifyFoodValue(75);
		cCheese.modifyHpValue(15);
		cCheese.modifyThrownAttackValue(1);
		return cCheese;
	}

	// Key Items
	public Item newShovel(int depth) {
		Item shovel = new Item('1', AsciiPanel.brightYellow, "shovel");
		world.addAtEmptyLocation(shovel, depth);
		shovel.modifyAttackValue(6);
		shovel.modifyThrownAttackValue(4);
		return shovel;
	}

	public Item newVolumeOne(int depth) {
		Item item = new Item((char) 240, AsciiPanel.brightWhite,
				"Maginomicon: Volume 1");
		world.addAtEmptyLocation(item, depth);
		return item;
	}

	public Item newVolumeTwo(int depth) {
		Item item = new Item((char) 240, ExtraColors.wheat,
				"Maginomicon: Volume 2");
		world.addAtEmptyLocation(item, depth);
		return item;
	}

	public Item newVolumeThree(int depth) {
		Item item = new Item((char) 240, ExtraColors.lightCoral,
				"Maginomicon: Volume 3");
		world.addAtEmptyLocation(item, depth);
		return item;
	}

}
