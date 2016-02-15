package screens;

import java.awt.event.KeyEvent;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.Creature;
import rlike.LevelUpManager;

public class LevelUpScreen implements Screen {
	private LevelUpManager manager;
	private Creature player;
	private int picks;
	
	public LevelUpScreen(Creature player, int picks){
		this.manager = new LevelUpManager();
		this.player = player;
		this.picks = picks;
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.write("LEVEL UP", 0,0, AsciiPanel.brightGreen);

		List<String> options = manager.getLevelUpOptions();
		
		int y = 5;
		terminal.clear(' ', 5, y, 30, options.size() + 2);
		terminal.write("    Choose a level up bonus    ", 5, y++);
		terminal.write("-*--*--*--*--*--*--*--*--*--*-", 5, y++);
		
		for (int i = 0; i < options.size(); i++){
			terminal.write(String.format("[%d] %s", i+1, options.get(i)), 7, y++);
		}
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		List<String> options = manager.getLevelUpOptions();
		String chars = "";
		
		for (int i = 0; i < options.size(); i++){
			chars = chars + Integer.toString(i+1);
		}
		
		int i = chars.indexOf(key.getKeyChar());
		
		if (i < 0)
			return this;
		
		manager.getLevelUpOption(options.get(i)).invoke(player);
		
		if (--picks < 1)
			return null;
		else
			return this;
	}
}
