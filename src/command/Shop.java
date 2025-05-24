package command;
import World.Item;
import World.ItemType;
import World.Location;
import World.WorldMap;
import characters.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop extends Command{
    WorldMap worldMap;
    private static ArrayList<Item> shop = new ArrayList<>();
    public Shop(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
    @Override
    public String execute() throws IOException {

        String locationName = worldMap.getCurrentLocation().getName();
        if(!(locationName.equalsIgnoreCase("Merchant of the Hollow Veil")||locationName.equalsIgnoreCase("Merchant of the Crimson Depths"))){
            return "There is shop in this room!";
        }
        if(locationName.equalsIgnoreCase("Merchant of the Hollow Veil")){
            loadItemsFromShop("src/Files/shop1_items.txt");
        } else if (locationName.equalsIgnoreCase("Merchant of the Crimson Depths")) {
            loadItemsFromShop("src/Files/shop2_items.txt");
        }

        System.out.println("Welcome to my shop brave warrior! \nDo you wanna buy(1) some merchandise or perhaps sell(2) something from your backpack? ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        switch(choice){
            case "1":
                BuyItem();
                break;
            case "2":
                SellItem();
                break;
        }
        return "";
    }
    public void BuyItem(){
        System.out.println("Shop selection: ");
        for(Item item : shop){
            System.out.println(item.toString());
        }
        System.out.println("Type the name of the item you want to buy: ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        for(Item item : shop){
            if(item.getName().equalsIgnoreCase(choice)){
                if(Player.getInstance().getOrbs() >= item.getCost()){
                    Backpack.addItemToBackpack(item);
                    Player.getInstance().setOrbs(Player.getInstance().getOrbs() - item.getCost());
                    System.out.println( "You have successfully bought " + item.getName() + "." + "\nYour total of orbs: " + Player.getInstance().getOrbs());
                    return;
                }else{
                    System.out.println("Not enough orbs!");
                    return;
                }
            }
        }
        System.out.println("Item not found in selection.");
        return;
    }
    public void SellItem(){
      if(Backpack.getBackpack().isEmpty()){
          System.out.println("Your backpack is empty! ");
        return;
      }
        System.out.println("Your items in backpack: ");
      for (Item item : Backpack.getBackpack()){
          System.out.println(item.toString());
      }
        System.out.println("Choose name of the item you want to sell: ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        for (int i = 0; i < Backpack.getBackpack().size(); i++) {
            Item item  = Backpack.getBackpack().get(i);
            if(item.getName().equalsIgnoreCase(choice)){
                int value = item.getCost();
                Player.getInstance().setOrbs(Player.getInstance().getOrbs() + value);
                Backpack.getBackpack().remove(i);
                System.out.println("You have successfully sold item for " + value + "." + "\nYour total orbs: " + Player.getInstance().getOrbs());
                return;
            }
        }
        System.out.println("Item was not found in backpack!");
    }

    public static void loadItemsFromShop(String filename) throws IOException {
        shop.clear();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null){
            String[] parts = line.split(";");
            if(parts.length !=4) continue;

            String name = parts[0].trim();
            String description = parts[1].trim();
            ItemType typ = ItemType.valueOf(parts[2].trim());
            int cost = Integer.parseInt(parts[3].trim());

            Item item = new Item(name,description,typ,cost);
            shop.add(item);
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
