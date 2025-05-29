package tests;

import World.WorldMap;
import characters.NPC;
import command.Talk;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class TalkTest {

    public static class TestWorldMap extends WorldMap {
        private NPC npc;

        public void setNPCAtPosition(NPC npc) {
            this.npc = npc;
        }
        public NPC getNPCAtLocation(int position) {
            return npc;
        }

        private TestWorldMap worldMap;

        public void setup() {
            worldMap = new TestWorldMap();
        }
@Test
public void talkWithNPCTest(){
    NPC testNpc = new NPC("Old Sage", "Beware the shadows in the west...");
worldMap.setNPCAtPosition(testNpc);
    Talk talk = new Talk(worldMap);
    String result = talk.execute();

    assertEquals("Old Sage:\nBeware the shadows in the west...", result);
}
    @Test
    public void talkTalkWithNoNPC(){
    worldMap.setNPCAtPosition(null);

    Talk talk = new Talk(worldMap);
    String result = talk.execute();
    assertEquals("There's no one here to talk to.", result);
}
@Test
        public void exitAlwaysFalseTest(){
    Talk talk = new Talk(worldMap);
    assertFalse(talk.exit());
}

    }

}
