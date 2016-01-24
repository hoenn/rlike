package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.write("Welcome to", 27, 10);
		terminal.write("-Karina-", 38, 10, AsciiPanel.brightRed);
		terminal.write("Quest!", 47, 10);
		terminal.writeCenter("Press Enter to Begin", 22);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}

}
