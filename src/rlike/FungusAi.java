package rlike;

public class FungusAi extends CreatureAi {
	private EntityFactory factory;
	private boolean hasSpread;
	
	public FungusAi(Creature creature, EntityFactory factory) {
		super(creature);
		this.factory = factory;
	}

	public void onUpdate(){
		if (!hasSpread  && Math.random() < 0.008)
			spread();
	}
	
	private void spread(){
		int x = creature.x + (int)(Math.random() * 11) - 5;
		int y = creature.y + (int)(Math.random() * 11) - 5;
		
		if (!creature.canEnter(x, y, creature.z))
			return;
		
		creature.doAction("spawn a child");
		
		Creature child = factory.newFungus(creature.z);
		child.x = x;
		child.y = y;
		child.z = creature.z;
		hasSpread = true;
	}
}
