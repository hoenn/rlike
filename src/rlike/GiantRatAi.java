package rlike;

public class GiantRatAi extends CreatureAi
{
	private Creature player;
	public GiantRatAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}
	
	public void onUpdate() {
		if(creature.canSee(player.x, player.y, player.z))
			chase(player);
		else
			wander();
	}
}
