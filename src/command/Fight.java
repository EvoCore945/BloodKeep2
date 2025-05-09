package command;

import World.Item;
import World.WorldMap;
import characters.Demon;
import characters.Player;

import java.util.Random;
import java.util.Scanner;

public class Fight extends Command{

    private boolean isDead = false;
    Random rd = new Random();
    @Override
    public String execute() {
       /* Scanner sc = new Scanner(System.in);
        int currentPosition = WorldMap.getCurrentPosition();

        Player player = Player.getInstance();
        Demon demon =  Demon.getMonsterAt(currentPosition);

        if (monster == null){
            return "There is no monster in this location.";
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("You have found " + monster.getName() + "!");
        System.out.println("Monster health: " + monster.getHealth() + " Monster damage: "+ monster.getAttackDamage() );
        System.out.println("                VS              ");
        System.out.println("Your health: " + Player.getInstance().getHealth() + " Your damage: " + Player.getInstance().getAttackDamage());
        System.out.println("You can fight (shoot) or escape (escape)");
        String choice = sc.nextLine().toLowerCase();

        if("escape".equals(choice)){
            return "You escaped!";
        }else if("shoot".equals(choice)){
            System.out.println("The fight begins with " + monster.getName() + "!");

            while (player.getHealth() > 0 && monster.getHealth() > 0){
                System.out.println(monster.getName() + ", Health: " + monster.getHealth() + ", AttackDamage: " + monster.getAttackDamage());
                System.out.println(player.getName() + ", Health: " + player.getHealth() + ", AttackDamage: " + player.getAttackDamage());

                monster.setHealth(monster.getHealth() - player.getAttackDamage());
                if(monster.getHealth() <= 0){

                    System.out.println("----------------------------------------------------------------");
                    System.out.println("You have won!");
                    player.setHealth(player.getHealth() + hpReward);
                    player.setAttackDamage(player.getAttackDamage() + attackDamageReward);

                    if(monster.getName().equals("OmegaX-07 (Boss)")){
                        System.out.println("The Final BOSS has been defeated! He dropped Electro Stabilizer!");
                        Backpack.addItemToBackpack(new Item("Electro Stabilizer", "Use it to shut down the Portal in Portalova Komora and end this madness!",FINALITEM,false));
                    }
                    Monster.removeMonster(currentPosition);
                    return "You have gained extra health: " + hpReward + " and attackDamage: " + attackDamageReward + "!";
                }
                player.setHealth(player.getHealth() - monster.getAttackDamage());
                if(player.getHealth() <= 0){
                    System.out.println("You have died!");
                    End.exit = true;
                    isDead = true;
                }
            }

        }

        */
        return "";
    }
    @Override
    public boolean exit() {
        return false;
    }
}
