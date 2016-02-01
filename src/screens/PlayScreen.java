package screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.Creature;
import rlike.EntityFactory;
import rlike.FieldOfView;
import rlike.World;
import rlike.WorldBuilder;


public class PlayScreen implements Screen {
	private World world;
	private Screen subScreen;
	private Creature player;
	private int screenWidth;
	private int screenHeight;
	private List<String> messages;
	private List<String> messageHistory;
	private FieldOfView fov;
	
	public PlayScreen(){
		screenWidth = 80;
		screenHeight = 23;
		subScreen = null;
		messages = new ArrayList<String>();
		messageHistory = new ArrayList<String>();
		createWorld();
		
		EntityFactory entityFactory = new EntityFactory(world, fov);
		createCreatures(entityFactory);
		createItems(entityFactory);
	}
	
	private void createCreatures(EntityFactory entityFactory){
		player = entityFactory.newPlayer(messages);
		
		for (int z = 0; z < world.depth(); z++){
			for (int i = 0; i < 8; i++){
				entityFactory.newFungus(z);
			}
			for (int i = 0; i < 20; i++){
			    entityFactory.newBat(z);
			}
		}	
	}
	private void createItems(EntityFactory factory) {
	    for (int z = 0; z < world.depth(); z++){
	        for (int i = 0; i < world.width() * world.height() / 20; i++){
	            factory.newRock(z);
	        }
	    }
	}
	
	private void createWorld(){
		world = new WorldBuilder(90, 32, 5)
					.makeCaves()
					.build();
		fov = new FieldOfView(world);
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
		
		if (subScreen != null)
		    subScreen.displayOutput(terminal);
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
	    fov.update(player.x, player.y, player.z, player.visionRadius());
	    
	    for (int x = 0; x < screenWidth; x++){
	        for (int y = 0; y < screenHeight; y++){
	            int wx = x + left;
	            int wy = y + top;

	            if (player.canSee(wx, wy, player.z))
	                terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
	            else
	                terminal.write(fov.tile(wx, wy, player.z).glyph(), x, y, Color.darkGray);
	        }
	    }
	}
	
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if (subScreen != null) {
	         subScreen = subScreen.respondToUserInput(key);
	     }
		else
			switch (key.getKeyCode()){
			case KeyEvent.VK_LEFT: player.moveBy(-1, 0, 0); break;
			case KeyEvent.VK_RIGHT: player.moveBy( 1, 0, 0); break;
			case KeyEvent.VK_UP: player.moveBy( 0,-1, 0); break;
			case KeyEvent.VK_DOWN: player.moveBy( 0, 1, 0); break;

			case KeyEvent.VK_H: return new HistoryScreen(messageHistory, this);
			case KeyEvent.VK_D: subScreen = new DropScreen(player); break;
		}
		
		switch (key.getKeyChar()){
	        case 'g': player.pickUp(); break;
	        case '<': player.moveBy( 0, 0, -1); break;
	        case '>': player.moveBy( 0, 0, 1); break;
        
		}
		if(subScreen == null)
			world.update();
		if (player.hp() < 1)
		    return new StartScreen();
		return this;
	}
}

