package characters;
import World.Item;
import World.PlayerClasses;
import World.Weapon;
import command.Backpack;

public class Player {
    private String name;
    private static Player instance = new Player();
    private int health;
    private PlayerClasses playerClass;
    private Weapon weapon;
    private int orbs = 0;
    /**
     * This method returns the single instance of the Player class.
     * If the instance does not exist, it will be created.
     *
     * @return The single instance of the Player class.
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }
    public static boolean hasItem(String name){
        return Backpack.getBackpack().contains(name);
    }
    public String getName() {
        return name;
    }
    public void addOrbs(int amount){
        orbs += amount;
    }
    public int getOrbs() {
        return orbs;
    }
    public void setOrbs(int orbs) {
        this.orbs = orbs;
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
        return "Your stats: \n" + "Name: " + name + "\nClass: " + playerClass + "\nWeapon: " + weapon.getName() + "\nHealth: " + health + "\nDamage: " + weapon.getDamage() + "\nOrbs: " + getOrbs();
    }
}
