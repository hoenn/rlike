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
	private int screenWidth;
	private int screenHeight;
	private List<String> messages;
	private List<String> messageHistory;
	
	public PlayScreen(){
		screenWidth = 80;
		screenHeight = 23;
		messages = new ArrayList<String>();
		messageHistory = new ArrayList<String>();
		createWorld();
		
		CreatureFactory creatureFactory = new CreatureFactory(world);
		createCreatures(creatureFactory);
	}
	
	private void createCreatures(CreatureFactory creatureFactory){
		player = creatureFactory.newPlayer(messages);
		
		for (int z = 0; z < world.depth(); z++){
			for (int i = 0; i < 8; i++){
				creatureFactory.newFungus(z);
			}
		}
	}
	
	private void createWorld(){
		world = new WorldBuilder(90, 32, 5)
					.makeCaves()
					.build();
	}
	
	public int getScrollX() { return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth)); }
	
	public int getScrollY() { return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight)); }
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		int top = getScrollY(); 
		
		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);
		

		String stats = String.format(" %3d/%3d hp", player.hp(), player.maxHp());
		terminal.write(stats, 1, 23);
	}

	private void displayMessages(AsciiPanel terminal, List<String> messages) 
	{
	    int top = screenHeight - messages.size();
	    for (int i = 0; i < messages.size(); i++){
	        terminal.writeCenter(messages.get(i), top + i);
	    }
	    if(messages.size()>0)
	    {
	    	for(String s: messages)
	    	{
	    		messageHistory.add(0, s);
	    		if(messageHistory.size()>20)
	    		{
	    			messageHistory.remove(messageHistory.size()-1);
	    		}
	    	}
	    	messages.clear();
	    }
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {
		for (int x = 0; x < screenWidth; x++){
			for (int y = 0; y < screenHeight; y++){
				int wx = x + left;
				int wy = y + top;

				Creature creature = world.creature(wx, wy, player.z);
				if (creature != null)
					terminal.write(creature.glyph(), creature.x - left, creature.y - top, creature.color());
				else
					terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
			}
		}
	}
	
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
			case KeyEvent.VK_LEFT: player.moveBy(-1, 0, 0); break;
			case KeyEvent.VK_RIGHT: player.moveBy( 1, 0, 0); break;
			case KeyEvent.VK_UP: player.moveBy( 0,-1, 0); break;
			case KeyEvent.VK_DOWN: player.moveBy( 0, 1, 0); break;

			case KeyEvent.VK_H: return new HistoryScreen(messageHistory, this);
		}
		
		switch (key.getKeyChar()){
			case '<': player.moveBy( 0, 0, -1); break;
			case '>': player.moveBy( 0, 0, 1); break;
		}
		
		world.update();
		
		return this;
	}
}

