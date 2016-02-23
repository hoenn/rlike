package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;

public class LoseScreen implements Screen{
	private int numberOfTurns;
	private String causeOfDeath;
	public LoseScreen(int numberOfTurns, String causeOfDeath){
		this.numberOfTurns = numberOfTurns;
		this.causeOfDeath = causeOfDeath;
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU DIED", 10, AsciiPanel.brightYellow);
		terminal.writeCenter("Cause of death...", 12, AsciiPanel.brightYellow);
		terminal.writeCenter(causeOfDeath, 13, AsciiPanel.brightRed);
		terminal.writeCenter("And it only took you..", 14, AsciiPanel.brightYellow);
		terminal.writeCenter(String.format("%d turns!", numberOfTurns), 15, AsciiPanel.brightRed);
		terminal.writeCenter(" Press Enter to Restart", 23, ExtraColors.slateGray);


	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ENTER)
			return new StartScreen();
		return this;
	}
	

}
