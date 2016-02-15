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
        return item.foodValue() != 0 || item.hpValue() !=0;
    }

    protected Screen use(Item item) {
        player.eat(item);
        String sign = "";
        if(item.foodValue()>0)
        	sign="+";
        
        String hpSign = "";
        if(item.hpValue()>0)
        	hpSign="+";

        player.notify("You eat the "+item.name()+". "+sign+item.foodValue()+" food "+ hpSign+item.hpValue()+" hp");
        return null;
    }
}	