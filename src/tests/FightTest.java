package tests;

import World.WorldMap;
import characters.Demon;
import characters.Player;
import command.End;
import command.Fight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.transform.stream.StreamSource;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FightTest {
    @Test
public void testFightAtLocationWithoutDemon() {
    WorldMap worldMap = new WorldMap();
    Fight fight = new Fight(worldMap);
    try {
        Field positionField = WorldMap.class.getDeclaredField("currentPosition");
        positionField.setAccessible(true);
        positionField.setInt(null, 123);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set test position");
    }
    String result = fight.execute();
    assertEquals("There is no demon left", result);
}



}
