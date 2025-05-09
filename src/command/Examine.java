package command;

import World.Item;
import World.Location;
import World.WorldMap;
import characters.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Examine extends Command{

    private WorldMap worldMap;
    private HashMap<Integer, List<Item>> roomItemsTaken;

    public Examine(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.roomItemsTaken = new HashMap<>();
    }

    @Override
    public String execute() {

        int currentPosition = WorldMap.getCurrentPosition();
        System.out.println("DEBUG: currentPosition = " + currentPosition);

        Location location = worldMap.getLocationById(currentPosition);
        if(location == null){
            return "Unknown location";
        }
        System.out.println("You are in: " + location.getName());

        System.out.println("DEBUG: locationItems keys = " + worldMap.getLocationItems().keySet());
        List<Item> items = worldMap.getLocationItems().getOrDefault(currentPosition,new ArrayList<>());
        System.out.println("DEBUG: items found = " + items.size());

        if(roomItemsTaken.containsKey(currentPosition)){
            return "There are no items left in this room.";
        }

        if(items == null || items.isEmpty()) {
            return "There are no items in this room.";
        }
        System.out.println("Items in this room: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i).getName() + " - " + items.get(i).getDescription());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to take any of these items?\n(Type numbers to take items or 0 to skip.)");

        String response = sc.nextLine().trim();

        if(response.equals("0")){
          //  System.out.println("You chose not to take anything from this room.");
            return "You chose not to take anything from this room.";
        }
        String[] selections = response.split(";");
        ArrayList<Item> takenItems = new ArrayList<>();

        for(String selection : selections) {
            try {
                int index = Integer.parseInt(selection.trim()) - 1;
                if (index >= 0 && index < items.size()) {
                    Item chosen = items.get(index);
                    Backpack.addItemToBackpack(chosen);
                    takenItems.add(chosen);
                    System.out.println(chosen.getName() + " was added to your backpack.");
                } else {
                    System.out.println("Invalid item index " + (index + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + selection );
            }
        }
        if(!takenItems.isEmpty()){
        roomItemsTaken.put(currentPosition,takenItems);
        }
            return "";
    }
    private void interestingRoomText(int position){
        switch(position){
          //todo budouci zajimave misntosti, co neco obsahuji
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
