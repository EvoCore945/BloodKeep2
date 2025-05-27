import World.InitializePlayer;
import World.WorldMap;
import command.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
        commands.put("commands",new ShowCommands());
        commands.put("examine", new Examine(worldMap));
        commands.put("stats", new PlayerInfo());
        commands.put("backpack", new Backpack());
        commands.put("fight", new Fight(worldMap));
        commands.put("shop",new Shop(worldMap));
        commands.put("exit",new End());
        commands.put("talk",new Talk(worldMap));

    }


    public void doCommand() throws IOException {
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

    public void startGame() throws IOException {
        introductiontext();
        InitializePlayer.initializePlayer();
        initializeCommands();
        System.out.println("Welcome to The BloodKeep!");
        System.out.println("Type 'commands' to see available commands.");
            do{
                doCommand();
            }while(!End.exit);

        System.out.println("Game Over.");
    }
    private static void introductiontext(){

        try(BufferedReader reader =new BufferedReader(new FileReader("src/Files/Introduction.txt"))){
            String line;

            while((line = reader.readLine())!= null){
                System.out.println(line);
                Thread.sleep(1000);
            }
        } catch(IOException | InterruptedException e){
            System.out.println("Could not load introduction text.");
        }
    }


    }





