import World.Location;
import World.WorldMap;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        WorldMap world = new WorldMap();
        world.loadMap();
        world.loadItems("src/Files/items.txt");
        Console console = new Console(world);
        console.startGame();

    }
}