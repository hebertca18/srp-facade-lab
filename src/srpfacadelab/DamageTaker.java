package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class DamageTaker {
    private final IGameEngine gameEngine;

    public DamageTaker(IGameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    } 

      public void takeDamage(int damage, RpgPlayer player) {
        if (damage < player.getArmour()) {
            gameEngine.playSpecialEffect("parry");
        }
        if (calculateInventoryWeight(player) < player.getCarryingCapacity() * .5)
        {
            int damageToDeal = damage - player.getArmour();
            player.setHealth(player.getHealth() - damageToDeal * .25);
        }
        else
        {
            int damageToDeal = damage - player.getArmour();
            player.setHealth(player.getHealth() - damageToDeal);
        }


        gameEngine.playSpecialEffect("lots_of_gore");
    }



    private int calculateInventoryWeight(RpgPlayer player) {
        int sum=0;
        for (Item i: player.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }
}