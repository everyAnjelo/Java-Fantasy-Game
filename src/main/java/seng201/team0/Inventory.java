package seng201.team0;

import java.util.ArrayList;

/**
 * Represents the player's inventory.
 *
 * This class stores all items owned by the player and provides methods
 * for adding, removing, checking, retrieving, and displaying items.
 */
public class Inventory {

    public ArrayList<Item> items;

    /**
     * Creates a new empty inventory.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     *
     * If the item is null, nothing happens.
     *
     * @param item the item to add to the inventory
     */
    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        items.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    /**
     * Removes an item from the inventory.
     *
     * The item is only removed if it exists in the inventory.
     * If the item is not found, a message is displayed.
     *
     * @param item the item to remove from the inventory
     */
    public void removeItem(Item item) {
        if (item == null) {
            return;
        }

        if (items.contains(item)) {
            items.remove(item);
            System.out.println(item.getName() + " removed from inventory.");
        } else {
            System.out.println("Item is not in inventory.");
        }
    }

    /**
     * Checks whether the inventory contains a specific item.
     *
     * @param item the item to check for
     * @return true if the item is in the inventory, false otherwise
     */
    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    /**
     * Checks whether the inventory has no items.
     *
     * @return true if the inventory is empty, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Gets an item from the inventory using its index.
     *
     * If the index is invalid, null is returned.
     *
     * @param index the position of the item in the inventory
     * @return the item at the given index, or null if the index is invalid
     */
    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

    /**
     * Gets the number of items currently in the inventory.
     *
     * @return the size of the inventory
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Displays all items currently stored in the inventory.
     *
     * If the inventory is empty, a message is displayed instead.
     */
    public void displayItems() {
        System.out.println("=== Inventory ===");

        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getItemInfo());
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}