package rlike;

public class TroggAi extends CreatureAi {
	private Creature target = null;
	public TroggAi(Creature creature) {
		super(creature);
	}
	public void onUpdate() {
		if (target==null)
		{
			wander();
			target = seekTarget();
		}
		else {
			//Check if location is visible and creature isn't dead
			if(creature.canSee(target.x, target.y, target.z) && creature.creature(target.x, target.y, target.z)!=null) {			
				chase(target);
			}
			else
				target = null;
		}	
	}
}
