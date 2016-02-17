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

	private List<String> book;
	
	public ReadScreen(Screen prev, Inventory i) {
		this.prev = prev;
		this.inventory = i;
		
		topLine = 0;
		book = new ArrayList<String>();
		createBook();
	}
	public void displayOutput(AsciiPanel terminal)
	{
		terminal.clear();
		for(int i = 0; i < 22; i++) {
			if(topLine+i<book.size())
				terminal.write(book.get(topLine+i), 0, i);
		}
		
		terminal.writeCenter(" Press Arrow Keys To Scroll", 23, ExtraColors.slateGray);
	}
	public void createBook() {
		if(inventory.hasItem("Maginomicon: Volume 1")) {
			for(String s: vol1)
				book.add(s);
		}
		if(inventory.hasItem("Maginomicon: Volume 2")) {
			for(String s: vol2)
				book.add(s);
		}
		if(inventory.hasItem("Maginomicon: Volume 3")) {
			for(String s: vol3) 
				book.add(s);
		}
		
			
	}
	public Screen respondToUserInput(KeyEvent key)
	{
		switch (key.getKeyCode()){
			case KeyEvent.VK_UP: if(topLine>0)topLine--; break;
			case KeyEvent.VK_DOWN: if(topLine<book.size()-22)topLine++; break;
			case KeyEvent.VK_ESCAPE: return prev;
		}
		return this;
		
	}

}