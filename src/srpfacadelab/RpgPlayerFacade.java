package srpfacadelab;

import java.util.List;
import java.util.ArrayList;


public class RpgPlayerFacade {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private final IGameEngine gameEngine;

    private int health;

    private int maxHealth;

    private int armour;

    private List<Item> inventory;

    private ItemUser itemUser;

    private ItemPicker itemPicker;

    private DamageTaker damageTaker;

    // How much the player can carry in pounds
    private int carryingCapacity;

    public RpgPlayerFacade(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        inventory = new ArrayList<Item>();
        carryingCapacity = MAX_CARRYING_CAPACITY;
        //instantiate classes with functions
        itemUser = new ItemUser(gameEngine);
        itemPicker = new ItemPicker(gameEngine);
        itemUser = new ItemUser(gameEngine);
    }

    public void useItem(Item item, RpgPlayer player) {
        itemUser.useItem(item, player);
    }

    public boolean pickUpItem(Item item, RpgPlayer player) {
        return itemPicker.pickUpItem(item, player);        
    }

    public void takeDamage(int damage, RpgPlayer player) {
        damageTaker.takeDamage(damage, player);
    }

}
