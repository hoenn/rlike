package screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;
import rlike.Inventory;

public class ReadScreen implements Screen
{
	private Screen prev;
	private Inventory inventory;
	private int topLine;
	private String[] vol1= { "                                     STRENGTH                                  ", 
							 "                                                                               ",
							 "aaaaLong ago there was a boy. His strength surpassed all of his classmates anda",
							 "                                                                               "};
	private String[] vol2= { "                                    PROTECTION                                 ", 
							 "                                                                               ",
							 "   Long ago there was a boy. His strength surpassed all of his classmates and  ",
							 "                                                                               "};
	private String[] vol3= { "                                     FORTITUDE                                 ", 
							 "  Fortitude comes first. Fortitude is the power to withstand the first blow of ",
							 "your enemy. The first attack is always the most vicious. How could one stand a ",
							 "chance to fight when crippled by an adversaries sinister strike? Fortitude will",
							 "allow the user to compete with their rival and push through the cheapest of    ",
							 "attacks that the enemy has to offer.                                           ",
							 "                                                                               ",
							 "  Uta sha lugha' ur ru'susiaga su u'a'thuna xuia' ananuat. Yuia' ghaaknatt     ",
							 "                ftathunat ts'angsh xuia' lugha' ut ian'u'aftag.                ",
							 "                                                                               ",
							 "ta shut ftuuk ru'ts su thats a tlaftft shas ghuftft ftas xuia tia''u'a Daash't ",
							 "ru'ts ts'uka. Arsa' shut ftuuk xuia'ftft ha'a su garftaths a naguth tlaftft,   ",
							 "shan xuia'ftft iata huftx ts'angsh su thalsia'a sha Daash Gug't tuiaft ang saka",
							 "ftaa'a r'un shut s'aathha'uiat giangaun. F'aa as ftats.                        "};

	private List<String> master;
	public ReadScreen(Screen prev, Inventory i) {
		this.prev = prev;
		this.inventory = i;
		
		topLine = 0;
		master = new ArrayList<String>();
		createMaster();
	}
	public void displayOutput(AsciiPanel terminal)
	{
		terminal.clear();
		terminal.write("READ", 0, 0, AsciiPanel.brightGreen);
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