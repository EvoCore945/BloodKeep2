import World.WorldMap;

import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        WorldMap world = new WorldMap();
        world.loadMap();
        Console console = new Console();
        console.startGame();
        System.out.println("ffdf");
    }
}