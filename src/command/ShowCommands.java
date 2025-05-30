package command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShowCommands extends Command{
    /**
     * Executes the command to read and print all commands from the specified file.
     *
     * @return An empty string as this command does not produce any output.
     * @throws IOException If an I/O error occurs while reading the file.
     */
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
