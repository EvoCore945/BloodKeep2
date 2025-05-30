package command;

import characters.Player;

public class PlayerInfo extends Command{
    /**
     * Executes the command and returns a string representation of the player's information.
     *
     * @return A string representation of the player's information.
     */
    @Override
    public String execute() {
        Player player = Player.getInstance();
        return player.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
