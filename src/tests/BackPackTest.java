package tests;

import World.Item;
import World.ItemType;
import command.Backpack;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BackPackTest {

    void setup(){
        Backpack.getBackpack().clear();
    }
    @Test
    public void addItemToBackpackTest(){
        Item item = new Item("VitalStar_SMALL", "Restores 20 HP", ItemType.CONSUMABLE, 5);
        Backpack.addItemToBackpack(item);
        assertEquals(1, Backpack.getBackpack().size());
        assertEquals("VitalStar_SMALL", Backpack.getBackpack().get(0).getName());
    }
    @Test
    public void removeItemFromBackpackByNameTest(){
        Item item = new Item("Rusty Key", "Opens an old door", ItemType.KEY, 0);
        Backpack.addItemToBackpack(item);
        boolean removed = Backpack.removeItemFromBackpackByName("Rusty Key");
        assertTrue(removed);
        assertTrue(Backpack.getBackpack().isEmpty());
    }
    @Test
    public void HasAllBloodkeepShardsTest(){
        Backpack.addItemToBackpack(new Item("Echo shard of the BloodKeep", "One of the three keys", ItemType.SPECIAL_KEY, 0));
        Backpack.addItemToBackpack(new Item("Bloodlust shard of the BloodKeep", "One of the three keys", ItemType.SPECIAL_KEY, 0));
        Backpack.addItemToBackpack(new Item("Emerald shard of the BloodKeep", "One of the three keys", ItemType.SPECIAL_KEY, 0));
        assertTrue(Backpack.hasAllBloodkeepShards());
    }

    @Test
    public void testHasAllBloodkeepShards_MissingOne() {
        Backpack.addItemToBackpack(new Item("Echo shard of the BloodKeep", "One of the three keys", ItemType.SPECIAL_KEY, 0));
        Backpack.addItemToBackpack(new Item("Bloodlust shard of the BloodKeep", "One of the three keys", ItemType.SPECIAL_KEY, 0));
        assertFalse(Backpack.hasAllBloodkeepShards());
    }

}
