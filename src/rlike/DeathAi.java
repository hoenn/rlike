package rlike;
public class DeathAi extends CreatureAi {	
	private Creature player;
	private int worldDepth;
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
	public DeathAi(Creature creature, Creature player, int depth) {
		super(creature);
		this.player = player;
		this.worldDepth = depth;
	}

	public void onUpdate(){
		//Should do other things
		//Upgrade monsters in the dungeon?
		//Hurt the player
		//Teleport the player
		//Give player shady items
		if(Math.random() < 0.03) {
			sendPlayerRandomMessage();
		}
		if(Math.random() < 0.005) {
			
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
		
	}
	public void sendPlayerRandomMessage() {
		sendPlayerMessage(messages[(int)(Math.random()*messages.length)]);
		
	}
	public void sendPlayerMessage(String msg) {
		player.notify("Death whispers to you");
		player.notify((char)234+" "+msg+" "+(char)234);
	}
	

}
