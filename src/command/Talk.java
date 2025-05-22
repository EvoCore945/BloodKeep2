package command;

import World.WorldMap;
import characters.NPC;

public class Talk extends Command{
    WorldMap world;

    public Talk(WorldMap world) {
        this.world = world;
    }

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
