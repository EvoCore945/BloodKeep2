package command;

import World.Item;
import World.WorldMap;
import characters.Demon;
import characters.Player;

import java.util.Random;
import java.util.Scanner;

public class Fight extends Command{

    private boolean isDead = false;
    WorldMap world;
    Random rd = new Random();

    public Fight(WorldMap world) {
        this.world = world;
    }

    @Override
    public String execute() {
        Scanner sc = new Scanner(System.in);
        Player player = Player.getInstance();
        int locationID = WorldMap.getCurrentPosition();
        Demon demon = world.getDemonAtLocation(locationID);

        if(demon == null){
            return "There is no demon left";
        }
        System.out.println("You have found " + demon.getName() + "!");
        System.out.println("Monster health: " + demon.getHealth() + " Monster damage: "+ demon.getAttackDamage() );
        System.out.println("                VS              ");
        System.out.println("Your health: " + Player.getInstance().getHealth() + " Your damage: " + Player.getInstance().getWeapon().getDamage());
        System.out.println("You can fight (fight) or escape (escape)");
        String choice = sc.nextLine().toLowerCase();
        if(choice.equals("escape")){
            return "You escaped!";
        } else if (choice.equals("fight")) {
            System.out.println("You just started a fight with " + demon.getName() +"!");

            while(player.getHealth() > 0 && demon.getHealth() > 0){
                System.out.println(demon.getName() + ", Health: " + demon.getHealth() + ", AttackDamage: " + demon.getAttackDamage());
                System.out.println(player.getName() + ", Health: " + player.getHealth() + ", AttackDamage: " + player.getWeapon().getDamage());
                demon.setHealth(demon.getHealth() - player.getWeapon().getDamage());
                if(demon.getHealth() <= 0){
                    int gainedOrbs = new Random().nextInt(10,20);
                    System.out.println("You have won!");
                    player.addOrbs(gainedOrbs);
                    world.removeDemonAtLocation(locationID);
                    return "Your gained orbs : " + gainedOrbs + "!";
                }
                player.setHealth(player.getHealth() - demon.getAttackDamage());
                if(player.getHealth() <= 0){
                    System.out.println("You have died!");
                    End.exit = true;
                    isDead = true;
                }
            }
        }
        return "";
    }
    @Override
    public boolean exit() {
        return false;
    }
}
