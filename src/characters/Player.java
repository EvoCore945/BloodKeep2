package characters;

import World.PlayerClasses;
import World.Weapon;

public class Player {

    private String name;
    private static Player instance = new Player();
    private int health;
    private PlayerClasses playerClass;
    private Weapon weapon;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setInstance(Player instance) {
        Player.instance = instance;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public PlayerClasses getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(PlayerClasses playerClass) {
        this.playerClass = playerClass;
    }

    @Override
    public String toString() {
        return "Your stats: \n" + "Name: " + name + "\nClass: " + playerClass + "\nWeapon: " + weapon.getName() + "\nHealth: " + health + "\nDamage: " + weapon.getDamage();
    }
}
