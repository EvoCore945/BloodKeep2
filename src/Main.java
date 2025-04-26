import World.WorldMap;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        WorldMap world = new WorldMap();
        world.loadMap();
        Console console = new Console();
        console.startGame();

    }
}