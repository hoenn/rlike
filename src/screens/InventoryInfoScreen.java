package screens;

import rlike.Creature;
import rlike.Item;

public class InventoryInfoScreen extends InventoryBasedScreen {

    public InventoryInfoScreen(Creature player) {
        super(player);
    }

    protected String getVerb() {
        return "examine";
    }

    protected boolean isAcceptable(Item item) {
        return true;
    }

    protected Screen use(Item item) {
        String article = "aeiou".contains(item.name().subSequence(0, 1)) ? "an " : "a ";
        player.notify("It's " + article + item.name() + "." + item.info());
        return null;
    }
}
