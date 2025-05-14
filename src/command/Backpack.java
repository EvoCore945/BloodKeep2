package command;

import World.Item;
import World.ItemType;
import World.WorldMap;
import characters.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Backpack extends Command{

    WorldMap worldmap;
    private static ArrayList<Item> backpack = new ArrayList<>();

    public static ArrayList<Item> getBackpack() {
        return backpack;
    }
    public static void addItemToBackpack(Item item){
        if(item != null){
            backpack.add(item);
        }
    }


    @Override
    public String execute() {
        Scanner sc = new Scanner(System.in);

        if (backpack.isEmpty()) {
            System.out.println("Backpack is empty");
            return "";
        }
        System.out.println("TYPE: '1' - Writes all the items in your backpack.");
        System.out.println("TYPE: '2' - Use a specific item");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                showBackpack();
                break;
            case 2:
                useItem(sc);
                break;
            default:
                System.out.println("Invalid selection");
        }
        return "";
    }
     public void showBackpack(){
         System.out.println("These are the items in your backpack:");
         for (int i = 0; i < backpack.size(); i++) {
             System.out.println((i + 1) + ". " + backpack.get(i).getName() + " - " + backpack.get(i).getDescription());
     }
     }

     public void useItem(Scanner sc){
         int currentPosition = WorldMap.getCurrentPosition();
         showBackpack();
         System.out.println("Enter the number of the item you want to use:");

         if (!sc.hasNextInt()) {
             System.out.println("Invalid input");
             sc.next();
             return;
         }

         int index = sc.nextInt() - 1;

         if (index < 0 || index >= backpack.size()) {
             System.out.println("Invalid selection");
         }

         Item selectedItem = backpack.get(index);
         ItemType type = selectedItem.getType();

         switch(type){
             case CONSUMABLE -> {
                 int healedAmount;
                 switch(selectedItem.getName()){
                     case "SVitalStar":
                         healedAmount = 10;
                         Player.getInstance().setHealth(Player.getInstance().getHealth() + healedAmount);
                 }


                 backpack.remove(index);
                 System.out.println("You used " + selectedItem.getName() + "." + "\n Health restored to " + Player.getInstance().getHealth()+ "HP.");
             }
             case KEY -> System.out.println("Keys are already used.");
             case LORE ->{
                 System.out.println("Reading some writings:");
                 System.out.println(selectedItem.getDescription());
             }
             case WEAPON -> System.out.println("You cant use weapon in backpack. You can upgrade it in the Shop.");

         }

     }

    @Override
    public boolean exit() {
        return false;
    }
}
