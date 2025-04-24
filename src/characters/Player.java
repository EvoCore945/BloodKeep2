package characters;

public class Player {

    private String name;
    private static Player instance = new Player();
    private int attackDamage;
    private int health;
    private int gameClass;

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

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", attackDamage=" + attackDamage +
                ", health=" + health +
                ", gameClass=" + gameClass +
                '}';
    }
}
