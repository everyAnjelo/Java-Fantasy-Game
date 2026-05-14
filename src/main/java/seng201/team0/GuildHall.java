package seng201.team0;

import java.util.ArrayList;

public class GuildHall {

    public ArrayList<Adventurer> availableAdventurers;

    public GuildHall() {
        availableAdventurers = new ArrayList<>();
        generateStartingAdventurers();
    }

    /**
     * Generates the starting adventurers available in the guild hall.
     */
    public void generateStartingAdventurers() {
        availableAdventurers.clear();

        availableAdventurers.add(new Warrior("Warrior"));
        availableAdventurers.add(new Mage("Mage"));
        availableAdventurers.add(new Marksman("Marksman"));
        availableAdventurers.add(new Rogue("Rogue"));
    }

    /**
     * Replaces all guild hall adventurers after each expedition.
     *
     * Each new adventurer gets 10% higher stamina and perception
     * based on the number of completed expeditions.
     *
     * Their pay and hiring cost become lower.
     *
     * @param completedExpeditions the number of expeditions the player has completed
     */
    public void replaceAdventurersAfterExpedition(int completedExpeditions) {
        availableAdventurers.clear();

        Adventurer warrior = new Warrior("Warrior");
        Adventurer mage = new Mage("Mage");
        Adventurer marksman = new Marksman("Marksman");
        Adventurer rogue = new Rogue("Rogue");

        upgradeAdventurer(warrior, completedExpeditions);
        upgradeAdventurer(mage, completedExpeditions);
        upgradeAdventurer(marksman, completedExpeditions);
        upgradeAdventurer(rogue, completedExpeditions);

        availableAdventurers.add(warrior);
        availableAdventurers.add(mage);
        availableAdventurers.add(marksman);
        availableAdventurers.add(rogue);
    }

    /**
     * Increases an adventurer's stats and lowers their cost.
     *
     * @param adventurer the adventurer being upgraded
     * @param completedExpeditions the number of completed expeditions
     */
    public void upgradeAdventurer(Adventurer adventurer, int completedExpeditions) {
        double statMultiplier = 1 + (completedExpeditions * 0.10);
        double costMultiplier = 1 - (completedExpeditions * 0.10);

        if (costMultiplier < 0.5) {
            costMultiplier = 0.5;
        }

        adventurer.stamina = (int) (adventurer.stamina * statMultiplier);
        adventurer.perception = (int) (adventurer.perception * statMultiplier);
        adventurer.pay = adventurer.pay * costMultiplier;
        adventurer.hiringCost = adventurer.hiringCost * costMultiplier;
    }

    /**
     * Displays all adventurers available to hire.
     */
    public void displayAvailableAdventurers() {
        System.out.println("=== Guild Hall Adventurers ===");

        for (int i = 0; i < availableAdventurers.size(); i++) {
            System.out.println((i + 1) + ". " + availableAdventurers.get(i).getStats());
        }
    }

    public ArrayList<Adventurer> getAvailableAdventurers() {
        return availableAdventurers;
    }
}