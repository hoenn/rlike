package rlike;

import java.awt.Color;
import java.util.List;

import java.util.List;

public class PlayerAi extends CreatureAi {

	private List<String> messages;
	private FieldOfView fov;
	
	public PlayerAi(Creature creature, List<String> messages, FieldOfView fov) {
		super(creature);
		this.messages = messages;
		this.fov = fov;
	}
	public boolean canSee(int wx, int wy, int wz) {
	    return fov.isVisible(wx, wy, wz);
	}
	public void onEnter(int x, int y, int z, Tile tile){
		if (tile.isGround()){
			creature.x = x;
			creature.y = y;
			creature.z = z;
		} else if (tile.isDiggable()) {
			creature.dig(x, y, z);
		}
	}
	public void onUpdate() {
		if(creature.fortitudeCount>0) {
			creature.fortitudeCount--;	
			if(creature.fortitudeCount == 0) {
				creature.notify("Your fortitude depletes. You are restored to your regular constitution");
				if(creature.color == Color.GREEN)
					creature.color = Color.RED;
				creature.setHp(creature.maxHp());
			}
		}
		if(creature.godModeCount>0) {
			creature.godModeCount--;
			if(creature.godModeCount ==0) {
				creature.notify("Your blessing fades. Your regular strength returns");
				creature.color = Color.RED;
				creature.modifyAttackValue(-20000);
			}
		}
		if(creature.protectedCount>0) {
			creature.protectedCount--;
			if(creature.protectedCount ==0) {
				creature.notify("Your protection fades");
				if(creature.color==Color.WHITE)
					creature.color = Color.RED;
				creature.setProtected(false);
			}
		}
			
	}
	public void onLevelUp() {
		
	}
	public void onNotify(String message){
		messages.add(message);
	}
	public Tile rememberedTile(int wx, int wy, int wz) {
        return fov.tile(wx, wy, wz);
    }
}
