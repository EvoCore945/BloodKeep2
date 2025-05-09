import World.InitializePlayer;
import World.WorldMap;
import characters.Player;
import command.*;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Console {

    private Scanner sc;
    private Map<String, Command> commands = new HashMap<>();
    private WorldMap worldMap;

    public Console(WorldMap worldMap) {
        this.sc = new Scanner(System.in);
        this.worldMap = worldMap;
    }

    public Console() {
        this.sc = new Scanner(System.in);
    }


    private void initializeCommands() {
        commands.put("go", new Go());
        commands.put("examine", new Examine(worldMap));
        commands.put("stats", new PlayerInfo());
        commands.put("backpack", new Backpack());

    }
    public static void initializeDemons(){

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
        InitializePlayer.initializePlayer();
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
    }





