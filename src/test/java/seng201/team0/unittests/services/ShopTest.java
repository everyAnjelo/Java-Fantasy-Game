package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.Item;
import seng201.team0.ItemType;
import seng201.team0.Role;
import seng201.team0.Shop;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Shop implementation.
 */
class ShopTest {

    private Shop testShop;
    private GameEnvironment testGame;

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setupTest() {
        testShop = new Shop();
        testGame = new GameEnvironment();
    }

    /**
     * Test that the shop starts with the correct number of items.
     */
    @Test
    void testConstructor() {
        assertEquals(6, testShop.getAvailableItems().size());
    }

    /**
     * Test that generateItems creates the correct number of items.
     */
    @Test
    void testGenerateItems() {
        testShop.getAvailableItems().clear();

        assertEquals(0, testShop.getAvailableItems().size());

        testShop.generateItems();

        assertEquals(6, testShop.getAvailableItems().size());
    }

    /**
     * Test that the first shop item is the Iron Sword.
     */
    @Test
    void testFirstGeneratedItem() {
        Item item = testShop.getAvailableItems().get(0);

        assertEquals("Iron Sword", item.name);
        assertEquals(ItemType.SWORD, item.type);
        assertEquals(50.0, item.price);
        assertEquals(20, item.staminaBonus);
        assertEquals(0, item.perceptionBonus);
        assertEquals(Role.WARRIOR, item.allowedRole);
    }

    /**
     * Test that copyItem creates a different object with the same values.
     */
    @Test
    void testCopyItem() {
        Item originalItem = testShop.getAvailableItems().get(0);

        Item copiedItem = testShop.copyItem(originalItem);

        assertNotSame(originalItem, copiedItem);
        assertEquals(originalItem.name, copiedItem.name);
        assertEquals(originalItem.type, copiedItem.type);
        assertEquals(originalItem.price, copiedItem.price);
        assertEquals(originalItem.staminaBonus, copiedItem.staminaBonus);
        assertEquals(originalItem.perceptionBonus, copiedItem.perceptionBonus);
        assertEquals(originalItem.allowedRole, copiedItem.allowedRole);
    }

    /**
     * Test buying an item with enough gold.
     */
    @Test
    void testBuyItemWithEnoughGold() {
        Item item = testShop.getAvailableItems().get(0);

        testGame.gold = 100;

        boolean result = testShop.buyItem(item, testGame);

        assertTrue(result);
        assertEquals(50.0, testGame.gold);
        assertEquals(1, testGame.inventory.getItems().size());
    }

    /**
     * Test buying an item without enough gold.
     */
    @Test
    void testBuyItemWithoutEnoughGold() {
        Item item = testShop.getAvailableItems().get(0);

        testGame.gold = 10;

        boolean result = testShop.buyItem(item, testGame);

        assertFalse(result);
        assertEquals(10.0, testGame.gold);
        assertEquals(0, testGame.inventory.getItems().size());
    }

    /**
     * Test buying a null item.
     */
    @Test
    void testBuyNullItem() {
        testGame.gold = 100;

        boolean result = testShop.buyItem(null, testGame);

        assertFalse(result);
        assertEquals(100.0, testGame.gold);
        assertEquals(0, testGame.inventory.getItems().size());
    }

    /**
     * Test selling an item that the player owns.
     */
    @Test
    void testSellOwnedItem() {
        Item item = testShop.getAvailableItems().get(0);

        testGame.inventory.addItem(item);
        testGame.gold = 100;

        boolean result = testShop.sellItem(item, testGame);

        assertTrue(result);
        assertEquals(140.0, testGame.gold);
        assertFalse(testGame.inventory.hasItem(item));
    }

    /**
     * Test selling an item that the player does not own.
     */
    @Test
    void testSellItemNotOwned() {
        Item item = testShop.getAvailableItems().get(0);

        testGame.gold = 100;

        boolean result = testShop.sellItem(item, testGame);

        assertFalse(result);
        assertEquals(100.0, testGame.gold);
    }

    /**
     * Test selling a null item.
     */
    @Test
    void testSellNullItem() {
        testGame.gold = 100;

        boolean result = testShop.sellItem(null, testGame);

        assertFalse(result);
        assertEquals(100.0, testGame.gold);
    }
}