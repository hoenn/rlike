package screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.Creature;
import rlike.CreatureFactory;
import rlike.World;
import rlike.WorldBuilder;

public class PlayScreen implements Screen {
	private World world;
	
	private Creature player;
	
	private List<String> messages;
	
	private int screenWidth;
	private int screenHeight;
	
	public PlayScreen() {
		screenWidth = 80;
		screenHeight = 21;
		createWorld();
		messages = new ArrayList<String>();
		CreatureFactory creatureFactory = new CreatureFactory(world);
		createCreatures(creatureFactory);
		
	}
	public void createCreatures(CreatureFactory cf)
	{
		player = cf.newPlayer(messages);
		for(int i =0; i< 30; i++)
		{
			cf.newFungus();
		}
	}
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		
		int top = getScrollY();
		displayTiles(terminal, left, top);
		
		String stats = String.format(" %3d/%3d hp", player.hp(), player.maxHp());
		terminal.write(stats, 1, 23);
		 
		displayMessages(terminal, messages);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
			case KeyEvent.VK_LEFT:
	        case KeyEvent.VK_H: player.moveBy(-1, 0); break;
	        case KeyEvent.VK_RIGHT:
	        case KeyEvent.VK_L: player.moveBy( 1, 0); break;
	        case KeyEvent.VK_UP:
	        case KeyEvent.VK_K: player.moveBy( 0,-1); break;
	        case KeyEvent.VK_DOWN:
	        case KeyEvent.VK_J: player.moveBy( 0, 1); break;
	        case KeyEvent.VK_Y: player.moveBy(-1,-1); break;
	        case KeyEvent.VK_U: player.moveBy( 1,-1); break;
	        case KeyEvent.VK_B: player.moveBy(-1, 1); break;
	        case KeyEvent.VK_N: player.moveBy( 1, 1); break;
        }
		world.update();
        return this;
	}
	private void displayMessages(AsciiPanel terminal, List<String> messages) 
	{
	    int top = screenHeight - messages.size();
	    for (int i = 0; i < messages.size(); i++){
	        terminal.writeCenter(messages.get(i), top + i);
	    }
	    if(messages.size()>0)
	    messages.clear();
	}
	private void displayTiles(AsciiPanel terminal, int left, int top)
	{
		for(int x=0; x < screenWidth; x++) {
			for(int y = 0; y < screenHeight; y++) {
				int wx = x+left;
				int wy = y+top;
				
				terminal.write(world.glyph(wx,  wy), x, y, world.color(wx,  wy));

			}
		}
		for(Creature c: world.creatures())
		{
			if((c.x >= left && c.x < left+screenWidth) && (c.y>=top && c.y < top+screenHeight))
			{
				terminal.write(c.glyph(), c.x-left, c.y-top, c.color());
			}
		}
	}
	public int getScrollX()	{
		return Math.max(0,  Math.min(player.x - screenWidth/2, world.width() - screenWidth));
	}
	public int getScrollY() {
		return Math.max(0,  Math.min(player.y - screenHeight/2, world.height() - screenHeight));
	}
	private void createWorld() {
		world = new WorldBuilder(90, 32).makeCaves().build();
		
	}

}
