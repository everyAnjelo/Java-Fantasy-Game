package seng201.team0;

/**
 * Represents an item that can be bought, stored, and used in the game.
 * Items can be weapons or potions. Weapons are usually restricted to
 * specific adventurer roles, while potions can be used by anyone.
 */
public class Item implements Purchasable {

    public String name;
    public ItemType type;
    public double price;
    public int staminaBonus;
    public int perceptionBonus;
    public Role allowedRole;

    /**
     * Creates a new item with its type, price, stat bonuses, and allowed role.
     * @param name the name of the item
     * @param type the type of item
     * @param price the cost of the item
     * @param staminaBonus the stamina bonus given by the item
     * @param perceptionBonus the perception bonus given by the item
     * @param allowedRole the role that is allowed to use the item
     */
    public Item(String name, ItemType type, double price, int staminaBonus, int perceptionBonus, Role allowedRole) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.staminaBonus = staminaBonus;
        this.perceptionBonus = perceptionBonus;
        this.allowedRole = allowedRole;
    }

    /**
     * Gets the price of the item.
     *
     * This method comes from the Purchasable interface.
     *
     * @return the price of the item
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Checks whether the item is a weapon.
     *
     * @return true if the item is a sword, wand, bow, or dagger; false otherwise
     */
    public boolean isWeapon() {
        return type == ItemType.SWORD ||
                type == ItemType.WAND ||
                type == ItemType.BOW ||
                type == ItemType.DAGGER;
    }

    /**
     * Checks whether an adventurer is allowed to use this item.
     *
     * Potions can be used by any adventurer. Other items can only be used
     * if the adventurer's role matches the item's allowed role.
     *
     * @param adventurer the adventurer trying to use the item
     * @return true if the adventurer can use the item, false otherwise
     */
    public boolean canBeUsedBy(Adventurer adventurer) {
        if (type == ItemType.POTION) {
            return true;
        }

        return adventurer.getRole() == allowedRole;
    }

    /**
     * Applies the item's stat bonuses to an adventurer.
     *
     * This increases the adventurer's stamina and perception based on
     * the item's bonus values.
     *
     * @param adventurer the adventurer receiving the stat bonuses
     */
    public void applyStats(Adventurer adventurer) {
        adventurer.addStamina(staminaBonus);
        adventurer.addPerception(perceptionBonus);
    }

    /**
     * Returns a formatted description of the item.
     *
     * The description includes the item name, type, price, stat bonuses,
     * and which role can use it.
     *
     * @return the item information as a string
     */
    public String getItemInfo() {
        String roleText;

        if (type == ItemType.POTION) {
            roleText = "Anyone";
        } else {
            roleText = allowedRole.toString();
        }

        return name +
                " | Type: " + type +
                " | Price: " + price +
                " | Stamina Bonus: " + staminaBonus +
                " | Perception Bonus: " + perceptionBonus +
                " | Usable By: " + roleText;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public Role getAllowedRole() {
        return allowedRole;
    }

    public void setAllowedRole(Role allowedRole) {
        this.allowedRole = allowedRole;
    }
}