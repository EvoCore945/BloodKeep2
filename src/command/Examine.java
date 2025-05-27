package command;

import World.Item;
import World.ItemType;
import World.Location;
import World.WorldMap;
import characters.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static World.ItemType.SPECIAL_KEY;

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
        Location location = worldMap.getLocationById(currentPosition);
        if(location == null){
            return "Unknown location";
        }
        System.out.println("You are in: " + location.getName());
        interestingRoomText(currentPosition);
        List<Item> items = worldMap.getLocationItems().getOrDefault(currentPosition,new ArrayList<>());
        if(roomItemsTaken.containsKey(currentPosition)){
            return "There are no items left in this room.";
        }

        if(items == null || items.isEmpty()) {
            return "There are no items in this room.";
        }
        System.out.println("Items in this room: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i).getName());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to take any of these items?\n(Type numbers to take items or 0 to skip.)");

        String response = sc.nextLine().trim();

        if(response.equals("0")){
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
        switch(position) {
            case 23:
                triggerOsuarryPuzzle();
                break;
            case 25 | 14:
                System.out.println("Shop is available in this room.");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
            case 10:
            case 12:
            case 19:
            case 22:
            case 24:
                System.out.println("There could be a demon to fight in this room!");
                break;
            case 27:
                System.out.println("Here stands in front of you Harbinger of the Abyss\nYou can end this by defeating him! ");
                break;
            case 20:
                System.out.println("There might be someone strong...");


        }
    }

    private void triggerOsuarryPuzzle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You hear a whisper: \n'You hear a whisper: 'I speak without a mouth and hear without ears.\nI have nobody, but I come alive with the wind.\n What am I?'");
        System.out.println("Your answer:");
        String response = sc.nextLine().trim().toLowerCase();
        if (response.equalsIgnoreCase("echo")) {
            System.out.println("Correct. \nYou feel something solid in your hand...\nIts an shard of the BloodKeep!");
            Item shard = new Item("Echo shard of the BloodKeep", "One of three shards needed to open the Heart of BloodKeep.",SPECIAL_KEY, 0);
            Backpack.addItemToBackpack(shard);
        } else {
            System.out.println("The silence remains... That is not the answer.");
        }
    }


    @Override
    public boolean exit() {
        return false;
    }
}
