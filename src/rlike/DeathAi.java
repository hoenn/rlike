package rlike;
public class DeathAi extends CreatureAi {	
	private Creature player;
	private String[] messages = {
			"The monsters below grow impatient",
			"The walls are brittle",
			"Come hither, foul hero",
			"How long will you ignore me?",
			"The tomes are lost in time",
			"You won't find all three volumes",
			"You are the monster",
			"Face me",
			"Your soul withers in the dungeon",
			"Find me on the floor from which you came",
			"I am at the height of the dungeon",
			"I await you",
			"As you wander the dungeon grows stronger"
	};
	public DeathAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}

	public void onUpdate(){
		if(Math.random() < .03) {
			sendPlayerRandomMessage();
		}
	}
	public void sendPlayerRandomMessage() {
		player.notify((char)234+" "+messages[(int)(Math.random()*messages.length)]+" "+(char)234);
	}
	

}
