package World;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private int bonusHealth = 0;
    private int cost;


    public Item(String name, String description, ItemType type, int cost) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ItemType getType() {
        return type;
    }
    public int getBonusHealth() {
        return bonusHealth;
    }
    public int getCost() {
        return cost;
    }
    //Item smallHeal = new Item("Small Heal", "Restores 20 HP", ItemType.CONSUMABLE);
    //smallHeal.setBonusHealth(20);
    //
    //Item bigHeal = new Item("Large Heal", "Restores 50 HP", ItemType.CONSUMABLE);
    //bigHeal.setBonusHealth(50);

    public void setBonusHealth(int bonusHealth) {
        this.bonusHealth = bonusHealth;
    }

    @Override

    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", cost=" + cost +
                '}';
    }
}

