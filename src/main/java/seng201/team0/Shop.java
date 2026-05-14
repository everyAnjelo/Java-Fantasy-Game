package seng201.team0;

import java.util.ArrayList;

/**
 * Represents the shop/market in the game.
 *
 * The shop stores the list of items that the player can buy.
 * It also handles buying and selling items by using the current
 * GameEnvironment's gold and inventory.
 */
public class Shop {

    public ArrayList<Item> availableItems;

    /**
     * Creates a new shop and fills it with available items.
     */
    public Shop() {
        availableItems = new ArrayList<>();
        generateItems();
    }

    /**
     * Generates the items available in the shop.
     *
     * This method clears the current shop list first, then adds
     * weapons and potions that the player can buy.
     */
    public void generateItems() {
        availableItems.clear();

        availableItems.add(new Item("Iron Sword", ItemType.SWORD, 50, 20, 0, Role.WARRIOR));
        availableItems.add(new Item("Wooden Wand", ItemType.WAND, 60, 5, 25, Role.MAGE));
        availableItems.add(new Item("Hunter Bow", ItemType.BOW, 55, 0, 25, Role.MARKSMAN));
        availableItems.add(new Item("Sharp Dagger", ItemType.DAGGER, 50, 10, 15, Role.ROGUE));
        availableItems.add(new Item("Small Potion", ItemType.POTION, 25, 15, 5, null));
        availableItems.add(new Item("Focus Potion", ItemType.POTION, 30, 5, 20, null));

    }

    /**
     * Displays all items currently available in the shop.
     *
     * Each item is displayed with a number so the player can choose
     * which item they want to buy.
     */
    public void displayItems() {
        System.out.println("=== Market Items ===");

        for (int i = 0; i < availableItems.size(); i++) {
            System.out.println((i + 1) + ". " + availableItems.get(i).getItemInfo());
        }
    }

    /**
     * Creates a separate copy of an item.
     *
     * This is used when buying an item so the player receives their own
     * item object instead of directly using the shop's item object.
     *
     * @param item the item to copy
     * @return a new Item object with the same details
     */
    public Item copyItem(Item item) {
        return new Item(
                item.name,
                item.type,
                item.price,
                item.staminaBonus,
                item.perceptionBonus,
                item.allowedRole
        );
    }

    /**
     * Buys an item from the shop.
     *
     * The player must have enough gold to buy the item. If the purchase
     * is successful, the item's price is deducted from the player's gold,
     * and a copy of the item is added to the player's inventory.
     *
     * @param item the item the player wants to buy
     * @param game the current game environment containing the player's gold and inventory
     * @return true if the item was bought successfully, false otherwise
     */
    public boolean buyItem(Item item, GameEnvironment game) {
        if (item == null) {
            return false;
        }

        if (game.gold < item.price) {
            System.out.println("Not enough gold.");
            return false;
        }

        game.gold -= item.price;

        Item boughtItem = copyItem(item);
        game.inventory.addItem(boughtItem);

        System.out.println("You bought " + item.name + " for " + item.price + " gold.");
        return true;
    }

    /**
     * Sells an item from the player's inventory.
     *
     * The item must exist in the player's inventory before it can be sold.
     * The player receives 80% of the item's original price as gold.
     *
     * @param item the item the player wants to sell
     * @param game the current game environment containing the player's gold and inventory
     * @return true if the item was sold successfully, false otherwise
     */
    public boolean sellItem(Item item, GameEnvironment game) {
        if (item == null) {
            return false;
        }

        if (!game.inventory.hasItem(item)) {
            System.out.println("You do not own this item.");
            return false;
        }

        double sellPrice = item.price * 0.8;

        game.inventory.removeItem(item);
        game.gold += sellPrice;

        System.out.println("You sold " + item.name + " for " + sellPrice + " gold.");
        return true;
    }

    public ArrayList<Item> getAvailableItems() {
        return availableItems;
    }
}