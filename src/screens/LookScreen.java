package screens;

import asciiPanel.AsciiPanel;
import rlike.Creature;
import rlike.Item;
import rlike.Tile;

public class LookScreen extends TargettingBasedScreen {
	public LookScreen(Creature player, String caption, int sx, int sy) {
        super(player, caption, sx, sy);
    }

    public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
        Creature creature = player.creature(x, y, player.z);
        if (creature != null){
            caption = creature.glyph() + " "     + creature.name() + creature.info();
            return;
        }
    
        Item item = player.item(x, y, player.z);
        if (item != null){
            caption = item.glyph() + " "     + item.name() + item.info();
            return;
        }
    
        Tile tile = player.tile(x, y, player.z);
        caption = tile.glyph() + " " + tile.info();
    }
    public void displayOutput(AsciiPanel terminal) {
		terminal.write("LOOK", 0, 0, AsciiPanel.brightGreen);
    	super.displayOutput(terminal);
    }
}
