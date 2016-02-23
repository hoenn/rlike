package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rlike.ExtraColors;
import rlike.Stats;

public class StatsScreen implements Screen {

	private Stats stats;

	public StatsScreen(Stats stats) {
		this.stats = stats;
	}

	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("STATS", 0, AsciiPanel.brightYellow);
		terminal.writeCenter("Number of kills", 2, ExtraColors.mediumPurple);
		terminal.writeCenter(stats.kills+"", 3, ExtraColors.mediumOrchid);
		terminal.writeCenter("Number of things eaten", 4, ExtraColors.mediumPurple);
		terminal.writeCenter(stats.thingsEaten+"", 5, ExtraColors.mediumOrchid);
		terminal.writeCenter("Number of items thrown", 6, ExtraColors.mediumPurple);
		terminal.writeCenter(stats.itemsThrown+"", 7, ExtraColors.mediumOrchid);
		terminal.writeCenter("Number of level ups", 8, ExtraColors.mediumPurple);
		terminal.writeCenter(stats.levelUps+"", 9, ExtraColors.mediumOrchid);
		terminal.writeCenter("Number of turns spent digging", 10, ExtraColors.mediumPurple);
		terminal.writeCenter(stats.turnsSpentDigging+"", 11, ExtraColors.mediumOrchid);
		terminal.writeCenter((char)234+" Number of times death interfered "+(char)234, 12, ExtraColors.mediumPurple);
		terminal.writeCenter(stats.deathInterferences+"", 13, ExtraColors.mediumOrchid);
		terminal.writeCenter(" Press Enter to Restart", 23, ExtraColors.slateGray);
	}

	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ENTER)
			return new StartScreen();
		return this;
	}

}
