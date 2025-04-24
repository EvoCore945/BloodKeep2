package command;

public class End extends Command{

    public static boolean exit;
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
