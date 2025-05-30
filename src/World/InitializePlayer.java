package World;

import characters.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InitializePlayer {
    /**
     * This function initializes a new player by taking user input for name and class selection.
     * It uses a Scanner object to read user input from the console.
     * The function prompts the user to enter their name and then creates a new Player instance using the Singleton pattern.
     * It then asks the user to choose a class by displaying a menu of options and reading the user's choice.
     * If the user enters an invalid choice, the function defaults to the Swordsman class.
     * After setting the player's class, health, and weapon, the function prints a confirmation message.
     */
    public static void initializePlayer(){
        Scanner sc =new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();

        Player player = Player.getInstance();
        player.setName(name);

        int choice;
        while(true) {
            System.out.println("Choose your class:");
            System.out.println("1 --> Swordsman");
            System.out.println("2 --> Gunslinger");
            System.out.println("3 --> Trickster");
            try{
                choice = sc.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Please enter number.");
                sc.nextLine();
            }
        }
                switch(choice){

                    case 1:
                        player.setPlayerClass(PlayerClasses.SWORDSMAN);
                        player.setHealth(100);
                        player.setWeapon(new Weapon("Rebellion",20));
                        break;
                    case 2:
                        player.setPlayerClass(PlayerClasses.GUNSLINGER);
                        player.setHealth(110);
                        player.setWeapon(new Weapon("Ebony",15));
                        break;
                    case 3:
                        player.setPlayerClass(PlayerClasses.TRICKSTER);
                        player.setHealth(75);
                        player.setWeapon(new Weapon("Yamato",50));
                        break;
                    default:
                        System.out.println("Invalid choice. Automaticly defaulting to Swordsman class.");
                        player.setPlayerClass(PlayerClasses.SWORDSMAN);
                        player.setHealth(100);
                        player.setWeapon(new Weapon("Rebellion",20));
                }
        System.out.println("You have chosen " + player.getPlayerClass());

            }
        }