package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class ItemPicker {
    
    private final IGameEngine gameEngine;

    public ItemPicker(IGameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    } 

    //methods interact with item actor
     public boolean pickUpItem(Item item, RpgPlayer player) {
        int weight = calculateInventoryWeight(player);
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item, player))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }
        if (item.isRare() && item.isUnique())
            gameEngine.playSpecialEffect("blue_swirly");
        else if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        player.addInventory(item);

        calculateStats(player);

        return true;
    }
    
    private boolean checkIfItemExistsInInventory(Item item, RpgPlayer player) {
        for (Item i: player.getInventory()) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    private void calculateStats(RpgPlayer player) {
        for (Item i: player.getInventory()) {
            player.setArmour(player.getArmour() + i.getArmour());
        }
    }

    private int calculateInventoryWeight(RpgPlayer player) {
        int sum=0;
        for (Item i: player.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }

}