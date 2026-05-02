package seng201.team0;

import java.util.ArrayList;
import java.util.Random;

public class AdventurerManager {

    public ArrayList<Adventurer> mainTeam;
    public ArrayList<Adventurer> reserveTeam;
    public ArrayList<Adventurer> retiredTeam;

    public final int maxMainTeam;
    public final int maxReserveTeam;

    public Random random;

    public AdventurerManager() {
        this.mainTeam = new ArrayList<>();
        this.reserveTeam = new ArrayList<>();
        this.maxMainTeam = 5;
        this.maxReserveTeam = 5;
        this.random = new Random();
        this.retiredTeam = new ArrayList<>();
    }

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

    public void retireAdventurer(Adventurer adventurer) {
        if (adventurer == null) {
            return;
        }

        if (mainTeam.contains(adventurer) && mainTeam.size() <= 1) {
            System.out.println("You cannot retire your last main team adventurer.");
            return;
        }

        adventurer.retire();
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

    public int getTotalStamina() {
        int total = 0;

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                total += adventurer.stamina;
            }
        }

        return total;
    }

    public int getTotalPerception() {
        int total = 0;

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                total += adventurer.perception;
            }
        }

        return total;
    }

    public double getTotalPay() {
        double total = 0;

        for (Adventurer adventurer : mainTeam) {
            if (adventurer.canGoOnExpedition()) {
                total += adventurer.pay;
            }
        }

        return total;
    }

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

    public Adventurer generateNewAdventurer() {
        int choice = random.nextInt(4);
        int number = random.nextInt(900) + 100;

        if (choice == 0) {
            return new Warrior("Warrior" + number);
        } else if (choice == 1) {
            return new Mage("Mage" + number);
        } else if (choice == 2) {
            return new Marksman("Marksman" + number);
        } else {
            return new Rogue("Rogue" + number);
        }
    }

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
