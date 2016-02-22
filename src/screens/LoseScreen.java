package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class LoseScreen implements Screen{
	private int numberOfTurns;
	public LoseScreen(int numberOfTurns){
		this.numberOfTurns = numberOfTurns;
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU DIED", 10, AsciiPanel.brightYellow);
		terminal.writeCenter("And it only took you..", 11, AsciiPanel.brightYellow);
		terminal.writeCenter(String.format("10%d turns!", numberOfTurns), 12, AsciiPanel.brightRed);

	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return this;
	}
	

}
