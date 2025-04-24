import World.WorldMap;
import characters.Player;
import command.Command;
import command.End;
import command.Go;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Console {

    private Scanner sc;
    private Map<String, Command> commands = new HashMap<>();

    public Console() {
        this.sc = new Scanner(System.in);
    }


    private void initializeCommands() {
        WorldMap worldMap = new WorldMap();
        commands.put("go", new Go());

    }


    public void doCommand(){
        System.out.println("What do you want to do?");
        String command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();

        if(commands.containsKey(command)){
            System.out.println("--->" + commands.get(command).execute());
        }else{
            System.out.println("---> Nondefined command");
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player = Player.getInstance();
        player.setName(playerName);
        chooseClass();
        initializeCommands();
        System.out.println("Welcome to The BloodKeep!");
        System.out.println("Type 'hint' to see available commands.");

        try{
            do{
                doCommand();
            }while(!End.exit);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Game Over.");
    }
    public void chooseClass(){
        System.out.println("Choose one of these classes");
        System.out.println("1 -> Swordsman");
        System.out.println("2 -> Gunslinger");
        System.out.println("3 --> Tank");
        int response = sc.nextInt();
        try {
            switch (response) {
                case 1:
                    System.out.println("You chose Swordsman class !");
                    break;
                case 2:
                    System.out.println("You chose Gunslinger class!");
                    break;
                case 3:
                    System.out.println("You chose Tank class !");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong response!");

        }
        }

    }





