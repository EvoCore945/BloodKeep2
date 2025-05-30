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


