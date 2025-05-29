package tests;

import World.Weapon;
import org.junit.Test;
import static org.junit.Assert.*;
public class WeaponTest {

    @Test
    public void testWeaponConstructorAndGetters(){
        Weapon weapon = new Weapon("Rebellion", 25);
        assertEquals("Rebellion", weapon.getName());
        assertEquals(25, weapon.getDamage());
    }
    @Test
    public void SettersTest() {
        Weapon weapon = new Weapon("Old Sword", 10);
        weapon.setName("Sparda");
        weapon.setDamage(100);

        assertEquals("Sparda", weapon.getName());
        assertEquals(100, weapon.getDamage());
    }
@Test
    public void ToStringTest(){
        Weapon weapon = new Weapon("Yamato", 50);
        String expected = "Yamato, damage:50";
        assertEquals(expected, weapon.toString());
}



}
