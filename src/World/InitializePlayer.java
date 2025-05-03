package World;

import characters.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InitializePlayer {

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
        if(choice >= 1 &&choice<=3){
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
            }else{
                System.out.println("Invalid choice! Please select a number between 1 and 3.");
            }
        }
    }

