package World;
import characters.Demon;
import characters.NPC;
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
    private HashMap<Integer, Demon> locationDemons = new HashMap<>();
    private HashMap<Integer, NPC> locationNPCs = new HashMap<>();
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

    public void loadNPCs(String fileName) throws IOException {
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
          String line;
          while((line = bufferedReader.readLine()) !=null){
              String[] parts = line.split(";");
              if(parts.length !=3) continue;
              int locationId = Integer.parseInt(parts[0].trim());
              String name = parts[1].trim();
              String dialogue = parts[2].trim();
              NPC npc = new NPC(name,dialogue);
              locationNPCs.put(locationId,npc);
              System.out.println("Loaded npc " + name + " into location " + locationId);
          }

      }catch (IOException e){
          System.out.println("Error while loading npcs: " + e.getMessage());
      }
    }


    public void loadDemons(String filename) throws IOException {
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
            locationDemons.put(locationId,demon);
            System.out.println("Loaded demon " + name + " into location " + locationId);
        }
    }

    public Demon getDemonAtLocation(int locationId){
        return locationDemons.get(locationId);
    }
    public void removeDemonAtLocation(int locationId){
        locationDemons.remove(locationId);
    }
    public HashMap<Integer,Demon> getLocationDemons(){
        return locationDemons;
    }
    public NPC getNPCAtLocation(int locationId){
        return locationNPCs.get(locationId);
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
        }
          if(currentPosition == 16 && direction.equals("south")) {
              if (!Backpack.hasItemName("Crimson key")) {
                  return "You need Crimson key to unlock this room";
              } else {
                  Backpack.removeItemFromBackpackByName("Crimson key");
                  System.out.println("Key has been taken out of  your backpack.");
              }
          }
            if(currentPosition == 26 && direction.equals("south")) {
                if(!Backpack.hasAllBloodkeepShards()){
                    return "The final door that leads to the heart of Bloodkeep is sealed by three ancient locks. You need all three Shards of the bloodkeep to open it.";
                }else{
                    Backpack.removeItemFromBackpackByName("Echo shard of the BloodKeep");
                    Backpack.removeItemFromBackpackByName("Bloodlust shard of the BloodKeep");
                    Backpack.removeItemFromBackpackByName("Emerald shard of the BloodKeep");
                    System.out.println("You used all the shards to open gate to heart of the Bloodkeep!");
                }

            }
            if(currentPosition == 24 && direction.equals("east")){
                if(!Backpack.hasItemName("Crimson merchant key")){
                    return "You need Crimson merchant key";
                }else{
                    Backpack.removeItemFromBackpackByName("Crimson merchant key");
                    System.out.println("Key has been taken out of  your backpack.");
                }
            }
        currentPosition = newLocation;
        return "You moved to " + world.get(currentPosition).getName();

         }
    }
