package screens;

import rlike.Creature;
import rlike.Item;

public class ReadScreen extends InventoryBasedScreen {

    public ReadScreen(Creature player) {
        super(player);
    }

    protected String getVerb() {
        return "read";
    }

    protected boolean isAcceptable(Item item) {
        return item.name().contains("Volume");
    }

    protected Screen use(Item item) {
        player.read(item);
        player.notify("You read "+item.name());
        return null;
    }
}	