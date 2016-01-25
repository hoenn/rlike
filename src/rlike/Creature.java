package rlike;

import java.awt.Color;

public class Creature
{
	private World world;
	
	public int x;
	public int y;
	
	
	private CreatureAi ai;
	public void setCreatureAi(CreatureAi ai) {
		this.ai = ai;
	}
	
	private int maxHp;
	public int maxHp() {
		return maxHp;
	}
	
	private int hp;
	public int hp() {
		return hp;
	}
	
	private int attackValue;
	public int attackValue() {
		return attackValue;
	}
	
	private int defenseValue;
	public int defenseValue() {
		return defenseValue;
	}
	
	private char glyph;
	public char glyph(){
		return glyph;	
	}
	
	private Color color;
	public Color color(){
		return color;
	}
	public void dig(int wx, int wy)
	{
		world.dig(wx, wy);
	}
	
	public void moveBy(int mx, int my)
	{
		Creature other = world.creature(x+mx, y+my);
		if(other == null)
			ai.onEnter(x+mx, y+my, world.tile(x+mx,  y+my));
		else
			attack(other);
	}
	public void attack(Creature other){
        int amount = Math.max(0, attackValue() - other.defenseValue());
    
        amount = (int)(Math.random() * amount) + 1;
    
        other.modifyHp(-amount);
    }

    public void modifyHp(int amount) {
        hp += amount;
    
        if (hp < 1)
         world.remove(this);
    }
	public void update()
	{
		ai.onUpdate();
	}
	public boolean canEnter(int wx, int wy) 
	{
		return world.tile(wx, wy).isGround() && world.creature(wx, wy) == null;
	}
	public Creature(World world, char glyph, Color color, int maxHp, int aV, int dV)
	{
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = aV;
		this.defenseValue = dV;
	}
	
}
