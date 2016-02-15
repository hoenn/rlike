package rlike;

import java.util.List;

public class DeathAi extends CreatureAi {	
	private Creature player;
	private EntityFactory factory;
	private int worldDepth;
	private boolean hasBeenHit = false;
	private String[] messages = {
			"The monsters below grow impatient",
			"The walls are brittle.",
			"Come hither, foul hero.",
			"How long will you ignore me?",
			"The tomes are lost in time.",
			"You won't find all three volumes.",
			"You efforts are wasted.",
			"Face me",
			"Your soul withers in the dungeon",
			"Find me on the floor from which you came",
			"I am at the height of the dungeon",
			"I await you",
			"As you wander the dungeon grows stronger"
	};
	public DeathAi(Creature creature, Creature player, int depth, EntityFactory factory) {
		super(creature);
		this.player = player;
		this.worldDepth = depth;
		this.factory = factory;
		if(player.z<2) {
			player.notify("Your vision goes black");
			sendPlayerMessage("I wanted to see your struggle firsthand");
			player.teleport(this.creature.x, this.creature.y+1, this.creature.z);
		}
	}

	public void onUpdate(){
		//If hit, retaliate
		if(creature.hp()<creature.maxHp() && !hasBeenHit) {
			sendPlayerMessage("You fool. To think you could face the god of death");
			player.modifyHp(-1000);
		}
		else if(creature.hp() < creature.maxHp() && hasBeenHit) {
			sendPlayerMessage("What sorcery is this?!");
		}
		if(Math.random() < 0.003) {
			sendPlayerRandomMessage();	
		}
		else if(Math.random() < 0.0005 && player.z>0) {
			switch((int)(Math.random()*5)) {
				case 0: //Teleport player vertically
						if(player.z>0) {
							int teleportZ = (int) (Math.random()* worldDepth);
							if(teleportZ>player.z)
								player.notify("Death attempts to steal you to the depths below");
							else
								player.notify("Death attempts to bring you ever closer");
							
							//Teleport fails
							if(player.z == teleportZ) {
								player.notify("You feel cold hands about you but remain steadfast");
								sendPlayerMessage("Not so easily will you escape me next time");
							}
							else {
								player.notify("You succumb to the strength of a god");
								sendPlayerMessage("I can so easily grip your soul");
								player.teleport(player.x, player.y, teleportZ);
								
							}
						}
						break;
				case 1: //Sprout mushrooms around player
						player.notify("You see mushrooms rapidly sprout at your feet");
						sendPlayerMessage("Death and decay");
						player.modifyHp(-15);
						List<Point> surroundingTiles = player.getSurroundingTiles();
						for(Point p: surroundingTiles) {
							factory.newFungus(p.x, p.y, player.z);
						}
						break;
				case 2://Spawn bats around player
						player.notify("A flurry of wings flap rapidly around you");
						sendPlayerMessage("My dear Dracul sends his regards");
						player.modifyHp(-15);
						List<Point> surroundingTiles1 = player.getSurroundingTiles();
						for(Point p: surroundingTiles1) {
							factory.newBat(p.x, p.y, player.z);
						}
						break;				
				case 3: //Teleport player to Death
						if(player.z<2) {
							player.notify("Your vision goes black");
							sendPlayerMessage("I wanted to see your struggle firsthand");
							player.teleport(this.creature.x, this.creature.y+1, this.creature.z);
						}
						break;
				case 4: //Spawn three troggs into players level
						factory.newTrogg(player.z);
						factory.newTrogg(player.z);
						factory.newTrogg(player.z);
						player.notify("You hear a frightening growl and other wretched growls in response");
						sendPlayerMessage("Ogr"+(char)(130)+"moch sends his regards");
						break;
				
				
			}			
		}
	}
	public void sendPlayerRandomMessage() {
		sendPlayerMessage(messages[(int)(Math.random()*messages.length)]);
		
	}
	public void sendPlayerMessage(String msg) {
		player.notify("Death whispers to you");
		player.notify((char)234+" "+msg+" "+(char)234);
	}
	

}
