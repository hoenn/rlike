package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;
import rlike.Stats;

public class WinScreen implements Screen{
	private int numberOfTurns;
	private Stats stats;
	public WinScreen(int numberOfTurns, Stats stats){
		this.numberOfTurns = numberOfTurns;
		this.stats = stats;
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU WON", 10, AsciiPanel.brightYellow);
		terminal.writeCenter("And it only took you..", 11, AsciiPanel.brightYellow);
		terminal.writeCenter(String.format("%d turns!", numberOfTurns), 12, AsciiPanel.brightGreen);
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
