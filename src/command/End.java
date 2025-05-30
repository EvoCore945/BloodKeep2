package command;

public class End extends Command{

    public static boolean exit;
    /**
     * This method is responsible for executing the end command.
     * It will close the game.
     */
    @Override
    public String execute() {
        setExit(true);
        return "Closing game...";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public static void setExit(boolean exit) {
        End.exit = exit;
    }
}
