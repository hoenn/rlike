package rlike;

public class TroggAi extends CreatureAi {
	private Creature target = null;
	public TroggAi(Creature creature) {
		super(creature);
	}
	public void onUpdate() {
		if (target==null)
		{
			//Walk and search for new target
			wander();			
			target = seekTarget();
			if(target!=null && target.glyph == creature.glyph)
			{
				target = null;
			}
		}
		else {
			//Check if location is visible and creature isn't dead
			if(creature.canSee(target.x, target.y, target.z) && creature.creature(target.x, target.y, target.z)!=null) {			
				chase(target);
			}
			else
				target = null;
		}	
		//Grab and corpses walked past
		if(canPickUp())
		{
			creature.pickUp();
				
		}
	}
	public boolean canPickUp() {
		return super.canPickUp() && (creature.item(creature.x, creature.y, creature.z).name.contains("corpse")
				||creature.item(creature.x, creature.y, creature.z).name.contains("Maginomicon"));
	}
}
