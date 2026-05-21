package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.inventory.Inventory;
import seng201.team0.item.Item;
import seng201.team0.item.ItemType;
import seng201.team0.adventurer.Role;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Inventory implementation.
 */
class InventoryTest {

    private Inventory testInventory;
    private Item testSword;
    private Item testPotion;

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setupTest() {
        testInventory = new Inventory();

        testSword = new Item("Iron Sword", ItemType.SWORD, 50, 20, 0, Role.WARRIOR);
        testPotion = new Item("Small Potion", ItemType.POTION, 25, 15, 5, null);
    }

    /**
     * Test that inventory starts empty.
     */
    @Test
    void testConstructor() {
        assertEquals(0, testInventory.getSize());
        assertTrue(testInventory.isEmpty());
    }

    /**
     * Test adding an item to the inventory.
     */
    @Test
    void testAddItem() {
        testInventory.addItem(testSword);

        assertEquals(1, testInventory.getSize());
        assertTrue(testInventory.hasItem(testSword));
        assertFalse(testInventory.isEmpty());
    }

    /**
     * Test that null is not added to the inventory.
     */
    @Test
    void testAddNullItem() {
        testInventory.addItem(null);

        assertEquals(0, testInventory.getSize());
        assertTrue(testInventory.isEmpty());
    }

    /**
     * Test removing an item from the inventory.
     */
    @Test
    void testRemoveItem() {
        testInventory.addItem(testSword);

        assertEquals(1, testInventory.getSize());

        testInventory.removeItem(testSword);

        assertEquals(0, testInventory.getSize());
        assertFalse(testInventory.hasItem(testSword));
        assertTrue(testInventory.isEmpty());
    }

    /**
     * Test removing null from inventory.
     */
    @Test
    void testRemoveNullItem() {
        testInventory.addItem(testSword);

        testInventory.removeItem(null);

        assertEquals(1, testInventory.getSize());
        assertTrue(testInventory.hasItem(testSword));
    }

    /**
     * Test removing an item that is not in the inventory.
     */
    @Test
    void testRemoveItemNotInInventory() {
        testInventory.addItem(testSword);

        testInventory.removeItem(testPotion);

        assertEquals(1, testInventory.getSize());
        assertTrue(testInventory.hasItem(testSword));
        assertFalse(testInventory.hasItem(testPotion));
    }

    /**
     * Test checking if inventory has an item.
     */
    @Test
    void testHasItem() {
        assertFalse(testInventory.hasItem(testSword));

        testInventory.addItem(testSword);

        assertTrue(testInventory.hasItem(testSword));
    }

    /**
     * Test getting an item using a valid index.
     */
    @Test
    void testGetItemValidIndex() {
        testInventory.addItem(testSword);
        testInventory.addItem(testPotion);

        assertEquals(testSword, testInventory.getItem(0));
        assertEquals(testPotion, testInventory.getItem(1));
    }

    /**
     * Test getting an item using a negative index.
     */
    @Test
    void testGetItemNegativeIndex() {
        testInventory.addItem(testSword);

        assertNull(testInventory.getItem(-1));
    }

    /**
     * Test getting an item using an index that is too large.
     */
    @Test
    void testGetItemIndexTooLarge() {
        testInventory.addItem(testSword);

        assertNull(testInventory.getItem(1));
    }

    /**
     * Test getting the inventory size.
     */
    @Test
    void testGetSize() {
        assertEquals(0, testInventory.getSize());

        testInventory.addItem(testSword);
        assertEquals(1, testInventory.getSize());

        testInventory.addItem(testPotion);
        assertEquals(2, testInventory.getSize());
    }

    /**
     * Test getting the full item list.
     */
    @Test
    void testGetItems() {
        testInventory.addItem(testSword);
        testInventory.addItem(testPotion);

        assertEquals(2, testInventory.getItems().size());
        assertTrue(testInventory.getItems().contains(testSword));
        assertTrue(testInventory.getItems().contains(testPotion));
    }
}