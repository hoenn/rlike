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
							 "  Strength is of thirdmost importance. Without fortitude and protection, the   ",
							 "strength of the user will never be realized. A strong defense is not an offense",
							 "but the capacitance for a relentless offense. A lion's strike is powerful,     ",
							 "deadly, and singular. Only the most powerful and accurate bite is performed.   ",
							 "The most efficient killer of all beasts and man. Strength will allow you to    ",
							 "finally overcome your obstacles, to finish off your foul foe                   ",
							 "                                                                               ",
							 "Sdlovkdh. Wladavk urr ad dhak ralo huk koov kavk ad uvvaavavk. I vakh I huk    ",
							 "nalo kukkaav davulkk dhak klakodd ka I duv kid nalo oddald avda dho ralo       ",
							 "vladavk. Thak kavk ad dhavk nukok no ka vidk av Mallavavk.  Avavvuav, uk ikiur:",
							 "Faldadiko, Pladoddaav, Sdlovkdh.                                               ",
							 "                                                                               "};
	private String[] vol2= { "                                    PROTECTION                                 ", 
							 "                                                                               ",
							 "  Protection comes second. Protection is the resolve to expect the unexpected. ",
							 "Protection is required to survive exposure to the dark arts. A strong defense  ",
							 "merely allows the potential for offense. To be removed from existence with no  ",
							 "mercy will enable the counterattack to be available. Protection will allow the ",
							 "the user to reflect the dark arts of their rival and prepare themselves for a  ",
							 "counter attack of their own.                                                   ",
							 "                                                                               ",
							 "Fusdaidina...Hairr Tirrras, irrui'sa fsufefrirr saenailr dhair sairhd luw. Ura ",
							 "dhair duna rasuln. Thair ula erruwr irrui du sallrasd e rfarr  dhed nuar raica ",
							 "100000 nenera rur. Yui'rr fa efra du suildas eddesc elldaswusnr ru fsafesa     ",
							 "irruisrarll du ira dha lazd duna! Rananfas dha usnas: llusdaidina, fsudasdaiul,",
							 " rdsalrdh.                                                                     ",
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