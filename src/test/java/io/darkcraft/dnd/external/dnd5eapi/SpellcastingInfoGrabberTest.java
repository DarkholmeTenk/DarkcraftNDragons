package io.darkcraft.dnd.external.dnd5eapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.darkcraft.dnd.character.data.Attribute;
import io.darkcraft.dnd.stats.Spellcasting;

public class SpellcastingInfoGrabberTest
{
    private static String data = "The sphinx is a 12th-level spellcaster. "
            + "Its spellcasting ability is Wisdom (spell save DC 18, +10 to hit with spell attacks). "
            + "It requires no material components to cast its spells. "
            + "The sphinx has the following cleric spells prepared:"
            + "\n\n• Cantrips (at will): sacred flame, spare the dying, thaumaturgy\n"
            + "• 1st level (4 slots): command, detect evil and good, detect magic\n"
            + "• 2nd level (3 slots): lesser restoration, zone of truth\n"
            + "• 3rd level (3 slots): dispel magic, tongues\n"
            + "• 4th level (3 slots): banishment, freedom of movement\n"
            + "• 5th level (2 slots): flame strike, greater restoration\n"
            + "• 6th level (1 slot): heroes' feast";

    private static Spellcasting sc;
    
    @BeforeClass
    public static void setup()
    {
        sc = SpellcastingInfoGrabber.getInfo(data);
    }
    
    @Test
    public void testLevel()
    {
        assertEquals(Integer.valueOf(12), sc.level);
    }
    
    @Test
    public void testAttribute()
    {
        assertEquals(Attribute.WIS, sc.ability);
    }
    
    @Test
    public void testDC()
    {
        assertEquals(Integer.valueOf(18), sc.dc);
    }
    
    @Test
    public void testMod()
    {
        assertEquals(Integer.valueOf(10), sc.attackMod);
    }
    
    @Test
    public void testSlots()
    {
        assertEquals(Integer.valueOf(4), sc.slots.get(1));
        assertEquals(Integer.valueOf(3), sc.slots.get(2));
        assertEquals(Integer.valueOf(3), sc.slots.get(3));
        assertEquals(Integer.valueOf(3), sc.slots.get(4));
        assertEquals(Integer.valueOf(2), sc.slots.get(5));
        assertEquals(Integer.valueOf(1), sc.slots.get(6));
    }
    
    @Test
    public void testCantrips()
    {
        assertThat(sc.spells.get(0), Matchers.hasItems("sacred flame", "spare the dying", "thaumaturgy"));
    }
    
    @Test
    public void testSpells()
    {
        assertThat(sc.spells.get(1), Matchers.hasItems("command", "detect evil and good", "detect magic"));
        assertThat(sc.spells.get(2), Matchers.hasItems("lesser restoration", "zone of truth"));
        assertThat(sc.spells.get(3), Matchers.hasItems("dispel magic", "tongues"));
        assertThat(sc.spells.get(4), Matchers.hasItems("banishment", "freedom of movement"));
        assertThat(sc.spells.get(5), Matchers.hasItems("flame strike", "greater restoration"));
        assertThat(sc.spells.get(6), Matchers.hasItems("heroes' feast"));
    }
}
