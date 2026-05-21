package seng201.team0.unittests;

import org.junit.jupiter.api.Test;
import seng201.team0.ItemType;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ItemType enum.
 */
class ItemTypeTest {

    /**
     * Test that ItemType has the correct number of values.
     */
    @Test
    void testItemTypeLength() {
        assertEquals(5, ItemType.values().length);
    }

    /**
     * Test the SWORD item type.
     */
    @Test
    void testSwordItemType() {
        assertEquals(ItemType.SWORD, ItemType.valueOf("SWORD"));
    }

    /**
     * Test the WAND item type.
     */
    @Test
    void testWandItemType() {
        assertEquals(ItemType.WAND, ItemType.valueOf("WAND"));
    }

    /**
     * Test the BOW item type.
     */
    @Test
    void testBowItemType() {
        assertEquals(ItemType.BOW, ItemType.valueOf("BOW"));
    }

    /**
     * Test the DAGGER item type.
     */
    @Test
    void testDaggerItemType() {
        assertEquals(ItemType.DAGGER, ItemType.valueOf("DAGGER"));
    }

    /**
     * Test the POTION item type.
     */
    @Test
    void testPotionItemType() {
        assertEquals(ItemType.POTION, ItemType.valueOf("POTION"));
    }
}