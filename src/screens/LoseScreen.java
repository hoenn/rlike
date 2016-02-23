package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;
import rlike.Stats;

public class LoseScreen implements Screen{
	private int numberOfTurns;
	private String causeOfDeath;
	private Stats stats;
	public LoseScreen(int numberOfTurns, String causeOfDeath, Stats stats){
		this.numberOfTurns = numberOfTurns;
		this.causeOfDeath = causeOfDeath;
		this.stats = stats;
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU DIED", 10, AsciiPanel.brightYellow);
		terminal.writeCenter("Cause of death...", 12, AsciiPanel.brightYellow);
		terminal.writeCenter(causeOfDeath, 13, AsciiPanel.brightRed);
		terminal.writeCenter("And it only took you..", 14, AsciiPanel.brightYellow);
		terminal.writeCenter(String.format("%d turns!", numberOfTurns), 15, AsciiPanel.brightRed);
		terminal.writeCenter(" Press S to view Stats", 22, ExtraColors.slateGray);
		terminal.writeCenter(" Press Enter to Restart", 23, ExtraColors.slateGray);


	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ENTER)
			return new StartScreen();
		if(key.getKeyCode() == KeyEvent.VK_S) {
			return new StatsScreen(stats);
		}
		return this;
	}
	

}
