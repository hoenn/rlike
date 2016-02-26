package screens;

import rlike.Creature;
import rlike.Item;

public class CastScreen extends InventoryBasedScreen {

    public CastScreen(Creature player) {
        super(player);
    }

    protected String getVerb() {
        return "cast";
    }

    protected boolean isAcceptable(Item item) {
    	//Only the three tomes can be cast
        return item.name().contains("Volume");
    }

    protected Screen use(Item item) {
        player.read(item);
        player.notify("You cast "+item.name());
        return null;
    }
}	