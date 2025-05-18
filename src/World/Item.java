package World;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private int cost;


    public Item(String name, String description, ItemType type, int cost) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.cost = cost;
    }
    public  String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ItemType getType() {
        return type;
    }
    public int getCost() {
        return cost;
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

