package tests;

import World.PlayerClasses;
import World.Weapon;
import characters.Player;
import command.PlayerInfo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerInfoTest {

    @Before
    public  void setup(){
        Player player = Player.getInstance();
        player.setName("Dante");
        player.setHealth(100);
        player.setOrbs(50);
        player.setPlayerClass(PlayerClasses.SWORDSMAN);
        player.setWeapon(new Weapon("Rebellion", 25));
    }

    @Test
    public void executeReturnsPlayerInfoTest() {
        PlayerInfo infoCommand = new PlayerInfo();
        String result = infoCommand.execute();

        assertTrue(result.contains("Dante"));
        assertTrue(result.contains("SWORDSMAN"));
        assertTrue(result.contains("Rebellion"));
        assertTrue(result.contains("100"));
        assertTrue(result.contains("25"));
        assertTrue(result.contains("50"));
    }
    @Test
    public void testExitAlwaysFalse() {
        PlayerInfo infoCommand = new PlayerInfo();
        assertFalse(infoCommand.exit());
    }

    }

