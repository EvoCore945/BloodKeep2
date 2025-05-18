package World;
import characters.Demon;
import characters.Player;
import command.Backpack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class WorldMap {
    private static HashMap<Integer, Location> world = new HashMap<>();
    private HashMap<Integer, List<Item>> locationItems = new HashMap<>();
    private HashMap<Integer, Demon> locationDemos = new HashMap<>();
    private static int startingPoint = 0;
    private static int currentPosition = startingPoint;

    public void loadMap () throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Files/map.txt"))){
            String part;
            while ((part = br.readLine()) != null) {
                String[] parts = part.split(";");

                Location location = new Location(parts[1], Integer.parseInt(parts[0]), Arrays.copyOfRange(parts, 2, 6));
                world.put(Integer.valueOf(parts[0]), location);
            }
        } catch (IOException e) {
            System.out.println("Error while loading the map: " + e.getMessage());
        }
    }
    public void loadItems(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if(parts.length < 5){
                    System.out.println("Incorrect line in item file: " + line);
                    continue;
                }
                int locationId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String description = parts[2];
                ItemType type = ItemType.valueOf(parts[3].toUpperCase());
                int cost = Integer.parseInt(parts[4].trim());

                Item item = new Item(name, description, type,cost );

                if(!locationItems.containsKey(locationId)){
                    locationItems.put(locationId,new ArrayList<>());
                }
                locationItems.get(locationId).add(item);
                System.out.println("Loaded item " + name + " into location " + locationId);
                System.out.println("Item loaded at location " + locationId + ": " + name);
            }
        } catch (IOException e) {
            System.out.println("Error while loading items: " + e.getMessage());
        }
    }
    public boolean loadDemons(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line = br.readLine()) !=null){
            String[] parts = line.split(";");
            if(parts.length !=4) continue;

            int locationId = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int health = Integer.parseInt(parts[2].trim());
            int attack = Integer.parseInt(parts[3].trim());

            Demon demon = new Demon(name, health, attack);
            locationDemos.put(locationId,demon);
            System.out.println("Loaded demon" + name + "into location" + locationId);
        }
        return true;
    }
    public Demon getDemonAtLocation(int locationId){
        return locationDemos.get(locationId);
    }
    public void removeDemonAtLocation(int locationId){
        locationDemos.remove(locationId);
    }
    public HashMap<Integer,Demon> getLocationDemons(){
        return locationDemos;
    }
    public Location getCurrentLocation() {
        return world.get(currentPosition);
    }
    static public int getCurrentPosition() {
        return currentPosition;
    }
    public HashMap<Integer, Location> getWorld() {
        return world;
    }
    public Location getLocationById(int id){
        return world.get(id);
    }
    public HashMap<Integer, List<Item>> getLocationItems() {
        return locationItems;
    }
    public String move(String direction) {
        int indexOFdirection;
        switch (direction.toLowerCase()) {
            case "north":
                indexOFdirection = 0;
                break;
            case "south":
                indexOFdirection = 1;
                break;
            case "east":
                indexOFdirection = 2;
                break;
            case "west":
                indexOFdirection = 3;
                break;
            default:
                return "Invalid direction. Type only these directions: " + " north,  south,  east, west";
        }
        int newLocation = world.get(currentPosition).getLocations()[indexOFdirection];
        if (newLocation == -1) {
            return "You cant go that way!";


        }else{
            if(currentPosition == 16 && direction.equals("south") && !Backpack.hasItemName("Rusty Key 16")){
                return "You need rusty key 1 to unlock this room";
            }else{
                Backpack.removeItemFromBackpackByName("Rusty Key 1");
                System.out.println("Key has been taken out of  your backpack.");
            }
            if(currentPosition == 21 && direction.equals("south") && !Backpack.hasItemName("Shiny ancient Key")){
                return "You need Shiny ancient Key to unlock this room";
            }else{
                Backpack.removeItemFromBackpackByName("Shiny ancient Key");
                System.out.println("Key has been taken out of your backpack.");
            }
        }
        currentPosition = newLocation;
        return "You moved to " + world.get(currentPosition).getName();
    }
}