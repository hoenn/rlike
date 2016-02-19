package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;

public class HelpScreen implements Screen
{
	private Screen prev;
	public HelpScreen(Screen prev) {
		this.prev = prev;
	}
	public void displayOutput(AsciiPanel terminal)
	{
		terminal.clear();
		terminal.write("HELP", 0, 0, AsciiPanel.brightGreen);
		terminal.writeCenter(
				"Explore the dungeon and defeat enemies",
				 3);
		terminal.writeCenter(
				"Be wary of the death god",
				 4);
		terminal.writeCenter(
				"Find the three tomes of the Maginomicon",
				 5);
		terminal.writeCenter(
				"use their power to defeat Death and free yourself",
				6);


		int y = 8;
		terminal.write("'g' to pick up", 2, y++);
		terminal.write("'d' to drop", 2, y++);
		terminal.write("'e' to eat", 2, y++);
		terminal.write("'w' to wear or equip armor and weapons", 2, y++);
		terminal.write("'h' for help", 2, y++);
		terminal.write("'m' for recent messages", 2, y++);
		terminal.write("'x' to examine your items", 2, y++);
		terminal.write("'c' to cast a tome. DESTROYS TOME", 2, y++);
		terminal.write("'r' to read your tomes", 2, y++);
		terminal.write("'l' to look around", 2, y++);

		terminal.write((char)234, 23, 20, ExtraColors.mediumOrchid);
		terminal.write((char)234, 55, 20, ExtraColors.mediumOrchid);
		terminal.writeCenter(" Press Any Key to Continue ", 20);
	}

	public Screen respondToUserInput(KeyEvent key)
	{
		return prev;
	}

}
