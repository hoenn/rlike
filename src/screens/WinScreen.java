package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class WinScreen implements Screen{
	//Add int to track number of turns to win
	public WinScreen(){
		
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU WIN", 10, AsciiPanel.yellow);

	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
