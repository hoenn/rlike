package screens;

import java.awt.event.KeyEvent;
import java.util.List;

import asciiPanel.AsciiPanel;

public class HistoryScreen implements Screen
{
	private List<String> messages;
	private Screen prev;
	
	public HistoryScreen(List<String> msgs, Screen prev)
	{
		messages = msgs;
		this.prev = prev;
		
	}
	
	public void displayOutput(AsciiPanel terminal)
	{
		terminal.write("NEWEST", 10, 0, AsciiPanel.brightYellow);

		for(int i =0; i<messages.size(); i++)
		{
			terminal.writeCenter(messages.get(i), i);
		}
	}

	public Screen respondToUserInput(KeyEvent key)
	{
		if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE) return prev;
		return this;
	}

}
