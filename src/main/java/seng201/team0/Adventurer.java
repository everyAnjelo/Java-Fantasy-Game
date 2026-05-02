package seng201.team0;

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

    public abstract void specialSkill();

    public boolean canGoOnExpedition() {
        return !isRetired && expeditionCount < maxExpeditions;
    }

    public void completeExpedition() {
        expeditionCount++;


    }

    public void retire() {
        isRetired = true;
    }

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

    public void usePotion(Item potion) {
        if (potion == null) {
            return;
        }

        if (potion.type != ItemType.POTION) {
            System.out.println(potion.name + " is not a potion.");
            return;
        }

        potion.applyStats(this);

        System.out.println(name + " used " + potion.name);
    }

    public void addStamina(int amount) {
        stamina += amount;
    }

    public void addPerception(int amount) {
        perception += amount;
    }

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