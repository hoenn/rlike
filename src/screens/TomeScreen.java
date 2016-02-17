package screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;
import rlike.Inventory;

public class TomeScreen implements Screen
{
	private Screen prev;
	private Inventory inventory;
	private int topLine;
	private String[] vol1= { "                                STRENGTH                                       ", 
							 "                                                                               ",
							 "aaaaLong ago there was a boy. His strength surpassed all of his classmates anda",
							 "                                                                               "};
	private String[] vol2= { "                               PROTECTION                                      ", 
							 "                                                                               ",
							 "   Long ago there was a boy. His strength surpassed all of his classmates and  ",
							 "                                                                               "};
	private String[] vol3= { "                               FORTITUDE                                       ", 
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",
							 "                                     dddd                                      ",						 
							 "   Long ago there was a boy. His strength surpassed all of his classmates and  "};

	private List<String> master;
	public TomeScreen(Screen prev, Inventory i) {
		this.prev = prev;
		this.inventory = i;
		
		topLine = 0;
		master = new ArrayList<String>();
		createMaster();
	}
	public void displayOutput(AsciiPanel terminal)
	{
		terminal.clear();
		terminal.write("TOME", 0, 0, AsciiPanel.brightGreen);
		for(int i = 0; i < 22; i++) {
			if(topLine+i<master.size())
				terminal.write(master.get(topLine+i), 0, i);
		}
		
		terminal.writeCenter(" Press Arrow Keys To Scroll", 23, ExtraColors.slateGray);
	}
	public void createMaster() {
		if(inventory.hasItem("Maginomicon: Volume 1")) {
			for(String s: vol1)
				master.add(s);
		}
		if(inventory.hasItem("Maginomicon: Volume 2")) {
			for(String s: vol2)
				master.add(s);
		}
		if(inventory.hasItem("Maginomicon: Volume 3")) {
			for(String s: vol3) 
				master.add(s);
		}
		
			
	}
	public Screen respondToUserInput(KeyEvent key)
	{
		switch (key.getKeyCode()){
			case KeyEvent.VK_UP: if(topLine>0)topLine--; break;
			case KeyEvent.VK_DOWN: if(topLine<master.size()-22)topLine++; break;
			case KeyEvent.VK_ESCAPE: return prev;
		}
		return this;
		
	}

}