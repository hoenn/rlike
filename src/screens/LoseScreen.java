package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class LoseScreen implements Screen{
	//Add int to track number of turns to win
	public LoseScreen(){
		
	}
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU DIED", 10, AsciiPanel.yellow);

	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
