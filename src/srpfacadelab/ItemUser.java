package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class ItemUser {

    private final IGameEngine gameEngine;

    public ItemUser(IGameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    } 

    //method interacts with enemy actor
    public void useItem(Item item, RpgPlayer player) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }
}