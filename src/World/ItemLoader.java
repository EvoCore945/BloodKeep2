package World;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.io.IOException;

public class ItemLoader {

    public boolean loadItems(String filename, HashMap<Integer, Location> map) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int locationId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String description = parts[2];
                ItemType type = ItemType.valueOf(parts[3].toUpperCase());

                Item item = new Item(name, description, type );

                Location location = map.get(locationId);
                if (location != null) {
                    location.addItem(item);
                } else {
                    System.out.println("Warning: Location ID " + locationId + " not found.");
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error while loading items: " + e.getMessage());
            return false;
        }

    }

    }

