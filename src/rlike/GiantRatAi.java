package rlike;

public class GiantRatAi extends CreatureAi
{
	private Creature player;
	public GiantRatAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}
	
	public void onUpdate() {
		if(canPickUp()) {
			creature.pickUp();
		}
		if(creature.canSee(player.x, player.y, player.z))
			chase(player);
		else {
			wander();
		}
	}
	
	public boolean canPickUp() {
		if(super.canPickUp()) {
			String itemName = creature.item(creature.x, creature.y, creature.z).name;
			return (itemName.contains("fungi") || itemName.contains("cheese"));
		}
		else
			return false;
	}
}
