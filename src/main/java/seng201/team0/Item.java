package seng201.team0;

public class Item implements Purchasable {

    public String name;
    public ItemType type;
    public double price;
    public int staminaBonus;
    public int perceptionBonus;
    public Role allowedRole;

    public Item(String name, ItemType type, double price, int staminaBonus, int perceptionBonus, Role allowedRole) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.staminaBonus = staminaBonus;
        this.perceptionBonus = perceptionBonus;
        this.allowedRole = allowedRole;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean isWeapon() {
        return type == ItemType.SWORD ||
                type == ItemType.WAND ||
                type == ItemType.BOW ||
                type == ItemType.DAGGER;
    }

    public boolean canBeUsedBy(Adventurer adventurer) {
        if (type == ItemType.POTION) {
            return true;
        }

        return adventurer.getRole() == allowedRole;
    }

    public void applyStats(Adventurer adventurer) {
        adventurer.addStamina(staminaBonus);
        adventurer.addPerception(perceptionBonus);
    }

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