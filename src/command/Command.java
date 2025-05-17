package command;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Command {

protected String command;

public void setCommand(String command){
    this.command = command;
}

public abstract String execute() throws IOException;
    public abstract  boolean exit();
}
