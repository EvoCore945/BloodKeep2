package command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShowCommands extends Command{
    @Override
    public String execute() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/Files/commands.txt"));
        String line;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
