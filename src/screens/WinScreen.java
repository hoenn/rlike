package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class WinScreen implements Screen{
	private int numberOfTurns;
	public WinScreen(int numberOfTurns){
		this.numberOfTurns = numberOfTurns;
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU WON", 10, AsciiPanel.brightYellow);
		terminal.writeCenter("And it only took you..", 11, AsciiPanel.brightYellow);
		terminal.writeCenter(String.format("10%d turns!", numberOfTurns), 12, AsciiPanel.brightGreen);

	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
