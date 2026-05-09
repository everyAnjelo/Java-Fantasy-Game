package seng201.team0;

import java.util.ArrayList;

public class Inventory {

    public ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        items.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

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

    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

    public int getSize() {
        return items.size();
    }

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