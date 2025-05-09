package characters;

import java.util.HashMap;

public class Demon {

    private String name;
    private int health;
    private int attackDamage;

    public Demon(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
    public boolean isAlive(){
        return health > 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
