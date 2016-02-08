package screens;

import java.awt.event.KeyEvent;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;

public class HistoryScreen implements Screen {
	private List<String> messages;
	private Screen prev;

	public HistoryScreen(List<String> msgs, Screen prev) {
		messages = msgs;
		this.prev = prev;

	}

	public void displayOutput(AsciiPanel terminal) {
		terminal.write("HISTORY", 0,0, AsciiPanel.brightGreen);
		terminal.writeCenter("NEWEST", 23, AsciiPanel.brightYellow);

		for (int i = 0; i < messages.size(); i++) {
			//If the message is from Death
	    	if(messages.get(i).contains(""+(char)234)) {
		        terminal.writeCenter(messages.get(i), 21-i, ExtraColors.mediumOrchid);

	    	}
	    	else
	    		terminal.writeCenter(messages.get(i), 21-i);
		}
	}

	public Screen respondToUserInput(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE)
			return prev;
		return this;
	}

}
