package command;

import World.Item;
import World.Location;
import World.WorldMap;
import characters.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        System.out.println("You are in:" + location.getName());

        List<Item> items = worldMap.getLocationItems().getOrDefault(currentPosition,new ArrayList<>());

        if(roomItemsTaken.containsKey(currentPosition)){
            System.out.println("There are no items left in this room.");
        }else if(items != null &&!items.isEmpty()){
            for(Item item : items){
                Backpack.addItemToBackpack(item);
                System.out.println(item.getName() + "was add to your Backpack!");
            }
            roomItemsTaken.put(currentPosition,items);
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
