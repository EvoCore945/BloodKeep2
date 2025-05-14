import World.Location;
import World.WorldMap;
import command.Shop;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        WorldMap world = new WorldMap();
        world.loadMap();
        Shop.loadItemsFromShop("src/Files/shop_items.txt");
        world.loadItems("src/Files/items.txt");
        world.loadDemons("src/Files/demons.txt");
        Console console = new Console(world);
        console.startGame();

    }
}