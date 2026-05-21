package seng201.team0.adventurer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the player's adventurers.
 *
 * This class keeps track of the main team, reserve team, and retired adventurers.
 * It also handles adding, removing, retiring, generating adventurers, and
 * calculating the team's expedition stats.
 */
public class AdventurerManager {

    public ArrayList<Adventurer> mainTeam;
    public ArrayList<Adventurer> reserveTeam;
    public ArrayList<Adventurer> retiredTeam;

    public final int maxMainTeam;
    public final int maxReserveTeam;

    public Random random;

    /**
     * Creates a new AdventurerManager.
     *
     * The manager starts with empty main, reserve, and retired teams.
     * The main team and reserve team can each hold up to 5 adventurers.
     */
    public AdventurerManager() {
        this.mainTeam = new ArrayList<>();
        this.reserveTeam = new ArrayList<>();
        this.maxMainTeam = 5;
        this.maxReserveTeam = 5;
        this.random = new Random();
        this.retiredTeam = new ArrayList<>();
    }

    /**
     * Adds an adventurer to the main team.
     *
     * The adventurer must not be null, the main team must not be full,
     * and the adventurer must not already be in the main team.
     * If the adventurer is currently in the reserve team, they are moved
     * from the reserve team to the main team.
     *
     * @param adventurer the adventurer to add to the main team
     */
    public void addToMain(Adventurer adventurer) {
        if (adventurer == null) {
            return;
        }

        if (mainTeam.size() >= maxMainTeam) {
            System.out.println("Main team is already full.");
            return;
        }

        if (mainTeam.contains(adventurer)) {
            System.out.println(adventurer.name + " is already in the main team.");
            return;
        }

        if (reserveTeam.contains(adventurer)) {
            reserveTeam.remove(adventurer);
        }

        mainTeam.add(adventurer);
        System.out.println(adventurer.name + " was added to the main team.");
    }

    /**
     * Adds an adventurer to the reserve team.
     *
     * The adventurer must not be null, the reserve team must not be full,
     * and the adventurer must not already be in the reserve team.
     * If the adventurer is currently in the main team, they are moved to
     * the reserve team, as long as at least one adventurer remains in the
     * main team.
     *
     * @param adventurer the adventurer to add to the reserve team
     */
    public void addToReserve(Adventurer adventurer) {
        if (adventurer == null) {
            return;
        }

        if (reserveTeam.size() >= maxReserveTeam) {
            System.out.println("Reserve team is already full.");
            return;
        }

        if (reserveTeam.contains(adventurer)) {
            System.out.println(adventurer.name + " is already in the reserve team.");
            return;
        }

        if (mainTeam.contains(adventurer)) {
            if (mainTeam.size() <= 1) {
                System.out.println("You must have at least one adventurer in the main team.");
                return;
            }

            mainTeam.remove(adventurer);
        }

        reserveTeam.add(adventurer);
        System.out.println(adventurer.name + " was added to the reserve team.");
    }

    /**
     * Removes an adventurer from the team.
     *
     * The adventurer is removed from both the main team and reserve team.
     * However, the player cannot remove their last main team adventurer.
     *
     * @param adventurer the adventurer to remove
     */
    public void removeAdventurer(Adventurer adventurer) {
        if (adventurer == null) {
            return;
        }

        if (mainTeam.contains(adventurer) && mainTeam.size() <= 1) {
            System.out.println("You must have at least one adventurer in the main team.");
            return;
        }

        mainTeam.remove(adventurer);
        reserveTeam.remove(adventurer);

        System.out.println(adventurer.name + " was removed.");
    }

    /**
     * Retires an adventurer.
     * Retiring an adventurer marks them as retired and removes them from
     * both the main team and reserve team. The player cannot retire their
     * last main team adventurer.
     *
     * @param adventurer the adventurer to retire
     */
    public void retireAdventurer(Adventurer adventurer) {
        if (adventurer == null) {
            return;
        }

        if (mainTeam.contains(adventurer) && mainTeam.size() <= 1) {
            System.out.println("You cannot retire your last main team adventurer.");
            return;
        }

        adventurer.retire();
        retiredTeam.add(adventurer);
        mainTeam.remove(adventurer);
        reserveTeam.remove(adventurer);

        System.out.println(adventurer.name + " has retired.");
    }

    public ArrayList<Adventurer> getMainTeam() {
        return mainTeam;
    }

    public void setMainTeam(ArrayList<Adventurer> mainTeam) {
        this.mainTeam = mainTeam;
    }

    public ArrayList<Adventurer> getReserveTeam() {
        return reserveTeam;
    }

    public void setReserveTeam(ArrayList<Adventurer> reserveTeam) {
        this.reserveTeam = reserveTeam;
    }

    /**
     * Gets all adventurers who can still go on expeditions.
     *
     * This includes available adventurers from both the main team and
     * reserve team.
     *
     * @return a list of adventurers who can still go on expeditions
     */
    public ArrayList<Adventurer> getAvailableAdventurers() {
        ArrayList<Adventurer> availableAdventurers = new ArrayList<>();

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                availableAdventurers.add(adventurer);
            }
        }

        for (Adventurer adventurer : reserveTeam) {
            if (adventurer.canGoOnExpedition()) {
                availableAdventurers.add(adventurer);
            }
        }

        return availableAdventurers;
    }

    /**
     * Calculates the total stamina of the main team.
     *
     * Only adventurers who can still go on expeditions are counted.
     *
     * @return the total stamina of the available main team adventurers
     */
    public int getTotalStamina() {
        int total = 0;

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                total += adventurer.stamina;
            }
        }

        return total;
    }

    /**
     * Calculates the total perception of the main team.
     *
     * Only adventurers who can still go on expeditions are counted.
     *
     * @return the total perception of the available main team adventurers
     */
    public int getTotalPerception() {
        int total = 0;

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                total += adventurer.perception;
            }
        }

        return total;
    }

    /**
     * Calculates the total pay required for the main team.
     *
     * Only adventurers who can still go on expeditions are counted.
     *
     * @return the total pay of the available main team adventurers
     */
    public double getTotalPay() {
        double total = 0;

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                total += adventurer.pay;
            }
        }

        return total;
    }

    /**
     * Updates the main team after completing an expedition.
     *
     * Each available main team adventurer has their expedition count increased.
     * If an adventurer reaches their maximum number of expeditions, they are
     * added to a temporary retirement list and retired after the loop.
     */
    public void completeExpeditionForTeam() {
        ArrayList<Adventurer> toRetire = new ArrayList<>();

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                adventurer.completeExpedition();

                if (!adventurer.canGoOnExpedition()) {
                    toRetire.add(adventurer);
                }
            }
        }

        for (Adventurer adv : toRetire) {
            retireAdventurer(adv);
        }
    }

    /**
     * Generates a random new adventurer.
     *
     * The generated adventurer can be a Warrior, Mage, Marksman, or Rogue.
     * Their stats increase by 10% for each completed expedition, while their pay
     * decreases by 10% for each completed expedition.
     *
     * @param completedExpeditions the number of expeditions already completed
     * @return a randomly generated adventurer with scaled stats and pay
     */
    public Adventurer generateNewAdventurer(int completedExpeditions) {
        int choice = random.nextInt(4);
        int number = random.nextInt(900) + 100;

        Adventurer adventurer;

        if (choice == 0) {
            adventurer = new Warrior("Warrior" + number);
        } else if (choice == 1) {
            adventurer = new Mage("Mage" + number);
        } else if (choice == 2) {
            adventurer = new Marksman("Marksman" + number);
        } else {
            adventurer = new Rogue("Rogue" + number);
        }

        double statMultiplier = 1 + (completedExpeditions * 0.10);
        double payMultiplier = 1 - (completedExpeditions * 0.10);

        adventurer.stamina = (int) (adventurer.stamina * statMultiplier);
        adventurer.perception = (int) (adventurer.perception * statMultiplier);
        adventurer.pay = adventurer.pay * payMultiplier;

        return adventurer;
    }

    /**
     * Displays all adventurers currently in the main team.
     */
    public void displayMainTeam() {
        System.out.println("=== Main Team ===");

        if (mainTeam.isEmpty()) {
            System.out.println("No adventurers in main team.");
            return;
        }

        for (Adventurer adventurer : mainTeam) {
            System.out.println(adventurer.getStats());
        }
    }

    /**
     * Displays all adventurers currently in the reserve team.
     */
    public void displayReserveTeam() {
        System.out.println("=== Reserve Team ===");

        if (reserveTeam.isEmpty()) {
            System.out.println("No adventurers in reserve team.");
            return;
        }

        for (Adventurer adventurer : reserveTeam) {
            System.out.println(adventurer.getStats());
        }
    }

    public int getMaxMainTeam() {
        return maxMainTeam;
    }

    public int getMaxReserveTeam() {
        return maxReserveTeam;
    }
}