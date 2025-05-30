package command;

import World.WorldMap;

import java.util.Scanner;

public class Go extends Command{

    private WorldMap worldMap = new WorldMap();
    Scanner sc = new Scanner(System.in);
    /**
     * This function triggers a puzzle in the game. The player is presented with a riddle and must provide an answer.
     * If the answer is correct, an item is added to the player's backpack.
     *
     * @return void
     */
    @Override
    public String execute() {
        System.out.println("Where do you want to go?" );

        String direction = sc.next();
        direction = direction.toLowerCase();

        System.out.println(worldMap.move(direction));
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
