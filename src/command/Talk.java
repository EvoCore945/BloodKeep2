package command;

import World.WorldMap;
import characters.NPC;

public class Talk extends Command{
    WorldMap world;

    public Talk(WorldMap world) {
        this.world = world;
    }
    /**
     * Executes the Talk command, allowing the player to interact with NPCs in the game.
     *
     * @return A string containing the dialogue of the NPC the player is talking to, or a message indicating that there's no one to talk to.
     */
    @Override
    public String execute() {
        int currentPosition = WorldMap.getCurrentPosition();
        NPC npc = world.getNPCAtLocation(currentPosition);
        if(npc != null){
            return npc.getName() + ":\n" + npc.getDialogue();
        }else{
            return "There's no one here to talk to.";
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
