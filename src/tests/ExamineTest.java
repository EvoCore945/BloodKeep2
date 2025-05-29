package tests;

import World.Item;
import World.ItemType;
import World.Location;
import World.WorldMap;
import command.Examine;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ExamineTest {
    private WorldMap worldMap;
    private Examine examine;

@Test
public void testExamineDetectsItemsInRoom() {
    worldMap = new WorldMap();
    examine = new Examine(worldMap);

    Location testLocation = new Location("Test Room", 99, new String[]{"-1", "-1", "-1", "-1"});
    worldMap.getWorld().put(99, testLocation);
    List<Item> items = new ArrayList<>();
    items.add(new Item("VitalStar_SMALL", "Heals 20 HP", ItemType.CONSUMABLE, 5));
    worldMap.getLocationItems().put(99, items);
    try {
        java.lang.reflect.Field positionField = WorldMap.class.getDeclaredField("currentPosition");
        positionField.setAccessible(true);
        positionField.setInt(null, 99);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(e);
    }
    String result = examine.execute();
    assertTrue(result.contains("Do you want to take any of these items?"));
}
@Test
public void testExamineReturnsUnknownIfLocationIsNull() {
    WorldMap worldMap = new WorldMap();
    Examine examine = new Examine(worldMap);
    try {
        java.lang.reflect.Field positionField = WorldMap.class.getDeclaredField("currentPosition");
        positionField.setAccessible(true);
        positionField.setInt(null, 123); // location 123 does not exist
    } catch (Exception e) {
        fail("Failed to set test position");
    }
    String result = examine.execute();
    assertEquals("Unknown location", result);
}
}


