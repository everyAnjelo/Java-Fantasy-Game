package seng201.team0.adventurer;

import seng201.team0.item.Item;
import seng201.team0.item.ItemType;

/**
 * Represents a general adventurer in the game.
 *
 * This is an abstract class because every adventurer shares common data
 * such as name, role, stamina, perception, pay, and hiring cost, but each
 * specific adventurer type should have its own special skill.
 */
public abstract class Adventurer {

    public String name;
    public Role role;
    public int stamina;
    public int perception;
    public double pay;
    public double hiringCost;
    public int expeditionCount;
    public final int maxExpeditions;
    public boolean isRetired;
    public Item equippedWeapon;

    /**
     * Creates a new adventurer with the given starting stats.
     *
     * Each adventurer starts with 0 completed expeditions, a maximum of
     * 5 expeditions and is not retired.
     *
     * @param name the name of the adventurer
     * @param role the role/class of the adventurer
     * @param stamina the stamina stat of the adventurer
     * @param perception the perception stat of the adventurer
     * @param pay the amount the adventurer must be paid
     * @param hiringCost the cost to hire the adventurer
     */
    public Adventurer(String name, Role role, int stamina, int perception, double pay, double hiringCost) {
        this.name = name;
        this.role = role;
        this.stamina = stamina;
        this.perception = perception;
        this.pay = pay;
        this.hiringCost = hiringCost;
        this.expeditionCount = 0;
        this.maxExpeditions = 5;
        this.isRetired = false;
        this.equippedWeapon = null;
    }

    /**
     * Performs the adventurer's special skill.
     *
     * This is abstract because each subclass of Adventurer should provide
     * its own version of the skill.
     */
    public abstract void specialSkill();

    /**
     * Checks whether the adventurer is still allowed to join an expedition.
     *
     * An adventurer can go on an expedition if they are not retired and
     * have not reached the maximum number of expeditions.
     *
     * @return true if the adventurer can still go on an expedition, false otherwise
     */
    public boolean canGoOnExpedition() {
        return !isRetired && expeditionCount < maxExpeditions;
    }

    /**
     * Adds one to the adventurer's completed expedition count.
     */
    public void completeExpedition() {
        expeditionCount++;

        if(expeditionCount == 3){
            specialSkill();
            System.out.println(name + "use the special skills");
        }
    }

    /**
     * Retires the adventurer, preventing them from joining future expeditions.
     */
    public void retire() {
        isRetired = true;
    }

    /**
     * Equips a weapon to the adventurer.
     *
     * The item must not be null, must be a weapon, must be usable by this
     * adventurer, and the adventurer must not already have a weapon equipped.
     *
     * If the weapon is successfully equipped, its stats are applied to
     * the adventurer.
     *
     * @param weapon the weapon item to equip
     */
    public void equipWeapon(Item weapon) {
        if (weapon == null) {
            return;
        }

        if (!weapon.isWeapon()) {
            System.out.println(weapon.name + " is not a weapon.");
            return;
        }

        if (!weapon.canBeUsedBy(this)) {
            System.out.println(weapon.name + " cannot be equipped by " + role);
            return;
        }

        if (equippedWeapon != null) {
            System.out.println(name + " already has a weapon equipped: " + equippedWeapon.name);
            return;
        }

        equippedWeapon = weapon;
        weapon.applyStats(this);

        System.out.println(name + " equipped " + weapon.name);
    }

    /**
     * Uses a potion on the adventurer.
     *
     * The item must not be null and must be a potion.
     * If valid, the potion's stats are applied to the adventurer.
     *
     * @param potion the potion item to use
     */
    public void usePotion(Item potion) {
        if (potion == null) {
            return;
        }

        if (potion.type != ItemType.POTION) {
            System.out.println(potion.name + " is nto a potion.");
            return;
        }

        potion.applyStats(this);

        System.out.println(name + " used " + potion.name);
    }

    /**
     * Increases the adventurer's stamina.
     *
     * @param amount the amount of stamina to add
     */
    public void addStamina(int amount) {
        stamina += amount;
    }

    /**
     * Increases the adventurer's perception.
     *
     * @param amount the amount of perception to add
     */
    public void addPerception(int amount) {
        perception += amount;
    }

    /**
     * Returns a formatted string containing the adventurer's current stats.
     *
     * This includes their name, role, stamina, perception, pay, hiring cost,
     * expedition count, retirement status, and equipped weapon.
     *
     * @return the adventurer's stats as a string
     */
    public String getStats() {
        String weaponName;

        if (equippedWeapon == null) {
            weaponName = "None";
        } else {
            weaponName = equippedWeapon.name;
        }

        return name +
                " | Role: " + role +
                " | Stamina: " + stamina +
                " | Perception: " + perception +
                " | Pay: " + pay +
                " | Hiring Cost: " + hiringCost +
                " | Expeditions: " + expeditionCount + "/" + maxExpeditions +
                " | Retired: " + isRetired +
                " | Weapon: " + weaponName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getHiringCost() {
        return hiringCost;
    }

    public void setHiringCost(double hiringCost) {
        this.hiringCost = hiringCost;
    }

    public int getExpeditionCount() {
        return expeditionCount;
    }

    public void setExpeditionCount(int expeditionCount) {
        this.expeditionCount = expeditionCount;
    }

    public int getMaxExpeditions() {
        return maxExpeditions;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }
}