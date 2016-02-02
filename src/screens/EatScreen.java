package screens;

import rlike.Creature;
import rlike.Item;

public class EatScreen extends InventoryBasedScreen {

    public EatScreen(Creature player) {
        super(player);
    }

    protected String getVerb() {
        return "eat";
    }

    protected boolean isAcceptable(Item item) {
        return item.foodValue() != 0;
    }

    protected Screen use(Item item) {
        player.eat(item);
        String sign = "";
        if(item.foodValue()>0)
        	sign="+";

        player.notify("You eat the "+item.name()+". "+sign+item.foodValue()+" food.");
        return null;
    }
}	