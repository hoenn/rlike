package rlike;
import java.awt.Color;

public class Creature extends Entity{
	private World world;
	
	public int x;
	public int y;
	public int z;
	
	private int visionRadius;
	public int visionRadius() { return visionRadius; }


	private CreatureAi ai;
	public void setCreatureAi(CreatureAi ai) { this.ai = ai; }
	
	private int maxHp;
	public int maxHp() { return maxHp; }
	
	private int hp;
	public int hp() { return hp; }
	
	private int attackValue;
	public int attackValue() { return attackValue; }

	private int defenseValue;
	public int defenseValue() { return defenseValue; }
	
	private Inventory inventory;
	public Inventory inventory() { return inventory; }
	
	public Creature(World world, String name,  char glyph, Color color, int maxHp, int attack, int defense, int visionRadius){
		super(glyph, color, name);
		this.world = world;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = attack;
		this.defenseValue = defense;
		this.inventory = new Inventory(15);
		this.visionRadius = visionRadius;
	}
	public void pickUp() {
		Item item = world.item(x, y,  z);

		if(item == null) {
			doAction("grab at ground");
		}
		else if(inventory.isFull()) {	
			doAction("rethink that. Inventory "+inventory.getSize()+"/"+inventory.getSize());
		}
		else
		{
			world.remove(x, y, z);
			inventory.add(item);
			doAction("pickup a %s. Inventory " +inventory.getSize()+"/"+inventory.getItems().length, item.name());

		}
	}
	public void drop(Item item){
	    if (world.addAtEmptySpace(item, x, y, z)){
	         doAction("drop a " + item.name());
	         inventory.remove(item);
	    } else {
	         notify("There's nowhere to drop the %s.", item.name());
	    }
	}
	public boolean canSee(int wx, int wy, int wz)
	{
		return ai.canSee(wx, wy ,wz);
	}
	public Tile tile(int wx, int wy, int wz)
	{
		return world.tile(wx, wy, wz);
	}
	public void moveBy(int mx, int my, int mz){
		if (mx==0 && my==0 && mz==0)
		    return;
		Tile tile = world.tile(x+mx, y+my, z+mz);
		
		if (mz == -1){
			if (tile == Tile.STAIRS_DOWN) {
				doAction("walk up the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go up but are stopped by the cave ceiling");
				return;
			}
		} else if (mz == 1){
			if (tile == Tile.STAIRS_UP) {
				doAction("walk down the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go down but are stopped by the cave floor");
				return;
			}
		}
		
		Creature other = world.creature(x+mx, y+my, z+mz);
		
		if (other == null)
			ai.onEnter(x+mx, y+my, z+mz, tile);
		else
			attack(other);
	}
	public Creature creature(int wx, int wy, int wz) {
	    return world.creature(wx, wy, wz);
	}
	public void attack(Creature other){
		int amount = Math.max(0, attackValue() - other.defenseValue());
		
		amount = (int)(Math.random() * amount) + 1;
		
		doAction("attack the '%s' for %d damage", other.name, amount);
		
		other.modifyHp(-amount);
	}

	public void modifyHp(int amount) { 
		hp += amount;
		
		if (hp < 1) {
			doAction("die");
			world.remove(this);
		}
	}
	
	public void dig(int wx, int wy, int wz) {
		world.dig(wx, wy, wz);
		doAction("dig");		
	}
	
	public void update(){
		ai.onUpdate();
	}

	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
	}

	public void notify(String message, Object ... params){
		ai.onNotify(String.format(message, params));
	}
	
	public void doAction(String message, Object ... params){
	    int r = 9;
	    for (int ox = -r; ox < r+1; ox++){
	        for (int oy = -r; oy < r+1; oy++){
	            if (ox*ox + oy*oy > r*r)
	                continue;
	         
	            Creature other = world.creature(x+ox, y+oy, z);
	         
	            if (other == null)
	                continue;
	         
	            if (other == this)
	                other.notify("You " + message + ".", params);
	            else if (other.canSee(x, y, z))
	                other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
	         }
	    }
	}

	
	private String makeSecondPerson(String text){
		String[] words = text.split(" ");
		words[0] = words[0] + "s";
		
		StringBuilder builder = new StringBuilder();
		for (String word : words){
			builder.append(" ");
			builder.append(word);
		}
		
		return builder.toString().trim();
	}
}
