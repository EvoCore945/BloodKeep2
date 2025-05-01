package World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Location {

    private String name;
    private int ID;
    private int[] locations;
    private ArrayList<Item> items = new ArrayList<>();


    public Location(String name, int ID, String[] locations) {
        this.name = name;
        this.ID = ID;
        this.locations = new int [4];
        for (int i = 0; i < locations.length; i++){
            this.locations[i] = Integer.parseInt(locations[i]);
        }
    }
    public void addItem(Item item){
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getLocations() {
        return locations;
    }

    public void setLocations(int[] locations) {
        this.locations = locations;
    }

    public ArrayList<Item> getItems() {
        return items;
    }


    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", locations=" + Arrays.toString(locations) +
                '}';
    }
}
