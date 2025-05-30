package command;

import World.Item;
import World.ItemType;
import World.WorldMap;
import characters.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Backpack extends Command{

    private static ArrayList<Item> backpack = new ArrayList<>();

    public static ArrayList<Item> getBackpack() {
        return backpack;
    }
    public static void addItemToBackpack(Item item){
        if(item != null){
            backpack.add(item);
        }
    }
    public static boolean removeItemFromBackpackByName(String itemName){
        for (int i = 0; i < Backpack.getBackpack().size(); i++) {
            if(Backpack.getBackpack().get(i).getName().equalsIgnoreCase(itemName)){
                Backpack.getBackpack().remove(i);
                return true;
            }
        }
        return false;
    }
    public static boolean hasItemName(String itemName){
        for (Item item : Backpack.getBackpack()){
            if(item.getName().equalsIgnoreCase(itemName)){
                return true;
            }
        }
        return false;
    }
    public static boolean hasAllBloodkeepShards(){
        return Backpack.hasItemName("Echo shard of the BloodKeep") && Backpack.hasItemName("Bloodlust shard of the BloodKeep") && Backpack.hasItemName("Emerald shard of the BloodKeep");
    }
    /**
     * Executes the backpack command.
     * This method handles user input to either display all items in the backpack or use a specific item.
     *
     * @return String - An empty string, indicating that the command execution is successful.
     */
    @Override
    public String execute() {
        Scanner sc = new Scanner(System.in);

        if (backpack.isEmpty()) {
            System.out.println("Backpack is empty");
            return "";
        }
        System.out.println("TYPE: '1' - Writes all the items in your backpack.");
        System.out.println("TYPE: '2' - Use a specific item");
        int choice;
        try {
        choice = sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Invalid input. Enter number!");
            return "";
        }
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
    /**
     * Displays all items currently in the player's backpack.
     *
     * This method iterates through the backpack ArrayList and prints out each item's name and description.
     * The items are numbered for easy selection when using the 'useItem' method.
     *
     * @return void - This method does not return any value.
     */
     public void showBackpack(){
         System.out.println("These are the items in your backpack:");
         for (int i = 0; i < backpack.size(); i++) {
             System.out.println((i + 1) + ". " + backpack.get(i).getName() + " - " + backpack.get(i).getDescription());
     }
     }
    /**
     * Handles the use of items from the player's backpack.
     *
     * This method displays the player's backpack, prompts the user to select an item, and then uses the selected item.
     * The method handles different types of items (CONSUMABLE, KEY, LORE, WEAPON_UPGRADE, SPECIAL_KEY) and performs the appropriate action.
     *
     * @param sc - A Scanner object used to read user input.
     * @return void - This method does not return any value.
     */
     public void useItem(Scanner sc){
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
                     case "VitalStar_SMALL":
                         healedAmount = 20;
                         Player.getInstance().setHealth(Player.getInstance().getHealth() + healedAmount);
                         break;
                     case "VitalStar_MEDIUM":
                         healedAmount = 35;
                         Player.getInstance().setHealth(Player.getInstance().getHealth() + healedAmount);
                         break;
                     case "VitalStar_BIG":
                         healedAmount = 70;
                         Player.getInstance().setHealth(Player.getInstance().getHealth() + healedAmount);
                         break;
                 }
                 backpack.remove(index);
                 System.out.println("You used " + selectedItem.getName() + "." + "\n Health restored to " + Player.getInstance().getHealth()+ "HP.");
             }
             case KEY -> System.out.println("Go to the locked location to use this key.");
             case LORE ->{
                 System.out.println("Reading some writings:");
                 System.out.println(selectedItem.getDescription());
             }
             case WEAPON_UPGRADE ->{
                 int bonusDamage;
                 switch(selectedItem.getName()){
                     case"WeaponUpgrade_SMALL":
                         bonusDamage = 10;
                         Player.getInstance().getWeapon().setDamage(Player.getInstance().getWeapon().getDamage() + bonusDamage);
                         break;
                     case"WeaponUpgrade_MEDIUM":
                         bonusDamage = 15;
                         Player.getInstance().getWeapon().setDamage(Player.getInstance().getWeapon().getDamage() + bonusDamage);
                     case"WeaponUpgrade_BIG":
                         bonusDamage = 25;
                         Player.getInstance().getWeapon().setDamage(Player.getInstance().getWeapon().getDamage() + bonusDamage);
                 }
                 backpack.remove(index);
                     System.out.println("You just upgraded your: " + Player.getInstance().getWeapon().getName() + " damage to: " +Player.getInstance().getWeapon().getDamage() );

             }
             case SPECIAL_KEY -> System.out.println("Can be used only at the heart of BloodKeep.");
         }
     }
    @Override
    public boolean exit() {
        return false;
    }
}
