package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class PlayScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		for(int i =0; i < 24; i++) {
			terminal.writeCenter("This is fun", i);
		}	
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
		return null;
	}

}
