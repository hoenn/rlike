package screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;

public class StartScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		//Fill background
		for(int r = 0; r<24; r++) {
			for(int c =0; c<80; c++) {
				terminal.write('.', c, r, AsciiPanel.brightBlack);
			}
		}
		
		
		//Draw Image
		ArrayList<String> img = new ArrayList<String>();
		img.add("            ...            ");
		img.add("          .@@@@@.          ");
		img.add("        .@@@   @@@.        ");
		img.add("      .@@@       @@@.      ");
		img.add("     .@@@@       @@@@.     ");
		img.add("    .@@@@         @@@@.    ");
		img.add("    .@@@           @@@.    ");
		img.add("    .@@@           @@@.    ");
		img.add("     .@@@         @@@.     ");
		img.add("     .@@@         @@@.     ");
		img.add("  ..  .@@         @@.  ..  ");
		img.add(" .@@. ..@         @.. .@@. ");
		img.add(" .@@..@@@@       @@@@..@@. ");
		img.add("  .@@@@@@@       @@@@@@@.  ");
		img.add("   ......         ......   ");
		randomizeCharacter(img);
		int xx = (int)(Math.random()*50)+1;
		int yy = (int) (Math.random()*10);
		for(int i =0; i<img.size(); i++) {
			terminal.write(img.get(i), xx, i+yy, ExtraColors.mediumOrchid);
		}
		
		
		//Write title
		terminal.write("Welcome to", 27, 10);
		terminal.write("-Karina-", 38, 10, AsciiPanel.brightRed);
		terminal.write("Quest!", 47, 10);
		terminal.writeCenter("Press Enter to Begin", 22);
		
	}
	public void randomizeCharacter(List<String> list) {
		String randomSymbol = Character.toString((char)((int)(Math.random()*255)+1));
		for(int i =0; i<list.size(); i++) {
			String s = list.get(i);
			s = list.get(i).replaceAll("@", randomSymbol);
			list.set(i, s);
		}
	}
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}

}
