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
    /**
     * Loads the game map from a text file.
     * The map file contains information about each location, including its ID, name, and connected locations.
     *
     * @throws FileNotFoundException If the map file cannot be found.
     */
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
    /**
     * Loads items from a specified file into the game world.
     * Each line in the file represents an item and its properties.
     * The format of each line is: locationId;name;description;type;cost
     *
     * @param filename The name of the file containing the item data.
     */
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
            }
        } catch (IOException e) {
            System.out.println("Error while loading items: " + e.getMessage());
        }
    }
    /**
     * Loads NPCs from a specified file into the game world.
     * Each line in the file represents an NPC and its properties.
     * The format of each line is: locationId;name;dialogue
     *
     * @param fileName The name of the file containing the NPC data.
     */
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
          }
      }catch (IOException e){
          System.out.println("Error while loading npcs: " + e.getMessage());
      }
    }
    /**
     * Loads demons from a specified file into the game world.
     * Each line in the file represents a demon and its properties.
     * The format of each line is: locationId;name;health;attack
     *
     * @param filename The name of the file containing the demon data.
     * @throws IOException If an error occurs while reading the file.
     */
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
    /**
     * Moves the player to a new location based on the specified direction.
     *
     * @param direction The direction in which the player wants to move. It can be one of the following: "north", "south", "east", "west".
     * @return A message indicating the result of the move. If the move is successful, it returns a message indicating the new location.
     *        If the move is not possible due to an invalid direction or a blocked path, it returns an appropriate error message.
     *        If the move is successful and the player encounters a special location (e.g., locked room, final door), it returns a message
     *        describing the special condition and prompting the player to interact with it.
     */
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
