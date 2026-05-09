package seng201.team0;

import java.util.ArrayList;

public class Shop {

    public ArrayList<Item> availableItems;

    public Shop() {
        availableItems = new ArrayList<>();
        generateItems();
    }

    public void generateItems() {
        availableItems.clear();

        availableItems.add(new Item("Iron Sword", ItemType.SWORD, 50, 20, 0, Role.WARRIOR));
        availableItems.add(new Item("Wooden Wand", ItemType.WAND, 60, 5, 25, Role.MAGE));
        availableItems.add(new Item("Hunter Bow", ItemType.BOW, 55, 0, 25, Role.MARKSMAN));
        availableItems.add(new Item("Sharp Dagger", ItemType.DAGGER, 50, 10, 15, Role.ROGUE));
        availableItems.add(new Item("Small Potion", ItemType.POTION, 25, 15, 5, null));
        availableItems.add(new Item("Focus Potion", ItemType.POTION, 30, 5, 20, null));

        availableItems.add(new Item("Small Potion", ItemType.POTION, 25, 15, 5, null));
    }

    public void displayItems() {
        System.out.println("=== Market Items ===");

        for (int i = 0; i < availableItems.size(); i++) {
            System.out.println((i + 1) + ". " + availableItems.get(i).getItemInfo());
        }
    }

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